import axios, { AxiosResponse } from "axios";
import { NewUser, User } from "../../models/users/user";

export const getAllUsers = async (token: string): Promise<AxiosResponse<User[]>> => {

    const config = {
        headers: {
            'Authorization': 'Bearer ' + token
        }
    }

    const data = await axios.get('http://localhost:8083/api/users', config);
    const response = data.data;
    return response
}

export const postCreateUser = async (user: NewUser): Promise<AxiosResponse<User>> => {

    const data = await axios.post('http://localhost:8083/api/users', user);
    const response = data.data;
    return response
}


export const softDeleteUser = async (id: string, token: string): Promise<AxiosResponse<User>> => {

    const config = {
        headers: {
            'Authorization': 'Bearer ' + token,
            'Content-Type': 'text/plain'
        },
    }

    const data = await axios.delete('http://localhost:8083/api/users/' + id, config);
    const response = data.data;
    return response
}

export const disableUser = async (id: string, email: string, username: string, isEnabled: boolean, token: string): Promise<AxiosResponse<User>> => {

    const config = {
        headers: {
            'Authorization': 'Bearer ' + token,
            'Content-Type': 'text/plain'
        },
    }

    const payload = {
        email: email,
        username: username,
        isEnabled: isEnabled
    }

    const data = await axios.put('http://localhost:8083/api/users/' + id, payload, config);
    const response = data.data;
    return response
}