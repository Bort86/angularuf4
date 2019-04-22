import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';

import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { FriendsComponent } from './friends/friends.component';
import { FriendService } from './services/friend.service';
import { MessageService } from './shared/services/message.service';
import { FriendDetailComponent } from './friend-detail/friend-detail.component';

//Pagination
import { NgxPaginationModule } from 'ngx-pagination';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    FriendsComponent,
    FriendDetailComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule,
    NgxPaginationModule //pagination
  ],
  providers: [FriendService, MessageService],
  bootstrap: [AppComponent]
})
export class AppModule { }
