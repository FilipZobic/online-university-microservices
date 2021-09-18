import { put, takeEvery } from 'redux-saga/effects';

import {
    AUTH_LOGIN_REQUEST,
    AUTH_LOGIN_SUCCESS,
    AUTH_LOGIN_FAILED
} from '../actions/auth/constants';

import { postLoginUser } from '../../api/auth/auth';
import jwtDecode from 'jwt-decode';

function* loginUser(action) {
    try {
        const response = yield postLoginUser(action.username, action.password);
        console.log(response);
        if ((response.status === 200 || response.status === 201) && response.data) {
            const token =  response.data.replace('Bearer ', '');
            const user = jwtDecode(token);
            console.log(user);
            yield put({
                type: AUTH_LOGIN_SUCCESS,
                token: token,
                user: user
            });
            
        } else {
            throw Error('Something went wrong');
        }

    } catch(error) {
        yield put({
            type: AUTH_LOGIN_FAILED,
            error: error.message
        });
    }
     
}

// eslint-disable-next-line @typescript-eslint/explicit-module-boundary-types
export default function* authSaga() {
    yield takeEvery(AUTH_LOGIN_REQUEST, loginUser);
}