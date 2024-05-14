import { Component } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {LoginService} from "../../services/login.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  loginForm: FormGroup = this.fb.group({
    username: ['', [Validators.required]],
    password: ['', Validators.required]
  });
  signupForm: FormGroup = this.fb.group({
    username: ['', Validators.required],
    password: ['', [Validators.required]],
    firstName: ['', Validators.required],
    lastName: ['', Validators.required],
    email: ['', Validators.required],
    role: ['USER', Validators.required]

  });


  user: any = {
    username: '',
    password: '',
    firstName: '',
    lastName: '',
    email: '',
    role: 'USER'
  };

  constructor(private fb: FormBuilder , private router: Router , private loginService:LoginService)  {}

  get loginFormControl() {
    return this.loginForm.controls;
  }

  get signupFormControl() {
    return this.signupForm.controls;
  }

  onLogin() {
    if (this.loginForm.valid) {
      // Handle login logic
      //console.log('Login form submitted:', this.loginForm.value);
      this.loginService.login(this.loginForm.getRawValue()).subscribe({
        next:data => {
          console.log(data.token)
          this.loginService.saveToken(data.token)
          this.router.navigate(["/user"])
        },
        error:err => console.log(err),
        complete:()=>console.log('done')
      })
    }
  }

  onSignup() {
    if (this.signupForm.valid) {
      // Handle signup logic
      console.log('Signup form submitted:', this.signupForm.value);
      this.loginService.register(this.signupForm.getRawValue()).subscribe({
        next:data=> {
          console.log(data.token)
          this.loginService.saveToken(data.token);
          this.router.navigate(["/user"])

        }
      })

    }
  }



}
