import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable } from 'rxjs';
import {LoginService} from "../services/login.service";
import {jwtDecode} from "jwt-decode";
import {Router} from "@angular/router";

@Injectable()
export class TokenInterceptorInterceptor implements HttpInterceptor {

  constructor(public authService:LoginService , private router:Router) {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    const token = this.authService.getToken();


    if (token){
      const tokenPayload = jwtDecode(token);
      const expirationDate = new Date(tokenPayload.exp?tokenPayload.exp * 1000:1); // Convert to milliseconds
      // Compare with current date
      const currentDate = new Date();
      if (currentDate > expirationDate) {
        // Delete the token
        localStorage.removeItem('token');
        this.router.navigate(['/login']);

        // Redirect the user to the login page or show a message
        // You can also trigger an event to notify other parts of the application about token expiration
      }else {
        request = request.clone({
          setHeaders: {'Authorization': `Bearer ${token}`}
        })
      }

    }
    return next.handle(request);
  }
}
