import { Component } from '@angular/core';
import {faSpotify} from "@fortawesome/free-brands-svg-icons/faSpotify";
import {LoginService} from "../../services/login.service";
import {Router} from "@angular/router";
import {UserService} from "../../user/services/user.service";
import {CategoryInterface} from "../../interfaces/CategoryInterface";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent {

  userId?:string | null;

  // categories ?: CategoryInterface[] ;



  constructor(

    private authService:LoginService ,
    private router: Router ,
    private userService:UserService)   {


    // this.userId = this.authService.userId();
    // this.loadCategories();
    //
    // this.filterChange();

    // this.filterForm.valueChanges.subscribe(value => {
    //   this.userService.setFilter(value.dropdown);
    //
    // });

  }

  protected readonly faSpotify = faSpotify;



  filterStyle: string='';





  logout() {
    console.log(this.authService.logout())
    this.authService.logout();
    this.userId=null
    this.router.navigate(['/init'])


  }

  protected isLogged():boolean {

    return  !!this.authService.userId()
  }



  // private loadCategories() {
  //
  //
  //   this.userService.getCategories().subscribe({
  //     next: data => {
  //       this.categories = data
  //     },
  //     error:err => console.log(err),
  //     complete:()=>console.log('category found'),
  //   })
  // }




  // filterChange() {
  //   console.log(this.filterStyle + ' from navbar')
  //   if (this.filterStyle !=null){
  //     this.userService.setFilter(this.filterStyle)
  //   }
  // }
}
