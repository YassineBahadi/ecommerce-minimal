import { Injectable } from '@angular/core';
import axios, { AxiosInstance , AxiosRequestConfig } from 'axios';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  private axiosInstance:AxiosInstance;


  constructor() {
    this.axiosInstance=axios.create({
      baseURL:environment.apiUrl,
      headers:{
        'Content-Type':'application/json'
      }
    });

    this.axiosInstance.interceptors.request.use(
      (config)=>{
        const token = localStorage.getItem('access_token');
        if(token){
          config.headers.Authorization=`Bearer ${token}`;
        }
        return config;
      },
      (error)=> Promise.reject(error)
    );

    this.axiosInstance.interceptors.response.use(
      (response)=>response,
      (error)=>{
        if(error.response?.status===401){
          localStorage.removeItem('access_token');
          localStorage.removeItem('user');
          window.location.href='/login';
        }
        return Promise.reject(error);
      }
    );

   }

   get<T>(url:string,config?:AxiosRequestConfig){
    return this.axiosInstance.get<T>(url,config);
   }

   
   post<T>(url:string,data?:any,config?:AxiosRequestConfig){
    return this.axiosInstance.post<T>(url,data,config);
   }
   
    put<T>(url: string, data?: any, config?: AxiosRequestConfig) {
    return this.axiosInstance.put<T>(url, data, config);
  }

    delete<T>(url: string, config?: AxiosRequestConfig) {
    return this.axiosInstance.delete<T>(url, config);
  }







}
