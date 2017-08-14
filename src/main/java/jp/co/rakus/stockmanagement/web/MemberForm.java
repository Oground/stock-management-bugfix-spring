package jp.co.rakus.stockmanagement.web;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

/**
 * メンバー関連のリクエストパラメータが入るフォーム.
 * @author igamasayuki
 *
 */
public class MemberForm {
	/** 名前 */
	@NotBlank(message="名前は必須項目です。入力してください。")
	private String name;
	/** メールアドレス */
	@NotBlank(message="メールアドレスは必須項目です。入力してください。")
	@Email(message="Eメールの形式が不正です。")
	private String mailAddress;
	/** パスワード */
	@NotBlank(message="パスワードは必須項目です。入力してください。")
	@Size(min=8, max=16, message="パスワードは8文字以上16文字以内で入力してください。")
	private String password;
	@NotBlank(message="パスワードは必須項目です。入力してください。")
	@Size(min=8, max=16, message="パスワードは8文字以上16文字以内で入力してください。")
	private String checkPassword;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMailAddress() {
		return mailAddress;
	}
	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCheckPassword() {
		return checkPassword;
	}
	public void setCheckPassword(String checkPassword) {
		this.checkPassword = checkPassword;
	}
}
