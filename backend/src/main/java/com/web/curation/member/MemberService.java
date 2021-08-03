package com.web.curation.member;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
	@Autowired
	MemberDao memberDao;

	public boolean hasSameEmail(String email) {
		Optional<Member> member = memberDao.getMemberByEmail(email);
		
		if(member.isPresent()) return true;
		return false;
	}

	public MemberDto registMember(MemberDto memberDto) {
		Member member = new Member.MemberBuilder()
				.memberId(memberDto.getMemberId())
				.name(memberDto.getName())
				.email(memberDto.getEmail())
				.phone(memberDto.getPhone())
				.password(memberDto.getPassword())
				.address(memberDto.getAddress())
				.addressDetail(memberDto.getAddressDetail())
				.zonecode(memberDto.getZonecode())
				.sex(memberDto.getSex())
				.mbti(memberDto.getMbti())
				.authenticated(memberDto.getAuthenticated())
				.build();
		memberDao.save(member);
		return memberDto;
	}
	
	public Optional<MemberDto> getUser(String email) {
		Optional<Member> member = memberDao.getMemberByEmail(email);
		Optional<MemberDto> memberDto = null;
		if(member.isPresent()) {
			memberDto = Optional.of(MemberDto.builder()
					.memberId(member.get().getMemberId())
					.name(member.get().getName())
					.email(member.get().getEmail())
					.phone(member.get().getPhone())
					.password(member.get().getPassword())
					.address(member.get().getAddress())
					.addressDetail(member.get().getAddressDetail())
					.zonecode(member.get().getZonecode())
					.sex(member.get().getSex())
					.mbti(member.get().getMbti())
					.authenticated(member.get().getAuthenticated())
					.build());
			return memberDto;
		}
		return memberDto;
	}

	public Optional<MemberDto> getUser(String email, String password) {
		Optional<Member> member = memberDao.getMemberByEmailAndPassword(email, password);
		Optional<MemberDto> memberDto = null;
		if(member.isPresent()) {
			memberDto = Optional.of(MemberDto.builder()
					.memberId(member.get().getMemberId())
					.name(member.get().getName())
					.email(member.get().getEmail())
					.phone(member.get().getPhone())
					.password(member.get().getPassword())
					.address(member.get().getAddress())
					.addressDetail(member.get().getAddressDetail())
					.zonecode(member.get().getZonecode())
					.sex(member.get().getSex())
					.mbti(member.get().getMbti())
					.authenticated(member.get().getAuthenticated())
					.build());
			return memberDto;
		}
		return memberDto;
	}

	public void certifyMemberByEmail(String email) {
		memberDao.updateAuthentication(email);
	}

	public Optional<MemberDto> getMemberByEmail(String email) {
		Optional<Member> member = memberDao.getMemberByEmail(email);
		Optional<MemberDto> memberDto = null;
		if(member.isPresent()) {
			memberDto = Optional.of(MemberDto.builder()
					.memberId(member.get().getMemberId())
					.name(member.get().getName())
					.email(member.get().getEmail())
					.phone(member.get().getPhone())
					.password(member.get().getPassword())
					.address(member.get().getAddress())
					.addressDetail(member.get().getAddressDetail())
					.zonecode(member.get().getZonecode())
					.sex(member.get().getSex())
					.mbti(member.get().getMbti())
					.authenticated(member.get().getAuthenticated())
					.build());
			return memberDto;
		}
		return memberDto;
	}

}
