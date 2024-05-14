
// export const userRoleGuard: CanActivateFn = (route, state) => {
//   return true;
// };

import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import {LoginService} from "../services/login.service";

@Injectable({
  providedIn: 'root'
})
export class UserRoleGuard implements CanActivate {

  constructor(private authService: LoginService, private router: Router) {}

  canActivate(): boolean {
    if (this.authService.isUser()) {
      return true;
    } else {
      this.router.navigate(['/access-denied']);
      return false;
    }
  }
}
