import {Component, OnInit} from '@angular/core';
import {SongInterface} from "../interfaces/SongInterface";
import {UserService} from "./services/user.service";
import {CategoryInterface} from "../interfaces/CategoryInterface";
import {LoginService} from "../services/login.service";
import {faUser} from "@fortawesome/free-solid-svg-icons/faUser";

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit{

  newestMusicsTitle = 'novedades';
  mostPlayedSongsTitle = 'lo más sonado'
  forYouSongsTitle = 'para tí';
  newestSong !: SongInterface[];
  mostPlayed !: SongInterface[];
  forYouSongs !: SongInterface[];
  filterCategory :string | undefined=undefined;
  userId !:string|null;


  categories ?: CategoryInterface[] ;





  constructor(private userService:UserService , private authService:LoginService) {

  }

  ngOnInit(): void {
    this.userId = this.authService.userId();
    this.loadCategories();
    this.loadNewestSongs();
    this.mostPlayedSongs();
    this.loadForYou();
  }



  private loadCategories() {


    this.userService.getCategories().subscribe({
      next: data => {
        this.categories = data
      },
      error:err => console.log(err),
      complete:()=>console.log('category found'),
    })
  }


  private loadNewestSongs() {

    console.log(this.filterCategory + 'from user components')

    this.userService.newestSongs(this.filterCategory).subscribe({
      next:data =>this.newestSong = data,
      error:err => console.log(err),
      complete:()=>console.log('loaded newest songs')
    })
  }

  private mostPlayedSongs() {
    this.userService.mostPlayedSongs(this.filterCategory).subscribe({
      next:data => {this.mostPlayed = data},
      error:err => console.log(err),
      complete:()=>console.log('mostPlayed songs loaded')
    })

  }

  private loadForYou() {
    this.userService.forYouSongs().subscribe({
      next:data => {
        this.forYouSongs = data
      },
      error:err => console.log(err),
      complete:()=>console.log('forYouSongs loaded')
    })
  }


  filterChange() {
    this.loadNewestSongs();
    this.mostPlayedSongs()

  }

  protected readonly faUser = faUser;
}
