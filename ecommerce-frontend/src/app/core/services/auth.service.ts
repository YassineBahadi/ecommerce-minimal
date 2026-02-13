import { LoginResponse } from './../models/auth.model';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { ApiService } from './api.service';
import { Router } from '@angular/router';
import { LoginRequest, SignupRequest, User } from '../models/auth.model';
import {jwtDecode} from 'jwt-decode';
@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private currentUserSubject=new BehaviorSubject<User | null>(null);
  public currentUser$=this.currentUserSubject.asObservable();



  constructor(private apiService:ApiService,private router:Router) {
    this.loadCurrentUser();
   }

   login(loginRequest:LoginRequest):Observable<LoginResponse>{
    return new Observable(observer=>{
      this.apiService.post<LoginResponse>('/auth/login',loginRequest)
      .then(response=>{
        const loginResponse=response.data;
        this.setSession(loginResponse);
        observer.next(loginResponse);
        observer.complete();
      })
      .catch(error=>observer.error(error));
    });
   }

   signup(signupRequest:SignupRequest):Observable<any>{
    return new Observable(observer=>{
      this.apiService.post('/auth/signup',signupRequest)
      .then(response=>{
        observer.next(response.data);
        observer.complete();
      })
      .catch(error=>observer.error(error));
    });
   }

   logout():void{
    localStorage.removeItem('access_token');
    localStorage.removeItem('user');
    this.currentUserSubject.next(null);
    this.router.navigate(['/login']);
   }

   getCurrentUser():User |null{
    return this.currentUserSubject.value;
   }

   isLoggedIn():boolean{
    const token=localStorage.getItem('access_token');
    if(!token) return false;

    try{
      const decoded:any=jwtDecode(token);
      const currentTime=Date.now()/1000;
      return decoded.exp>currentTime;
    } catch{
      return false;
    }
   }

   isAdmin():boolean{
    const user=this.getCurrentUser();
    return user?.roles.includes('ROLE_ADMIN') || false;
   }

   getToken():string|null{
    return localStorage.getItem('access_token');
   }

   private setSession(loginResponse:LoginResponse):void{
    localStorage.setItem('access_token',loginResponse.token);

    const user:User={
      id:loginResponse.id,
      email:loginResponse.email,
      firstName:loginResponse.firstName,
      lastName:loginResponse.lastName,
      roles:loginResponse.roles,
    };
    localStorage.setItem('user',JSON.stringify(user));
    this.currentUserSubject.next(user);
   }

   private loadCurrentUser():void{
    if(this.isLoggedIn()){
      const userStr=localStorage.getItem('user');
      if(userStr){
        try{
          const user=JSON.parse(userStr);
          this.currentUserSubject.next(user);
        } catch{
          this.logout();
        }
      }
    }
   }
}
