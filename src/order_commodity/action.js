import axios from 'axios';
export const ORDER_COMMODITY = 'ORDER_COMMODITY';
export const ORDER_STATUS = 'ORDER_STATUS';
export const ACCEPT_COMMODITY = 'ACCEPT_COMMODITY';
export const UPDATE_BACK_STATUS = 'UPDATE_BACK_STATUS';

export const UPDATE_ACCEPT_STATUS = 'UPDATE_ACCEPT_STATUS';

export function orderCommodity(ordCpId, address) {
    return async function (dispatch) {
        try {
            let orderStatus = ''
            fetch('http://localhost:8182/v1/save_order_details', {
                method: 'POST',
                headers: {
                    'Accept': '*/*',
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({
                    ordCpId: ordCpId,
                    cpAddress: address,
                    createdBy: 1,
                })
            }).then(data => {
                if (data.status === 201) {
                    orderStatus = "success"
                } else {
                    orderStatus = "failure"
                }
                return dispatch({
                    type: 'ORDER_COMMODITY',
                    data: orderStatus,
                });
            }).catch(error => alert(error))
        } catch (e) {
            console.log(e)
        }
    };
}

export function getOderStatus() {
    return async function (dispatch) {
        try {
            const ord_list = await axios.get('http://localhost:8182/v1/get_order_details/1')
            const order_list = await ord_list.data;

            const tempData = []
            order_list.map((list) => {
                return tempData.push(Object.values(list))
            })
            return dispatch({
                type: 'ORDER_STATUS',
                data: tempData,
            });
        } catch (e) {
            console.log(e)
        }
    };
}

export function acceptCommodity(orderId, comments) {
    return async function (dispatch) {
        try {
            let acceptStatus = ''
            fetch('http://localhost:8182/v1/update_order_details', {
                method: 'POST',
                headers: {
                    'Accept': '*/*',
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({
                    ordId: orderId
                })
            }).then(data => {
                if (data.status === 201) {
                    acceptStatus = "success"
                } else {
                    acceptStatus = "failure"
                }
                return dispatch({
                    type: 'ACCEPT_COMMODITY',
                    data: acceptStatus,
                });
            }).catch(error => alert(error))
        } catch (e) {
            console.log(e)
        }
    };
}


export function updateBackStatus(status) {
    return async function (dispatch) {
        alert('Inside updateBackStatus' + status)
        try {
            return dispatch({
                type: 'UPDATE_BACK_STATUS',
                data: status,
            });
        } catch (e) {
            console.log(e)
        }
    };
}


export function updateAcceptOrderStatus(status) {
    return async function (dispatch) {
        alert('Inside updateAcceptOrderStatus :: ' + status)
        try {
            return dispatch({
                type: 'UPDATE_ACCEPT_STATUS',
                data: status,
            });
        } catch (e) {
            console.log(e)
        }
    };
}