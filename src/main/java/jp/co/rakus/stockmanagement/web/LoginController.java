package jp.co.rakus.stockmanagement.web;

//import javax.servlet.http.HttpSession;

//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.StandardPasswordEncoder;
//import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
//import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

//import jp.co.rakus.stockmanagement.domain.Member;
//import jp.co.rakus.stockmanagement.service.LoginUserDetails;
//import jp.co.rakus.stockmanagement.service.MemberService;

/**
 * ログイン関連処理を行うコントローラー.
 * @author igamasayuki
 *
 */
@Controller
@RequestMapping("/")
public class LoginController {

//	@Autowired
//	private MemberService memberService;

//	@Autowired
//	private StandardPasswordEncoder standardPasswordEncoder;

//	@Autowired
//	private HttpSession session;

	/**
	 * フォームを初期化します.
	 * @return フォーム
	 */
	@ModelAttribute
	public LoginForm setUpForm() {
		return new LoginForm();
	}

	/**
	 * ログイン画面を表示します.
	 * @return ログイン画面
	 */
	@RequestMapping("/")
	public String index(LoginForm form, BindingResult result, Model model, @RequestParam(required = false) String error) {
		if(error != null){
			result.addError(new ObjectError("loginError", "メールアドレスまたはパスワードが不正です"));
		}
		return "loginForm";
	}
	
//	/**
//	 * ログイン処理を行います.
//	 * @param form　フォーム
//	 * @param result　リザルト
//	 * @param model　モデル
//	 * @return　ログイン成功時：書籍リスト画面
//	 */
//	@RequestMapping(value = "/login")
//	public String login(@Validated LoginForm form,
//			BindingResult result, Model model, @AuthenticationPrincipal LoginUserDetails loginUserDetails) {
//		if (result.hasErrors()){
//			return index();
//		}
//		String mailAddress = form.getMailAddress();
//		Member member = memberService.findByMailAddress(mailAddress);
//		if (member == null || !standardPasswordEncoder.matches(form.getPassword(), member.getPassword())) {
//			ObjectError error = new ObjectError("loginerror", "メールアドレスまたはパスワードが違います。");
//            result.addError(error);
//			return index();
//		}
//		session.setAttribute("member", member);
//		return "redirect:/book/list";
//	}
}