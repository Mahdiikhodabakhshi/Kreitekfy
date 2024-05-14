import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {LoginComponent} from "../components/login/login.component";
import {jwtDecode} from 'jwt-decode'

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private token: string | null = null;


  constructor(private http: HttpClient) { }

  BASE_URL = "http://localhost:8080/auth";


  register(user:any):Observable<any>{
    return this.http.post<any>(`${this.BASE_URL}/register`, user);
  }

  login(credentials: any): Observable<any> {
    return this.http.post(`${this.BASE_URL}/login`, credentials );
  }

  saveToken(token: string): void {
    this.token = token;
    localStorage.setItem('auth_token', token);
  }

  isAuthenticated(): boolean {
    if (this.token != null){
      return true
    }
    else {
      return false
    }
  }

  logout(): void {
    this.token = null;

    localStorage.clear()
  }



  getToken(): string | null {
    return localStorage.getItem('auth_token');
  }


  isAdmin(): boolean {
    const token = this.getToken();
    if (!token) return false;
    const decodedToken: any = jwtDecode(token);
    return decodedToken.role === 'ADMIN';
  }


  isUser(): boolean {
    const token = this.getToken();
    if (!token) return false;

    const decodedToken: any = jwtDecode(token);
    console.log(decodedToken);
    return decodedToken.role === 'USER';
  }

  userId():string | null{
    const token = this.getToken();
    if (!token) return null;

    const decodedToken: any = jwtDecode(token);
    console.log(decodedToken);
    return decodedToken.sub ;
  }


}
