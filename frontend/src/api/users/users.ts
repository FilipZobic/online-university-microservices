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