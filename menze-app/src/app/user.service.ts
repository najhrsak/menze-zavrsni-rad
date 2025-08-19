import { Injectable } from '@angular/core';
import { User } from "./user";
import {catchError, Observable, of, tap} from "rxjs";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Blagajna} from "./blagajna";

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private userURL = "http://localhost:8088/menze/login";
  httpOptions = {
    headers:new HttpHeaders({'Content-Type': 'application/json'})
  }
  constructor(private http: HttpClient) { }

  private handleError<T>(operation = 'operation', result?: T){
    return (error:any): Observable<T> => {
      console.error(operation);
      console.error(error);
      return of(result as T);
    };
  }

  login(user: User): Observable<Blagajna>{
    return this.http.post<Blagajna>(this.userURL, user).pipe(
      tap(_ => console.log('fetched user')),
      catchError(this.handleError<Blagajna>('login', undefined))
    );
  }
}
