/* eslint-disable @typescript-eslint/no-unused-vars */
import { put, takeEvery } from 'redux-saga/effects';

import {
    FETCH_ALL_TODOS_REQUEST,
    FETCH_ALL_TODOS_SUCCESS,
    FETCH_ALL_TODOS_FAILED,
    // FETCH_TODO_REQUEST,
    // FETCH_TODO_SUCCESS,
    // FETCH_TODO_FAILED,
    // CREATE_TODO_REQUEST,
    // CREATE_TODO_SUCCESS,
    // CREATE_TODO_FAILED
} from '../actions/todos/constants';

// import { todoAction } from '../actions/todos/actions';

import { getTodos } from '../../api/todos/todos';
import { AxiosResponse } from 'axios';
import { Todo } from '../../api/todos/models/todos';
import { todoAction } from '../actions/todos/actions';

function* fetchAllTodos() {
    let responseAction: todoAction;
    try {
        const response: AxiosResponse<Todo[]> = yield getTodos();
        if((response.status === 200 || response.status === 201) && response.data) {
            responseAction = {
                type: FETCH_ALL_TODOS_SUCCESS,
                payload: response.data
            }
            yield put(responseAction);
        } else {
            throw Error('Something went wrong');
        }
    } catch (error) {
        responseAction = {
            type: FETCH_ALL_TODOS_FAILED,
            // error: error.message
        }
        yield put(responseAction)
    }
}

// eslint-disable-next-line @typescript-eslint/explicit-module-boundary-types
export default function* todoSaga() {
    yield takeEvery(FETCH_ALL_TODOS_REQUEST, fetchAllTodos);
}