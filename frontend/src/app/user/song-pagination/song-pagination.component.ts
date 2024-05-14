import { Component } from '@angular/core';
import {Content} from "../../interfaces/SongPageableInterface";
import {UserService} from "../services/user.service";
import {faFilter} from "@fortawesome/free-solid-svg-icons/faFilter";

@Component({
  selector: 'app-song-pagination',
  templateUrl: './song-pagination.component.html',
  styleUrls: ['./song-pagination.component.css']
})
export class SongPaginationComponent {
  songInfo !: Content[]

  page = 0;
  size = 8;
  first:Boolean = false;
  last:Boolean = false;
  totalPages = 0;
  totalElements = 0;


  categoryFilter?:String ;
  titleFilter?:String ;
  artistFilter?:String ;
  albumFilter?:String ;
  title: string = 'Todas las canciones';


  constructor(private userService: UserService)  {
    this.loadSongs();
  }

  private loadSongs() {
    const filters:string | undefined = this.buildFilters();
    this.userService.songsPageable(this.page,this.size,filters).subscribe({
      next:value => {
        console.log(value);
        this.songInfo = value.content ;
        this.first = value.first;
        this.last = value.last;
        this.totalPages = value.totalPages;
        this.totalElements = value.totalElements;
      },
      error:err => console.log(err),
      complete:()=>console.log('loaded song')
    })

  }

  toPreviousPage() {
    this.page -=1
    this.loadSongs()
  }

  toNextPage() {
    this.page +=1
    this.loadSongs()
  }

  protected readonly faFilter = faFilter;

  cleanSearch() {
    this.categoryFilter = "";
    this.albumFilter= "";
    this.artistFilter = "";
    this.titleFilter = '';

  }

  searchByFilters() {
    this.loadSongs();

  }

  private buildFilters():string|undefined {
    const filters:string[] = [];

    if (this.categoryFilter){
      filters.push("category:MATCH:"+this.categoryFilter);
    }
    if (this.albumFilter){
      filters.push("album:MATCH:"+this.albumFilter);
    }
    if (this.artistFilter){
      filters.push("artists:MATCH:"+this.artistFilter);
    }
    if (this.titleFilter){
      filters.push("title:MATCH:"+this.titleFilter);
    }


    if(filters.length > 0){
      let globalFilters:string = "";
      for (let filter of filters){
        globalFilters = globalFilters + filter + ",";
      }
      globalFilters = globalFilters.substring(0, globalFilters.length-1);
      return globalFilters;
    }else return undefined;



  }
}
