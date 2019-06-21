import axios from 'axios';

export const USER_LOGIN = "USER_LOGIN";
export const USER_LOGOUT = "USER_LOGOUT";

export function checkAuth(userName, Password) {

  return async function (dispatch) {

    axios.post("http://localhost:5050/agri-easy/auth", {
      loginId: userName,
      password: Password,
    }
    ).then(res => {
      return dispatch({
        type: "USER_LOGIN",
        login: true,
        userType: res.data.user_type,
        user_name:res.data.firstName,
      });
    }).catch(error => {
      alert("API Not Connected .... !");
    })
  };
}

export function logout() {
  return async function (dispatch) {
    return dispatch({
      type: "USER_LOGOUT",
      login: false,
    });
  };
}

