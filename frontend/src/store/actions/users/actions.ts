import {  NewUserAction, User, UserAction } from '../../../models/users/user';
import {
    GET_ALL_USERS_REQUEST,
    POST_CREATE_USER_REQUEST
} from './constants';


export const getAllUsers = (payload: string, data: User[] | null): UserAction => {
    return {
        type: GET_ALL_USERS_REQUEST,
        payload: payload,
        data: data
    }
}

export const createUser = (username: string, password: string, email: string, isEnabled: boolean ): NewUserAction => {
    return {
        type: POST_CREATE_USER_REQUEST,
        username: username,
        password: password,
        email: email,
        isEnabled: isEnabled
    }
}