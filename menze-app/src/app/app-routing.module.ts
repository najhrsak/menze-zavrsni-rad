import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from "./login/login.component";
import {NaziviComponent} from "./nazivi/nazivi.component";
import {MenzaComponent} from "./menza/menza.component";
import {InfoComponent} from "./info/info.component";
import {RacunComponent} from "./racun/racun.component";

const routes: Routes = [
  {path:'login', component: LoginComponent},
  {path:'menze/nazivi', component: NaziviComponent},
  {path:'info', component: InfoComponent},
  {path:'izdaj-racun', component:RacunComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
