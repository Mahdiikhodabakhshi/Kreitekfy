import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {BehaviorSubject, Observable} from "rxjs";
import {SongInterface} from "../../interfaces/SongInterface";
import {UserSongInterface} from "../../interfaces/UserSongInterface";
import {SingleAlbumInterface} from "../../interfaces/SingleAlbumInterface";
import {SongPageableInterface} from "../../interfaces/SongPageableInterface";
import {CategoryInterface} from "../../interfaces/CategoryInterface";
import {LoginService} from "../../services/login.service";
import {HistoryContent, HistoryPageableInterface} from "../../interfaces/HistoryPageableInterface";
import {UserDetailsInterface} from "../../interfaces/UserDetailsInterface";
import {UserInterface} from "../../interfaces/UserInterface";

@Injectable({
  providedIn: 'root'
})
export class UserService {
  BASE_URL = "http://localhost:8080/api";
  // private filterSubject: string | undefined = undefined;
  //
  // setFiler(filter:string){
  //   this.filterSubject = filter;
  //   console.log(this.getFilter() + ' from user service')
  //
  // }
  //
  // getFilter():string|undefined{
  //   return this.filterSubject;
  //
  // }

  // private filterSubject: BehaviorSubject<string> = new BehaviorSubject<string>('');
  // public filter$: Observable<string> = this.filterSubject.asObservable();
  //
  //
  // setFilter(value: string): void {
  //
  //     this.filterSubject.next(value);
  //
  //   console.log(this.filterSubject , this.filter$)
  // }
  //
  // getFilter(): Observable<string> {
  //   return this.filter$;
  // }



  constructor(private http:HttpClient , private authService:LoginService) {

  }

  newestSongs(category?: string | undefined):Observable<SongInterface[]> {
    let  newUrl = this.BASE_URL+'/songs/newest'
    if (category ){
      newUrl = newUrl+'?category='+category
    }
    return this.http.get<SongInterface[]>(newUrl)
  }

  getOneSong(id:number):Observable<SongInterface>{
    return this.http.get<SongInterface>(this.BASE_URL+'/songs/'+id)
  }

  postUserSong(userSong:UserSongInterface):Observable<UserSongInterface>{
    return this.http.post<UserSongInterface>(this.BASE_URL+'/user-music',userSong)
  }

  updateUserSongView(userSong:UserSongInterface):Observable<UserSongInterface>{
    return this.http.put<UserSongInterface>(this.BASE_URL+'/user-music/view',userSong)
  }

  updateUserSongRate(userSong:UserSongInterface):Observable<UserSongInterface>{
    return this.http.put<UserSongInterface>(this.BASE_URL+'/user-music/rating',userSong)
  }

  getSongsOfAlbum(albumId:number):Observable<SingleAlbumInterface> {
    return this.http.get<SingleAlbumInterface>(this.BASE_URL+'/albums/'+albumId)
  }

  songsPageable(page:number , size:number , filters?:string):Observable<SongPageableInterface>{
    let newUrl = this.BASE_URL+"/songs/pageable?page="+page+"&size="+size
    if (filters){
      newUrl = newUrl + "&filter="+filters
    }
    return this.http.get<SongPageableInterface>(newUrl);
  }

  getCategories():Observable<CategoryInterface[]>{
    return this.http.get<CategoryInterface[]>(this.BASE_URL+'/category');
  }

  mostPlayedSongs(category?: string | undefined):Observable<SongInterface[]> {
    let  newUrl = this.BASE_URL+'/songs/top-view'
    if (category ){
      newUrl = newUrl+'?categoryName='+category
    }
    return this.http.get<SongInterface[]>(newUrl)
  }


  forYouSongs():Observable<SongInterface[]> {
    const userId = this.authService.userId();
    let  newUrl = this.BASE_URL+'/songs/recommend/'+userId;
    return this.http.get<SongInterface[]>(newUrl)
  }

  getHistory(id:string,page:number,size:number):Observable<HistoryPageableInterface> {
    const newUrl = this.BASE_URL+'/user-history?filter=user:EQUAL:'+id+'&page='+page+"&size="+size;
    return this.http.get<HistoryPageableInterface>(newUrl)
  }

  postHistory(userHistory:HistoryContent):Observable<any> {
    const newUrl = this.BASE_URL+'/user-history';
    return this.http.post<any>(newUrl,userHistory)
  }

  getUserDetails(id:string):Observable<UserDetailsInterface>{
    return this.http.get<UserDetailsInterface>(this.BASE_URL+'/userDetails/'+id);
  }

  updateUserDetails(id:string , user:UserInterface):Observable<UserDetailsInterface>{
    return this.http.patch<UserDetailsInterface>(this.BASE_URL+'/userDetails/'+id , user);
  }


}
