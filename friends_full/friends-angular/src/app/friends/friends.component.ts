import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { FriendService } from '../services/friend.service';
import { Friend } from '../shared/friend';
import { Response } from '@angular/http';

@Component({
  moduleId: module.id,
  selector: 'app-friends',
  templateUrl: './friends.component.html',
  styleUrls: ['./friends.component.css']
})
export class FriendsComponent implements OnInit {

  public friends: Friend[];
  public selectedFriend: Friend;
  public friendsFiltered: Friend[];

  //Pagination properties
  public itemsPerPage: number;
  public currentPage: number;
  public totalItems: number;

  //Filter properties
  public nameFilter: string;
  public bornDateFilter: string;
  public ageFilter: number;

  constructor(
    private router: Router,
    private friendService: FriendService
  ) { }

  ngOnInit() {
    this.getFriendsO();
    this.itemsPerPage = 5;
    this.currentPage = 1;
    this.friendsFiltered = this.friends;
    this.ageFilter = 100;

  }

  getFriends(): void {
    this.friendService
      .getAllFriends()
      .subscribe(friends => this.friends = friends);
  }

  getFriendsO(): void {
    this.friendService
      .getAllFriends()
      .subscribe(friends => this.friends = friends);
    this.friendService
      .getAllFriends()
      .subscribe(friends => this.friendsFiltered = friends)
  }

  onSelect(friend: Friend): void {
    this.selectedFriend = friend;
  }

  gotoDetail(): void {
    this.router.navigate(['/friend-detail', this.selectedFriend.phone]);
  }


  /*
  add(s_phone: string, s_name: string, s_age: string): void {
    const phone = s_phone.trim();
    const name = s_name.trim();
    const st_age = s_age.trim();
    if ((!phone) || (!name) || (!st_age)) { return; }
    const age = parseInt(st_age, 10);
    this.friendService
      .insert(new Friend(phone, name, age))
      .subscribe(friend => {
        this.friends.push(friend);
        this.selectedFriend = null;
      });
  }*/

  delete(friend: Friend): void {
    this.friendService
        .delete(friend)
        .subscribe(() => {
          this.friends = this.friends.filter(e => e !== friend);
          if (this.selectedFriend === friend) { this.selectedFriend = null; }
        });
  }

  filter():void{

    this.friendsFiltered = this.friends.filter(
      friend => {
        let ageValid = false;
        let nameValid = false;
        let dateValid = false;

        ageValid = friend.age <= this.ageFilter;

        if(this.nameFilter && this.nameFilter !="") {
          nameValid = friend.name.toLowerCase().indexOf(this.nameFilter.toLowerCase())!=-1;

        } else {
          nameValid=true;
        }
        return (nameValid && ageValid)
      }
    );
  }

}
