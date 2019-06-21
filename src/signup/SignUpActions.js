import axios from "axios";

export const USER_SIGNUP = "USER_SIGNUP";
export const LOAD_SEC = "LOAD_SEC";

export function userSignUp(obj) {
  return async function(dispatch) {
    axios
      .post("http://localhost:5050/agri-easy/sign-up", obj)
      .then(res => {
        alert(res.status);
        return dispatch({
          type: "USER_SIGNUP",
          data: true
        });
      })
      .catch(error => {
        alert(error.response.data.exceptionMessage);
        return dispatch({
          type: "USER_SIGNUP",
          data: false
        });
      });
  };
}

export function getSecurityQuestion(userName, Password) {
  return async function(dispatch) {
    axios
      .get("http://localhost:5050/agri-easy/get-security-questions")
      .then(res => {
        
        return dispatch({ 
          type: "LOAD_SEC",
          data: res.data,
          isLoading:false
        });
      })
      .catch(error => {
        return dispatch({
          type: "LOAD_SEC",
          data: [],
          isLoading:false
        });
      });
  };
}

