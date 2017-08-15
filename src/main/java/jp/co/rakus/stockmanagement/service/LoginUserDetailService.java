package jp.co.rakus.stockmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import jp.co.rakus.stockmanagement.domain.Member;
import jp.co.rakus.stockmanagement.repository.MemberRepository;

@Service
public class LoginUserDetailService implements UserDetailsService{

	@Autowired
	private MemberRepository memberRepository;

	@Override
	public UserDetails loadUserByUsername(String mailAddress) throws UsernameNotFouondExcption{
		Member member = memberRepository.findByMailAddress(mailAddress);
		if(member == null){
			throw new UserNameNotFoundException("The requested user not found.");
		}return new LoginUserDetails(member);
	}
}
