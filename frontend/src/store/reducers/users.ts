import { UserAction, User } from '../../models/users/user';
import {
    GET_ALL_USERS_REQUEST,
    GET_ALL_USERS_SUCCESS,
    GET_ALL_USERS_FAILED,
    POST_CREATE_USER_REQUEST,
    POST_CREATE_USER_SUCCESS,
    POST_CREATE_USER_FAILED
} from '../actions/users/constants';

export interface UserReducerState {
    users: User[] | null
    error: boolean
    errorMessage: string
    isLoading: boolean
}

const initialState: UserReducerState = {
    users: null,
    error: false,
    errorMessage: '',
    isLoading: false,
}

const userReducer = (state: UserReducerState = initialState, action: UserAction): UserReducerState => {
    switch(action.type) {
        case GET_ALL_USERS_REQUEST:
            return {...state, isLoading: true, error: false, errorMessage: ''}
        case GET_ALL_USERS_SUCCESS:
            // eslint-disable-next-line @typescript-eslint/no-non-null-assertion
            return {...state, users: action.data!, isLoading: false}
        case GET_ALL_USERS_FAILED:
            return {...state, isLoading: false, error: true, errorMessage: action.payload}
        case POST_CREATE_USER_REQUEST:
            return {...state, error: false, errorMessage: ''}
        case POST_CREATE_USER_SUCCESS:
            // eslint-disable-next-line @typescript-eslint/no-non-null-assertion
            return {...state}
        case POST_CREATE_USER_FAILED:
            return {...state, error: true, errorMessage: action.payload}
    }

    return state;
}

export default userReducer;