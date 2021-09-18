
import { authUser } from '../../reducers/auth';
import {
    AUTH_LOGIN_REQUEST,
    LOGOUT
} from './constants';

export interface AuthAction {
    type: string,
}

export interface UserAuthAction extends AuthAction  {
    type: string
    username: string
    password: string
    token?: string
    error?: string
    user?: authUser
}

export const userLogin = (username: string, password: string): UserAuthAction => {
    return {
        type: AUTH_LOGIN_REQUEST,
        username: username,
        password: password
    }
}

export const userLogout = (): AuthAction => {
    return {
        type: LOGOUT,
    }
}