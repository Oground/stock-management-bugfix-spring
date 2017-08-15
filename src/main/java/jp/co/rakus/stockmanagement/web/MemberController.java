package jp.co.rakus.stockmanagement.web;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.rakus.stockmanagement.domain.Member;
import jp.co.rakus.stockmanagement.service.MemberService;

/**
 * メンバー関連処理を行うコントローラー.
 * @author igamasayuki
 *
 */
@Controller
@RequestMapping("/member")
@Transactional
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	@Autowired
	private StandardPasswordEncoder standardPasswordEncoder;
	
	/**
	 * フォームを初期化します.
	 * @return フォーム
	 */
	@ModelAttribute
	public MemberForm setUpForm() {
		return new MemberForm();
	}
	
	/**
	 * メンバー情報登録画面を表示します.
	 * @return メンバー情報登録画面
	 */
	@RequestMapping(value = "form")
	public String form() {
		return "/member/form";
	}
	
	/**
	 * メンバー情報を登録します.
	 * @param form フォーム
	 * @param result リザルト
	 * @param model モデル
	 * @return ログイン画面
	 */
	@RequestMapping(value = "create")
	public String create(@Validated MemberForm form, BindingResult result, Model model) {
		Member member = new Member();
		
		BeanUtils.copyProperties(form, member);
		String password = standardPasswordEncoder.encode(form.getPassword());
		member.setPassword(password);
		
		Member checkMember = memberService.findByMailAddress(member.getMailAddress());
		
		if(checkMember != null){
			result.rejectValue("mailAddress", null, "既に登録されているメールアドレスです。他のメールアドレスを登録してください。");
		}
		
		if(!standardPasswordEncoder.matches(form.getCheckPassword(), password)){
			result.rejectValue("password", null, "入力されたパスワードとパスワード(確認用)が異なっています。再度入力し直してください。");
		}
		
		if(result.hasErrors()){
			return form();			
		}
		
		memberService.save(member);
		return "redirect:/";
	}
	
}
