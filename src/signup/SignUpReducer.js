import { USER_SIGNUP } from "./SignUpActions";
import {LOAD_SEC} from './SignUpActions';

const initialState = {
  signup: false,
  SecurityQuestions: [],
  isLoading: true,
  securityQuestionsId:0,
  
};

export default function (state = initialState, action) {
  const { type, data } = action;
  switch (type) {
    case USER_SIGNUP:
      return {
        ...state,
        signup: data,
      };
    case LOAD_SEC:
      return {
        ...state,
        SecurityQuestions: data,
        securityQuestionsId:0,
      };
    default:
      return state;
  }
}
