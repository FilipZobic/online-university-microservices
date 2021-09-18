import { Todo } from '../../../api/todos/models/todos';
import {
    FETCH_ALL_TODOS_REQUEST,
    FETCH_TODO_REQUEST,
    CREATE_TODO_REQUEST
} from './constants';

export interface todoAction {
    type: string,
    id?: number,
    userId?: number
    title?: string,
    payload?: Todo | Todo[],
    error?: string

}

export const fetchAllTodos = (): todoAction => {
    return {
        type: FETCH_ALL_TODOS_REQUEST
    }
}

export const fetchTodo = (id: number): todoAction => {
    return {
        type: FETCH_TODO_REQUEST,
        id: id
    }
}

export const createTodo = (id: number, userId: number, title: string): todoAction => {
    return {
        type: CREATE_TODO_REQUEST,
        id: id,
        userId: userId,
        title: title
    }
}