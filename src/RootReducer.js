import { combineReducers } from 'redux';

import login from './login/LoginReducer'
import SignUp from './signup/SignUpReducer'
import commodityReducer from './commodity/Reducer'
import DashBoardReducer from './dashboard/DashBoardReducer'
import orderReducer from './order_commodity/reducer'

import {reducer as formReducer} from 'redux-form'

const RootReducer = combineReducers({
    login,
    form:formReducer,
    SignUp,
    commodityReducer,
    DashBoardReducer,
    orderReducer,
});

export default RootReducer;