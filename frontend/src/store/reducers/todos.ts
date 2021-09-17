import {
    FETCH_ALL_TODOS_REQUEST,
    FETCH_ALL_TODOS_SUCCESS,
    FETCH_ALL_TODOS_FAILED,
    FETCH_TODO_REQUEST,
    CREATE_TODO_REQUEST
} from '../actions/todos/constants';
import { todoAction } from '../actions/todos/actions';
import { Todo } from '../../api/todos/models/todos';

export interface todoReducerState {
    todos?: Todo[] | Todo,
    loading: boolean,
    error: boolean,
    errorMessage?: string
}

const initaleState: todoReducerState = {
    todos: [],
    loading: false,
    error: false,
    errorMessage: ''
    
}

const todoReducer = (state: todoReducerState = initaleState, action: todoAction): todoReducerState => {
    switch (action.type) {
        case FETCH_ALL_TODOS_REQUEST:
            return {...state, loading: true, error: false, errorMessage: ''}
        case FETCH_ALL_TODOS_SUCCESS:
            return {...state, loading: false, todos: action.payload}
        case FETCH_ALL_TODOS_FAILED:
            return {...state, loading: false, error: true, errorMessage: action.error}
        case FETCH_TODO_REQUEST:
            return {...state, loading: true, error: false, errorMessage: ''}
        case CREATE_TODO_REQUEST:
                return {...state, loading: true, error: false, errorMessage: ''}
    }
    return state;
}

export default todoReducer;