/*
 User API 예시
 */
import { createInstance } from "./index.js";

const instance = createInstance();
const requestLogin = (data,callback,errorCallback) => {
     //백앤드와 로그인 통신하는 부분
     //callback();
    instance.defaults.headers["access-token"] = window.localStorage.getItem(
        "access-token"
    );
    const body = {
        email: data.email,
        password: data.password,
    };
    
    instance
        .get(`/account/login?email=${data.email}&password=${data.password}`)
        .then(callback)
        .catch(errorCallback);
}

const UserApi = {
    requestLogin:(data,callback,errorCallback)=>requestLogin(data,callback,errorCallback)
}

export default UserApi
