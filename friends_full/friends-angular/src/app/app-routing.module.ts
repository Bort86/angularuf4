import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { HomeComponent } from './home/home.component';
import { FriendsComponent } from './friends/friends.component';
import { FriendDetailComponent } from './friend-detail/friend-detail.component';

const routes: Routes = [
      {
        path: 'home',
        component: HomeComponent
      },
      {
        path: 'friends',
        component: FriendsComponent
      },
      {
        path: 'friend-detail',
        component: FriendDetailComponent
      },
      {
        path: 'friend-detail/:id',
        component: FriendDetailComponent
      },
      {
        path: '**',
        redirectTo: '/home',
        pathMatch: 'full'
      }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
  providers: []
})
export class AppRoutingModule { }
