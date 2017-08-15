package jp.co.rakus.stockmanagement.web;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.rakus.stockmanagement.domain.Book;
import jp.co.rakus.stockmanagement.service.BookService;

/**
 * 書籍関連処理を行うコントローラー.
 * @author igamasayuki
 *
 */
@Controller
@RequestMapping("/book")
@Transactional
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private ServletContext application;
	
	/**
	 * フォームを初期化します.
	 * @return フォーム
	 */
	@ModelAttribute
	public BookForm setUpForm() {
		return new BookForm();
	}
	
	/**
	 * 書籍リスト情報を取得し書籍リスト画面を表示します.
	 * @param model モデル
	 * @return 書籍リスト表示画面
	 */
	@RequestMapping(value = "list")
	public String list(Model model) {
		List<Book> bookList = bookService.findAll();
		model.addAttribute("bookList", bookList);
		return "book/list";
	}

	/**
	 * 書籍詳細情報を取得し書籍詳細画面を表示します.
	 * @param id 書籍ID
	 * @param model　モデル
	 * @return　書籍詳細画面
	 */
	@RequestMapping(value = "show/{bookId}")
	public String show(@PathVariable("bookId") Integer id, Model model) {
		Book book = bookService.findOne(id);
		model.addAttribute("book", book);
		return "book/show";
	}
	
	/**
	 * 書籍登録画面を表示します.
	 * @param model モデル
	 * @return 書籍登録画面
	 */
	@RequestMapping(value = "/add")
	public String add(Model model) {
		return "book/add";
	}
	
	/**
	 * 書籍の追加を行います.
	 * @param form フォーム
	 * @param result リザルト情報
	 * @param model　モデル
	 * @return　書籍リスト画面
	 */
	@RequestMapping(value = "/create")
	public String create(@Validated BookForm form, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return add(model);
		}
		Book book = new Book();
		BeanUtils.copyProperties(form, book);
		book.setPrice(form.getIntPrice());
		book.setStock(form.getIntStock());
		book.setSaledate(form.getDateSaleDate());
		
		String realPath = application.getRealPath("/img");

		try{
        Path path = Paths.get(realPath,
                form.getImage().getOriginalFilename());
        System.out.println("path:" + path.toString());
        form.getImage().transferTo(path.toFile());
		}catch(IOException e){}
		
		book.setImage(form.getImage().getOriginalFilename());
		
		List<Book> bookList = bookService.findAll();
		int id = 0;
		for (Book book2 : bookList) {
			if(id < book2.getId()){
				id = book2.getId();
			}
		}
		
		book.setId(id + 1);
		
		bookService.save(book);
				
		return list(model);
	}

	/**
	 * 書籍更新を行います.
	 * @param form フォーム
	 * @param result リザルト情報
	 * @param model　モデル
	 * @return　書籍リスト画面
	 */
	@RequestMapping(value = "update")
	public String update(@Validated BookForm form, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return show(form.getId(), model);
		}
		Book book = bookService.findOne(form.getId());
		book.setStock(form.getIntStock());
		bookService.update(book);
		return list(model);
	}

}
