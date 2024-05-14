import {Component, Input} from '@angular/core';
import {SongInterface} from "../../interfaces/SongInterface";

@Component({
  selector: 'app-for-you',
  templateUrl: './for-you.component.html',
  styleUrls: ['./for-you.component.css']
})
export class ForYouComponent {
  @Input() title?:string;
  @Input() forYouSongsFromParent?:SongInterface[];
}
