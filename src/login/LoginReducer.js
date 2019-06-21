import { USER_LOGIN, USER_LOGOUT } from "./LoginActions";

const initialState = {
  login: false,
  userType:'admin',
  user_name:'',
};

export default function(state = initialState, action) {
  const { type,login, userType,user_name} = action;
  switch (type) {
    case USER_LOGIN:
      return {
        ...state,
        login: login,
        userType:userType,
        user_name:user_name,
      };
    case USER_LOGOUT:
      return {
        ...state,
        login: login,
      };
    default:
      return state;
  }
}
