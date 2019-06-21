import axios from 'axios';

export const GET_DASHBOARD_DATA = 'GET_DASHBOARD_DATA';
export const GET_WEATHER_DATA = 'GET_WEATHER_DATA';

export function getData(){
    return async function (dispatch){
        const com_list = await axios.get('http://172.23.202.141:8181/v1/get-last-5-commodity-pricing-details')
        const commodity_list = await com_list.data;
        return dispatch({
                type: 'GET_DASHBOARD_DATA',
                data: commodity_list,
                
            });
    }
}

export function getWeatherData(place){
    return async function (dispatch){
        const com_list = await axios.get(`http://api.openweathermap.org/data/2.5/weather?q=Porur,in&APPID=ef49db59a5fa2c57fe9e3bddb3bf7a36&units=metric`)
        const weather = await com_list.data;
        return dispatch({
                type: 'GET_WEATHER_DATA',
                data: weather,
                
            });
    }
}