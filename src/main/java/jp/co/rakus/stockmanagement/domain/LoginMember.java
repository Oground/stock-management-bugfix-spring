package jp.co.rakus.stockmanagement.domain;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class LoginMember extends User{

	private static final long serialVersionUID = 1L;
	
	private Member member;
	
	public LoginMember(Member member, Collection<GrantedAuthority> authorityList){
		super(member.getMailAddress(), member.getPassword(), authorityList);
		this.member = member;
	}
	
	public Member getMember(){
		return member;
	}
	
	public void setMember(Member member){
		this.member = member;
	}
}
