import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {catchError, Observable, of, tap} from "rxjs";
import {Menza} from "./menza";
import {Jelo} from "./jelo";
import {Racun} from "./racun";

@Injectable({
  providedIn: 'root'
})
export class MenzaService {

  private menzeURL = 'http://localhost:8088/menze';
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  }
  constructor(private http: HttpClient) { }

  private handleError<T>(operation = 'operation', result?: T){
    return (error:any): Observable<T> => {
      console.error(operation);
      console.error(error);
      return of(result as T);
    };
  }

  getNazivi(): Observable<Menza[]>{
    return this.http.get<Menza[]>(this.menzeURL + '/nazivi').pipe(
      tap(_ => console.log('fetched nazivi')),
      catchError(this.handleError<Menza[]>('nazivi', [])));
  }

  getMenzaByNaziv(naziv :String): Observable<Menza[]>{
    return this.http.get<Menza[]>(this.menzeURL+'/menza/Stjepan Radic, restoran 2').pipe(
      tap(_ => console.log('fetched menza by its name')),
      catchError(this.handleError<Menza[]>('menza/{naziv}', []))
    );
  }

  getCurrentMenza(): Observable<Menza>{
    return this.http.get<Menza>(this.menzeURL+'/trenutna-menza').pipe(
      tap(_ => console.log('fetched currently logged in menza')),
      catchError(this.handleError<Menza>('menza/{naziv}',  undefined))
    );
  }

  updateMenza(menza: Menza | undefined): Observable<any>{
    // @ts-ignore
    return this.http.put(this.menzeURL+'/update/'+ menza.id, menza).pipe(
      tap(_ => console.log('updated menza')),
      catchError(this.handleError<any>('updateMenza'))
    );
  }

  getJela(): Observable<any>{
    return this.http.get(this.menzeURL+'/jela').pipe(
      tap(_ => console.log('fetched jela')),
      catchError(this.handleError<any>('getJela'))
    );
  }

  newRacun(): Observable<Racun>{
    return this.http.get<Racun>(this.menzeURL + '/novi-racun').pipe(
      tap(_ => console.log('created new racun')),
      catchError(this.handleError<Racun>('novi-racun', undefined))
    );
  }

  addJeloToRacun(jelo: Jelo, racun: Racun | undefined, kolicina: number): Observable<Racun>{
    // @ts-ignore
    return this.http.get<Racun>(this.menzeURL+'/dodaj-jelo/' + racun.uuid + ',' + jelo.uuid + ',' + kolicina).pipe(
      tap(_ => console.log('Added a jelo to the racun')),
      catchError(this.handleError<Racun>('menza/dodaj-jelo', undefined))
    );
  }

  izdajRacun(racun: Racun | undefined): Observable<Racun>{
    // @ts-ignore
    return this.http.get<Racun>(this.menzeURL + '/izdaj-racun/' + racun.uuid).pipe(
      tap(_ => console.log('Izdan je racun')),
      catchError(this.handleError<Racun>('menza/izdaj-racun', undefined))
    );
  }

  setGuzva(menza: Menza | undefined, number: Number): Observable<any>{
    if(number == 0)
      { // @ts-ignore
        return this.http.put(this.menzeURL + '/odjavi-guzvu/' + menza.id).pipe(
                tap(_ => console.log('guzva odjavljena')),
                catchError(this.handleError<any>('odjaviGuzvu'))
              );
      }
    else
    { // @ts-ignore
      return this.http.put(this.menzeURL + '/prijavi-guzvu/' + menza.id).pipe(
        tap(_ => console.log('guzva prijavljena')),
        catchError(this.handleError<any>('prijaviGuzvu'))
      );
    }

  }
}

