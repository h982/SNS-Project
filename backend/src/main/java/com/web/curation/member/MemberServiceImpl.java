package com.web.curation.member;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	MemberDao memberDao;

	@Override
	public Optional<Member> checkEmail(String email) {
		return memberDao.getMemberByEmail(email);
	}

	@Override
	public Member registMember(Member member) {
		return memberDao.save(member);
	}
	
	@Override
	public Optional<Member> getUser(String email) {
		return memberDao.getMemberByEmail(email);
	}

	@Override
	public Optional<Member> getUser(String email, String password) {
		return memberDao.getMemberByEmailAndPassword(email, password);
	}

	@Override
	public void certifyMemberByEmail(String email) {
		memberDao.updateAuthentication(email);
	}

	@Override
	public Optional<Member> getMemberByEmail(String email) {
		return memberDao.getMemberByEmail(email);
	}

}
