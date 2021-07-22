package com.web.curation.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.curation.dao.user.MemberDao;
import com.web.curation.model.Member;

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

}
