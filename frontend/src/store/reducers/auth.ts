import { UserAuthAction } from '../actions/auth/actions';
import {
    AUTH_LOGIN_REQUEST,
    AUTH_LOGIN_SUCCESS,
    AUTH_LOGIN_FAILED,
    LOGOUT
} from '../actions/auth/constants';

export interface authUser {
    sub: string,
    created: number
    exp: number
    authorities: string[]
}

export interface authReducerState {
    token?: string 
    loading: boolean
    error: boolean
    errorMessage?: string
    user?: authUser 
}

const initaleState: authReducerState = {
    token: '',
    loading: false,
    error: false,
    errorMessage: '',
    user: undefined
}

const authReducer = (state: authReducerState = initaleState, action: UserAuthAction): authReducerState => {
    switch(action.type) {
        case AUTH_LOGIN_REQUEST:
            return {...state, loading: true, error: false, errorMessage: ''}
        case AUTH_LOGIN_SUCCESS:
            return {...state, loading: false, token: action.token, user: action.user}
        case AUTH_LOGIN_FAILED:
            return {...state, loading: false, error: true, errorMessage: action.error, token: ''}
        case LOGOUT:
            return initaleState;
    }
    return state;
}

export default authReducer;