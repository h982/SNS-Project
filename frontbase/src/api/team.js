import { createInstance } from "./teamindex.js";

const instance = createInstance();

function create(team, success, fail) {
  const body = {
    teamname: team.name,
    teamintroduction: team.introduction,
    teamimg: team.imgPath,
    leader: team.leader,
    sportId: team.sportId,
    leaderId: team.leaderId,
  };

  instance
    .post("/team/create", JSON.stringify(body))
    .then(success)
    .catch(fail);
}