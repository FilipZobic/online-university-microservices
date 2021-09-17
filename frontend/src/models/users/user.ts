export interface User {
    id: string;
    username: string;
    email: string;
    isDeleted: boolean;
    isEnabled: boolean;
    grantedAuthorities: string[];
}

export interface NewUser {
    username: string;
    password: string;
    email: string;
    isEnabled: boolean;
}

export interface UserAction {
    type: string,
    payload: string | '',
    data: User[] | null
}

export interface NewUserAction {
    type: string,
    username: string,
    password: string,
    email: string,
    isEnabled: boolean
}