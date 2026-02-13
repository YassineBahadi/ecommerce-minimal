export interface LoginRequest{
    email:string;
    password:string;
}

export interface LoginResponse{
    token:string;
    type:string;
    id:number;
    email:string;
    firstName:string;
    lastName:string;
    roles:string[];
}

export interface User{
    id:number;
    email:string;
    firstName:string;
    lastName:string;
    roles:string[];
}

export interface SignupRequest{
    email:string;
    password:string;
    firstName:string;
    lastName:string;
}