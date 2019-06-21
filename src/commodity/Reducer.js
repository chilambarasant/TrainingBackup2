import { GET_COMMODITY, GET_COMMODITY_MASTER, GET_LOCATION_MASTER, SAVE_COMMODITY_MASTER } from './Action'

const initialState = {
    commodity_list: [],
    commodity_master_list: [],
    location_master_list: [],
    saveStatus: '',
};

export default function (state = initialState, action) {
    const { type, data,commodity_master_list,location_master_list,saveStatus } = action;
    switch (type) {
        case GET_COMMODITY:
            return {
                ...state,
                commodity_list: data,
            };
        case GET_COMMODITY_MASTER:
            return {
                ...state,
                commodity_master_list : commodity_master_list,
            };
        case GET_LOCATION_MASTER:   
            return {
                ...state,
                location_master_list : location_master_list,
            };
        case SAVE_COMMODITY_MASTER:
            return {
                ...state,
                saveStatus: saveStatus,
            };
        default:
            return state;
    }
}