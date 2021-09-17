import axios, { AxiosResponse } from "axios";

interface userCredentials {
    username: string,
    password: string
}

export const postLoginUser = async (username: string, password: string): Promise<AxiosResponse<string>>  => {

    const user: userCredentials = {
        username: username,
        password: password
    }

    const response  = await axios.post<string>('http://localhost:8083/api/login', user);

    return response;
}   