import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {InicalComponent} from "./components/inical/inical.component";
import {LoginComponent} from "./components/login/login.component";
import {UserRoleGuard} from "./guards/user-role.guard";

const routes: Routes = [
  {
    path:'' , redirectTo:"/inicio" , pathMatch:"full"
  },
  {
    path:'inicio', component:InicalComponent
  },
  {
    path:'login', component:LoginComponent
  },
  { path: 'user',
    loadChildren: () => import('./user/user.module').then(m => m.UserModule) ,
    canActivate:[UserRoleGuard] },

  {
    path:'**' , component:InicalComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
