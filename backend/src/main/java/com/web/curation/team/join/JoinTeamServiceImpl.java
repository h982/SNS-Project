package com.web.curation.team.join;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JoinTeamServiceImpl implements JoinTeamService {
	@Autowired
	JoinTeamDao joinTeamDao;

	@Override
	public void createJoin(JoinTeam joinTeam) {
		joinTeamDao.save(joinTeam);
	}

	@Override
	public Optional<JoinTeam> getSameContent(JoinTeam joinTeam) {
		return joinTeamDao.findByMemberAndTeam(joinTeam.getMember(), joinTeam.getTeam());
	}

}
