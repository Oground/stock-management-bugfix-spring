package jp.co.rakus.stockmanagement.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

/**
 * 書籍関連のリクエストパラメータが入るフォーム.
 * 
 * @author igamasayuki
 *
 */
public class BookForm {
	/** id */
	private Integer id;

	/** 書籍名 */
	@NotBlank(message = "値を入力してください")
	private String name;

	/** 著者 */
	@NotBlank(message = "値を入力してください")
	private String author;

	/** 出版社 */
	@NotBlank(message = "値を入力してください")
	private String publisher;

	/** 価格 */
	@NotBlank(message = "値を入力してください")
	@Digits(integer = 9, fraction = 0, message = "数字を入力してください")
	private String price;

	/** ISBNコード */
	@NotBlank(message = "値を入力してください")
	private String isbncode;

	/** 発売日 */
	@NotBlank(message = "値を入力してください")
	private String saledate;

	/** 説明 */
	@NotBlank(message = "値を入力してください")
	@Size(min = 8, max = 200, message = "説明文は8文字以上200文字以内で入力してください")
	private String explanation;

	/** 画像 */
	@NotNull(message = "画像を選択してください")
	private MultipartFile image;

	/** 在庫数 */
	@NotBlank(message = "値を入力してください")
	@Digits(integer = 9, fraction = 0, message = "数字を入力してください")
	private String stock;

	public int getIntPrice() {
		return Integer.parseInt(price);
	}

	public int getIntStock() {
		return Integer.parseInt(stock);
	}

	public Date getDateSaleDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = sdf.parse(saledate);
		} catch (ParseException e) {
			return null;
		}
		return date;
	}

	/** 以降getter/setter */

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getIsbncode() {
		return isbncode;
	}

	public void setIsbncode(String isbncode) {
		this.isbncode = isbncode;
	}

	public String getSaledate() {
		return saledate;
	}

	public void setSaledate(String saledate) {
		this.saledate = saledate;
	}

	public String getExplanation() {
		return explanation;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}

	public MultipartFile getImage() {
		return image;
	}

	public void setImage(MultipartFile image) {
		this.image = image;
	}

	public String getStock() {
		return stock;
	}

	public void setStock(String stock) {
		this.stock = stock;
	}

}
