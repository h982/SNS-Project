import axios from "axios";
import store from "@/store/index";
//import { API_BASE_URL } from "../config";
//import http from '@/util/http-common';
function createInstance() {
  const instance = axios.create({
    baseURL: "http://localhost:8080",
    headers: {
      "Content-Type": "application/json",
      "access-token":store.state.token,
    }
  });
  return instance;
}

export { createInstance };
