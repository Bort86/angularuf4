import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute, Params }   from '@angular/router';
import { Location }                 from '@angular/common';

import 'rxjs/add/operator/switchMap';

import { FriendService } from '../services/friend.service';
import { Friend } from '../shared/friend';

@Component({
  moduleId: module.id,
  selector: 'app-friend-detail',
  templateUrl: './friend-detail.component.html',
  styleUrls: ['./friend-detail.component.css']
})
export class FriendDetailComponent implements OnInit {
  @Input()
  public friend: Friend;

  constructor(
    private friendService: FriendService,
    private route: ActivatedRoute,
    private location: Location
  ) { }

  ngOnInit(): void {
    // switchMap: switch to new search observable each time the term changes
    // switchMap is usually used when you have some async operation that is triggered by some prepended "event/stream".
    // In your case this means, that as soon as the route-params change,
    //    your friend-service is automatically called again with the changed params and the previous call is canceled
    //    so you won't receive outdated data.
    this.route.params
      .switchMap((params: Params) => this.friendService.getFriend(params['id']))
      .subscribe(friend => this.friend = friend);
  }

    goBack(): void {
      this.location.back();
    }

    save(): void {
      this.friendService.update(this.friend)
        .subscribe(() => {this.goBack()});
    }

    create(): void {
      this.friendService.insert(this.friend)
        .subscribe(() => {this.goBack()});
    }

}
