package com.web.curation.team.join;

import java.util.Optional;

public interface JoinTeamService {
	public void createJoin(JoinTeam joinTeam);
	public Optional<JoinTeam> getSameContent(JoinTeam joinTeam);
}
