import {Component, Input} from '@angular/core';
import {SongInterface} from "../../interfaces/SongInterface";

@Component({
  selector: 'app-newest-music',
  templateUrl: './newest-music.component.html',
  styleUrls: ['./newest-music.component.css']
})
export class NewestMusicComponent {

  @Input() title?:string;
  @Input() newestSongsFromParent?:SongInterface[];

}
