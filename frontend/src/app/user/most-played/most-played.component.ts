import {Component, Input} from '@angular/core';
import {SongInterface} from "../../interfaces/SongInterface";

@Component({
  selector: 'app-most-played',
  templateUrl: './most-played.component.html',
  styleUrls: ['./most-played.component.css']
})
export class MostPlayedComponent {

  @Input() title?:string;
  @Input() mostPlayedSongsFromParent?:SongInterface[];

}
