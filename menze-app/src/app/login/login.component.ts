import {Component, OnInit} from '@angular/core';
import { User } from "../user";
import { UserService } from "../user.service";
import {Blagajna} from "../blagajna";
import {FormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import {CommonModule} from "@angular/common";
import {ActivatedRoute, Router} from "@angular/router";
import {AppComponent} from "../app.component";

@Component({
  selector: 'app-login',
  standalone: true,
  imports:[FormsModule,
    HttpClientModule,CommonModule
  ],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit{
  public blagajna: Blagajna | undefined;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private userService: UserService,
              private appComp: AppComponent) {
  }

  login(username: string, password: string) {
      username = username.trim();
      password = password.trim();
      if(!username || !password){
        return;
      }
      this.userService.login({username, password} as User).subscribe(
        user => {
            this.blagajna = user;
            this.appComp.loggedIn();
            this.gotoInfo();
          },
      );
  }

  gotoInfo(){
    this.router.navigate(['/info'])
  }

  logout(){
    this.blagajna = undefined;
    this.appComp.loggedOut();
  }

  ngOnInit(): void {
    if(this.appComp.isLoggedIn)
    {
      this.logout();
      this.router.navigate(['/menze/nazivi'])
    }
  }
}
