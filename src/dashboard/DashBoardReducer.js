import { GET_DASHBOARD_DATA, GET_WEATHER_DATA } from './DashboardAction'

const initialState = {
    newlyAddedcomodity: [],
    weatherData: {},

}

export default function (state = initialState, action) {
    const { type, data } = action;
    switch (type) {
        case GET_DASHBOARD_DATA:
            return {
                ...state,
                newlyAddedcomodity: data,
            }
        case GET_WEATHER_DATA:
            return {
                ...state,
                weatherData: data,
            }
        default:
            return state;
    }
}
