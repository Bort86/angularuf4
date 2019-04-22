import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';

import { catchError, map, tap } from 'rxjs/operators';

import { MessageService } from '../shared/services/message.service';

import { Friend } from '../shared/friend';

import { FRIENDS } from './mock-friends';
import { Observable, of } from 'rxjs';

@Injectable()
export class FriendService {
  private server: string = 'http://localhost:8080';
  private serverApp = '/api-friends';
  private servicesPath = '/services';
  private serviceName = '/friend-service';
  private body: any;
  private wsUrl: string;

  constructor(private http: HttpClient, private messageService: MessageService ) {
    //this.wsUrl = 'http://localhost:8080/api-friends/services/friend-service';
    this.wsUrl = this.server + this.serverApp + this.servicesPath + this.serviceName;
  }

  getAllFriends(): Observable<any> {
    const url = `${this.wsUrl}/friends`;
    return this.http.get(url)
    .pipe(catchError(this.handleError<any>('getAllFriends', [])));
  }


   getFriend(phone: string): Observable<Friend> {
     // return this.getAllFriends()
     //      .then(friends => friends.find(friend => friend.phone === phone));
     const url = `${this.wsUrl}/friend/${phone}`;
     return this.http.get<Friend>(url);
     //.pipe(catchError(this.handleError))
   }

   insert(friend: Friend): Observable<Friend> {
     const url = `${this.wsUrl}/friend/add`;
     // set query parameters from form data

     let httpParams = new HttpParams()
       .append('phone', friend.phone)
       .append('name', friend.name)
       .append('surnames', friend.surnames)
       .append('age', friend.age.toString())
       .append('bornDate', friend.bornDate)
       .append('email', friend.email);


     // configure headers for form data.
     let httpHeaders : HttpHeaders = new HttpHeaders();

     httpHeaders.set('Content-Type',
     "application/x-www-form-urlencoded;charset=UTF-8");


     // send request
     return this.http
       .post<Friend>(url, httpParams, {headers: httpHeaders})
       .pipe(catchError(this.handleError<any>('insert', [])));
       //.pipe(catchError(this.handleError));
   }

   update(friend: Friend): Observable<Friend> {
     const url = `${this.wsUrl}/friend/${friend.phone}/update`;
     // set query parameters from form data
     let httpParams = new HttpParams()
       .append('phone', friend.phone)
       .append('name', friend.name)
       .append('surnames', friend.surnames)
       .append('age', friend.age.toString())
       .append('bornDate', friend.bornDate)
       .append('email', friend.email);

     // configure headers for form data.
     let httpHeaders : HttpHeaders = new HttpHeaders();

     httpHeaders.set('Content-Type',
     "application/x-www-form-urlencoded;charset=UTF-8");

     // send request
     return this.http
       .post<Friend>(url, httpParams, {headers: httpHeaders })
       .pipe(catchError(this.handleError<any>('update', [])));
   }


   delete(friend: Friend): Observable<any> {
    const url = `${this.wsUrl}/friend/${friend.phone}/delete`;
    // set query parameters from form data
    const body = new HttpParams();
    // send request
    return this.http.post(url, body)
    .pipe(catchError(this.handleError<any>('delete', [])));;
  }

  /**
 * Handle Http operation that failed.
 * Let the app continue.
 * @param operation - name of the operation that failed
 * @param result - optional value to return as the observable result
 */
private handleError<T> (operation = 'operation', result?: T) {
  return (error: any): Observable<T> => {

    // TODO: send the error to remote logging infrastructure
    console.error(error); // log to console instead

    // TODO: better job of transforming error for user consumption
    this.log(`${operation} failed: ${error.message}`);

    // Let the app keep running by returning an empty result.
    return of(result as T);
  };
}

/** Log a FriendService message with the MessageService */
private log(message: string) {
  this.messageService.add(`HeroService: ${message}`);
}

}
