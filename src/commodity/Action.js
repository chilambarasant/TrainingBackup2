import axios from 'axios';

export const GET_COMMODITY = 'GET_COMMODITY'
export const GET_COMMODITY_MASTER = 'GET_COMMODITY_MASTER'
export const GET_LOCATION_MASTER = 'GET_LOCATION_MASTER'
export const SAVE_COMMODITY_MASTER = 'SAVE_COMMODITY_MASTER'


export function getCommodityList() {
    return async function (dispatch) {
        try {
            const com_list = await axios.get('http://localhost:8181/v1/get-all-commodity-pricing-details')
            const commodity_list = await com_list.data;
            const tempData = []
            commodity_list.map((list) => {
                return tempData.push(Object.values(list))
            })
            return dispatch({
                type: 'GET_COMMODITY',
                data: tempData,
            });
        } catch (e) {
            console.log(e)
        }
    };
}

export function getCommodity() {
    return async function (dispatch) {
        try {
            const com_list = await axios.get('http://localhost:8181/v1/get-commodity-master')
            const commodity_master_list = await com_list.data;
            return dispatch({
                type: 'GET_COMMODITY_MASTER',
                commodity_master_list: commodity_master_list,
            });
        } catch (e) {
            console.log(e)
        }
    };
}

export function getLocation() {
    return async function (dispatch) {
        try {
            const loc_list = await axios.get(`http://localhost:8181/v1/get-location-master`)
            const location_master_list_data = await loc_list.data;
            return dispatch({
                type: 'GET_LOCATION_MASTER',
                location_master_list: location_master_list_data,
            });
        } catch (e) {
            console.log(e)
        }
    };
}

export function saveCommodity(commodityId, locationId, price, units, userId) {
    return function (dispatch) {
        try {
            let saveStatus = ''
            fetch('http://localhost:8181/v1/save-commodity', {
                method: 'POST',
                headers: {
                    'Accept': '*/*',
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({
                    cpCmId: commodityId,
                    cpLmId: locationId,
                    cpPrice: price,
                    cpUserId: userId,
                    cpCreatedby: userId,
                    cpUnits: units
                })
            }).then(data => {
                if (data.status === 201) {
                    saveStatus = "success"
                } else {
                    saveStatus = "failure"
                }
                return dispatch({
                    type: 'SAVE_COMMODITY_MASTER',
                    saveStatus: saveStatus,
                });
            }).catch(error => alert(error))
        } catch (e) {
            console.log(e)
        }
    };
}