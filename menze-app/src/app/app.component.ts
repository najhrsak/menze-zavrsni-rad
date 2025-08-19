import { Component } from '@angular/core';
import {MenzaService} from "./menza.service";
import {Menza} from "./menza";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'menze-app';
  public isLoggedIn: boolean = false;
  public loggedIn(){
    this.isLoggedIn = true;
  }
  public loggedOut(){
    this.isLoggedIn = false;
  }


}
