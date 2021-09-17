import axios, { AxiosResponse } from "axios";
import { Todo } from './models/todos';

// eslint-disable-next-line @typescript-eslint/explicit-module-boundary-types
export const getTodos = async ()  => {
    const response: AxiosResponse<Todo[]>  = await axios.get('https://jsonplaceholder.typicode.com/todos')

    return response;
}   

// eslint-disable-next-line @typescript-eslint/explicit-module-boundary-types
export const getTodo = async (id: number) => {
    const response: AxiosResponse<Todo>  = await axios.get('https://jsonplaceholder.typicode.com/todos/' + id);

    return response;
}

// eslint-disable-next-line @typescript-eslint/explicit-module-boundary-types
export const postTodo = async (todo: Todo) => {
    const response: AxiosResponse<null> = await axios.post('https://jsonplaceholder.typicode.com/todos', todo);
    
    return response;
}