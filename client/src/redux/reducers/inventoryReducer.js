import {CREATE_STORE, DELETE_STORE, GET_STORES_BY_ADMIN} from "../../redux/constants/actionTypes";


const initialState = {
    stores: []
};

export default function inventoryReducer(state = initialState, action) {
    console.log("inventoryReducer:");
    console.log(action.payload);

    if (action.type === CREATE_STORE) {
        return Object.assign({}, state, {
            stores: action.payload
        });
    } else if (action.type === GET_STORES_BY_ADMIN) {
        return Object.assign({}, state, {
            stores: action.payload
        });
    } else if (action.type === DELETE_STORE) {
        return Object.assign({}, state, {
            stores: action.payload
        });
    }
    return state;
}