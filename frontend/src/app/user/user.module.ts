import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UserRoutingModule } from './user-routing.module';
import { UserComponent } from './user.component';
import { NewestMusicComponent } from './newest-music/newest-music.component';
import { MusicDetailComponent } from './music-detail/music-detail.component';
import {FontAwesomeModule} from "@fortawesome/angular-fontawesome";
import { AlbumSongComponent } from './album-song/album-song.component';
import { SongPaginationComponent } from './song-pagination/song-pagination.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { StarComponent } from './star/star.component';
import {NgbRating} from "@ng-bootstrap/ng-bootstrap";
import { MostPlayedComponent } from './most-played/most-played.component';
import { ForYouComponent } from './for-you/for-you.component';
import { UserProfileComponent } from './user-profile/user-profile.component';
import { ProfileComponent } from './profile/profile.component';


@NgModule({
  declarations: [
    UserComponent,
    NewestMusicComponent,
    MusicDetailComponent,
    AlbumSongComponent,
    SongPaginationComponent,
    StarComponent,
    MostPlayedComponent,
    ForYouComponent,
    UserProfileComponent,
    ProfileComponent
  ],
    imports: [
        CommonModule,
        UserRoutingModule,
        FontAwesomeModule,
        FormsModule,
        NgbRating,
        ReactiveFormsModule
    ]
})
export class UserModule { }
