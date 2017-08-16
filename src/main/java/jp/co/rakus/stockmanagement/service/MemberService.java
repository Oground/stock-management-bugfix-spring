package jp.co.rakus.stockmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jp.co.rakus.stockmanagement.domain.Member;
import jp.co.rakus.stockmanagement.repository.MemberRepository;

/**
 * メンバー関連サービスクラス.
 * @author igamasayuki
 *
 */
@Service
public class MemberService {

	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

//	public List<Member> findAll(){
//		return memberRepository.findAll();
//	}
//
//	public Member findOne(Integer id) {
//		return memberRepository.findOne(id);
//	}

	public Member findByMailAddress(String mailAddress) {
		return memberRepository.findByMailAddress(mailAddress);
	}

	public Member findOneByMailAddressAndPassword(String mailAddress, String password){
		return memberRepository.findByMailAddressAndPassword(mailAddress, password);
	}

	public Member save(Member member){
		return memberRepository.save(member);
	}
	
	public String encodePassword(String rowPassword){
		String encodedPassword = passwordEncoder.encode(rowPassword);
		return encodedPassword;
	}

//	public Member update(Member member){
//		return memberRepository.save(member);
//	}
//
//	public void delete(Integer id){
//		memberRepository.delete(id);
//	}
}
