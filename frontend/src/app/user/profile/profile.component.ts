import { Component } from '@angular/core';
import {HistoryContent, HistoryPageableInterface} from "../../interfaces/HistoryPageableInterface";
import {UserDetailsInterface} from "../../interfaces/UserDetailsInterface";
import {UserService} from "../services/user.service";
import {ActivatedRoute} from "@angular/router";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {faArrowLeft} from "@fortawesome/free-solid-svg-icons/faArrowLeft";
import {faArrowRight} from "@fortawesome/free-solid-svg-icons/faArrowRight";
import {UserInterface} from "../../interfaces/UserInterface";
import {dateComparator} from "@ng-bootstrap/ng-bootstrap/datepicker/datepicker-tools";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent {

  historyList !:HistoryContent[];
  userDetails !: UserDetailsInterface;

  userUpdate !:{ firstName: any; lastName: any; password: any; email: any; username: any;role:any };

  page = 0;
  size = 5;
  first:Boolean = false;
  last:Boolean = false;
  totalPages = 0;
  totalElements = 0;

  userForm : FormGroup = this.formBuilder.group({
    username:[''],
    firstName:['',[Validators.required]],
    lastName:['',[Validators.required]],
    email:['',[Validators.email]],
    password:['',[]],
    repeatPassword:['']
  });

  get username():any{
    return this.userForm.get('username')?.value
  }
  get firstname():any{
    return this.userForm.get('firstName')?.value
  }
  get lastname():any{
    return this.userForm.get('lastName')?.value
  }
  get email():any{
    return this.userForm.get('email')?.value
  }
  get password():any{
    return this.userForm.get('password')?.value
  }
  get repeatPassword():any{
    return this.userForm.get('repeatPassword')?.value
  }


  constructor(private userService: UserService, private router : ActivatedRoute , private formBuilder : FormBuilder) {
    this.loadHistory();
    this.loadUserDetails();

  }

  private loadHistory() {
    const id = this.router.snapshot.params['id']
    this.userService.getHistory(id , this.page , this.size).subscribe({
      next:data => {
        this.historyList = data.content;
        this.first = data.first;
        this.last = data.last;
        this.totalPages = data.totalPages;
        this.totalElements = data.totalElements;

      },
      error: error => {console.log(error);},
      complete:()=>console.log('loaded history')
    })
  }

  private loadUserDetails() {
    const id = this.router.snapshot.params['id']
    this.userService.getUserDetails(id).subscribe({
      next:data => {
        this.userDetails = data;
        this.userForm.get('username')?.setValue(data.username);
        this.userForm.get('firstName')?.setValue(data.firstName);
        this.userForm.get('lastName')?.setValue(data.lastName);
        this.userForm.get('email')?.setValue(data.email);

      },
      error: error => {console.log(error);},
      complete:()=>console.log('loaded userDetails')
    })

  }

  onSubmit() {
    this.userUpdate = {
      username : this.userForm.get('username')?.value,
      firstName: this.userForm.get('firstName')?.value,
      lastName: this.userForm.get('lastName')?.value,
      email: this.userForm.get('email')?.value,
      password: this.userForm.get('password')?.value,
      role:'USER'
    };
    this.userService.updateUserDetails(this.userUpdate.username,this.userUpdate).subscribe({
      next:data =>{
        console.log('user updated')
        this.loadUserDetails()
      },
      error:err => console.log(err),
      complete:()=>console.log('update done')
    })
  }

  protected readonly faArrowLeft = faArrowLeft;
  protected readonly faArrowRight = faArrowRight;

  toNext() {
    this.page += 1;
    this.loadHistory();

  }

  toPrevious() {
    this.page -= 1;
    this.loadHistory();
  }
}
