import { ORDER_COMMODITY, ORDER_STATUS, ACCEPT_COMMODITY, UPDATE_BACK_STATUS, UPDATE_ACCEPT_STATUS } from './action'

const initialState = {
    orderStatus: '',
    order_list: [],
    acceptStatus: false,
    backAcceptCommodity: false,
    backUpdateOrderStatus:false,
};

export default function (state = initialState, action) {
    const { type, data } = action;
    switch (type) {
        case ORDER_COMMODITY:
            return {
                ...state,
                orderStatus: data,
            };
        case ORDER_STATUS:
            return {
                ...state,
                order_list: data,
            };
        case ACCEPT_COMMODITY:
            return {
                ...state,
                acceptStatus: data,
            };
        case UPDATE_BACK_STATUS:
            return {
                ...state,
                backAcceptCommodity: data,
            };
        case UPDATE_ACCEPT_STATUS:
            return {
                ...state,
                backUpdateOrderStatus: data,
            };
        default:
            return state;
    }
}