import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserComponent } from './user.component';
import {MusicDetailComponent} from "./music-detail/music-detail.component";
import {SongPaginationComponent} from "./song-pagination/song-pagination.component";
import {ProfileComponent} from "./profile/profile.component";

const routes: Routes = [
  { path: '', component: UserComponent },
  {path:'song/:id' , component:MusicDetailComponent},
  {
    path:'songs-pageable' , component:SongPaginationComponent
  },
  {
    path:'profile/:id' , component:ProfileComponent
  }


];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UserRoutingModule { }
