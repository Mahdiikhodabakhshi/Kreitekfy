import {Injectable} from "@angular/core";
import {CanActivate, Router} from "@angular/router";
import {LoginService} from "../services/login.service";

@Injectable({
  providedIn: 'root'
})
export class AdminRoleGuard implements CanActivate {

  constructor(private authService: LoginService, private router: Router) {}

  canActivate(): boolean {
    if (this.authService.isAdmin()) {
      return true;
    } else {
      this.router.navigate(['/access-denied']); // Navigate to access denied page or any other page
      return false;
    }
  }
}
