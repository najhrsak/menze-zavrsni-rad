import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import {HttpClient, HttpClientModule} from "@angular/common/http";
import { MenzaComponent } from './menza/menza.component';
import { NaziviComponent } from './nazivi/nazivi.component';
import {Ng2SearchPipe, Ng2SearchPipeModule} from "ng2-search-filter";
import {FormsModule} from "@angular/forms";
import { InfoComponent } from './info/info.component';
import { RacunComponent } from './racun/racun.component';


export function HttpLoaderFactory(http: HttpClient){}

@NgModule({
  declarations: [
    AppComponent,
    MenzaComponent,
    NaziviComponent,
    InfoComponent,
    RacunComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
