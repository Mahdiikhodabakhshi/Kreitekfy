import { Component } from '@angular/core';
import {SongInterface} from "../../interfaces/SongInterface";
import {UserService} from "../services/user.service";
import {ActivatedRoute} from "@angular/router";
import {faPlay} from "@fortawesome/free-solid-svg-icons/faPlay";
import {faHeart} from "@fortawesome/free-solid-svg-icons/faHeart";
import {LoginService} from "../../services/login.service";
import {UserSongInterface} from "../../interfaces/UserSongInterface";
import {SingleAlbumInterface, Song} from "../../interfaces/SingleAlbumInterface";
import {HistoryContent} from "../../interfaces/HistoryPageableInterface";

@Component({
  selector: 'app-music-detail',
  templateUrl: './music-detail.component.html',
  styleUrls: ['./music-detail.component.css']
})
export class MusicDetailComponent {

  songInfo !:SongInterface;
  songId? : number;
  albumSongs ?: SingleAlbumInterface;
  albumId !: number;
  // personalRate !:number;

  //chatgpt
  currentRating: number = 0;
  stars: number[] = [1, 2, 3, 4, 5]; // Total number of stars
  //chatgpt

  usersong :UserSongInterface={
    songId : this.songId!,
    userId : this.authService.userId()!,
    id: 0,
    personalPlays:0,
    personalValorations:0

  };

  userHistory:any ={
    songTitle :String,
    userId : this.authService.userId()!,
  }


  constructor(private userService:UserService , private route:ActivatedRoute , private authService:LoginService) {
    this.loadSong();

  }

  private loadSong() {
    const id = this.route.snapshot.params['id'];
    this.userService.getOneSong(id).subscribe({
      next:data => {
        this.songInfo = data;
        console.log(data)
        this.loadAlbumSongs();
        this.usersong.songId = data.id;
        this.userHistory.songTitle=data.title

      },
      error:err => console.log(err),
      complete:()=>console.log('loaded song')
    })
  }

  protected readonly faPlay = faPlay;
  protected readonly faHeart = faHeart;

  playMusic() {
   // this.usersong.personalPlays = this.usersong.personalPlays + 1;
    this.userService.updateUserSongView(this.usersong).subscribe({
      next:data => {
        this.usersong = data;
        this.songInfo!.totalViews++
        this.currentRating = data.personalValorations?data.personalValorations:0;
      },
      error:err => console.log(err),
      complete:()=>console.log('updated play song')
    })


    this.userService.postHistory(this.userHistory).subscribe({
      next:data => {console.log('music added to history')},
      error:err => console.log(err),
      complete:()=>console.log('done history song')
    })
    // songInfo.totalViews = songInfo.totalViews +1;
  }

  // private createUserSong() {
  //   if (this.authService.userId()){
  //
  //     this.userService.updateUserSongView(this.usersong).subscribe({
  //       next:data => {this.usersong = data;},
  //       error:err => console.log(err),
  //       complete:()=>console.log('usersong created'),
  //     })
  //
  //   }
  //
  // }

  private loadAlbumSongs() {
    this.albumId = this.songInfo.albumId
    console.log(this.albumId);

    if (this.albumId){
      this.userService.getSongsOfAlbum(this.albumId).subscribe({
        next:data => {
          console.log(data);
          this.albumSongs = data
        },
        error:err => console.log(err),
        complete:()=>console.log('albumSongs loaded'),
      })
    }

  }

  updateRating( index: number): void {
    this.currentRating = index + 1; // Update current rating
    this.usersong.personalValorations = this.currentRating;
    this.userService.updateUserSongRate(this.usersong).subscribe({
      next:value => {
        this.usersong = value
        this.currentRating = value.personalValorations?value.personalValorations:0;

      },
      error:err => console.log(err),
      complete:()=>console.log('updated rating')
    })
  }

    // addRate($event: number) {
    //   this.usersong.personalValorations = $event;
    //   this.userService.updateUserSongRate(this.usersong).subscribe({
    //     next:data => {
    //       this.personalRate = data.personalValorations?data.personalValorations:0
    //     },
    //     error:err => console.log(err),
    //     complete:()=>console.log('added valoration')
    //   })
    // }
}
