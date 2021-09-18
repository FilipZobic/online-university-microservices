import { put, takeEvery } from 'redux-saga/effects';

import {
    GET_ALL_USERS_REQUEST,
    GET_ALL_USERS_SUCCESS,
    GET_ALL_USERS_FAILED,
    POST_CREATE_USER_REQUEST,
    POST_CREATE_USER_SUCCESS,
    POST_CREATE_USER_FAILED,
} from '../actions/users/constants';

import { getAllUsers, postCreateUser } from '../../api/users/users';

function* getAllUsersSaga(action) {
    try {
        const response = yield getAllUsers(action.payload);
        console.log(response);
        if (response.length >= 1) {
            yield put({
                type: GET_ALL_USERS_SUCCESS,
                payload: '',
                data: response
            })
            
        } else {
            throw Error('Something went wrong');
        }
    } catch(error) {
        yield put({
            type: GET_ALL_USERS_FAILED,
            payload: error.message,
            data: ''
        });
    }
     
}

function* postCreateUserSaga(action) {
    try {
        const payload = {
            username: action.username,
            password: action.password,
            email: action.email,
            isEnabled: action.isEnabled,
        }
        const response = yield postCreateUser(payload);
        console.log(response);
        if (response !== null ) {
            yield put({
                type: POST_CREATE_USER_SUCCESS,
            })
            
        } else {
            throw Error('Something went wrong');
        }
    } catch(error) {
        yield put({
            type: POST_CREATE_USER_FAILED,
            payload: error.message,
        });
    }
     
}

// eslint-disable-next-line @typescript-eslint/explicit-module-boundary-types
export default function* userSaga() {
    yield takeEvery(POST_CREATE_USER_REQUEST, postCreateUserSaga);
    yield takeEvery(GET_ALL_USERS_REQUEST, getAllUsersSaga);
}