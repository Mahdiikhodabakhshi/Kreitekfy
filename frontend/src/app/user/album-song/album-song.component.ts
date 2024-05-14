import {Component, Input} from '@angular/core';
import {Router} from "@angular/router";
import {UserService} from "../services/user.service";
import {SingleAlbumInterface, Song} from "../../interfaces/SingleAlbumInterface";
import {faPlay} from "@fortawesome/free-solid-svg-icons/faPlay";

@Component({
  selector: 'app-album-song',
  templateUrl: './album-song.component.html',
  styleUrls: ['./album-song.component.css']
})
export class AlbumSongComponent {
  @Input() albumId?: number;
  @Input() albumSongs ?:Song[];

  constructor(private userService: UserService, private router: Router) {
    console.log(this.albumId)
    console.log(this.albumSongs);
  }


  protected readonly faPlay = faPlay;


}
