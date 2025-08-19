import {Component, numberAttribute, OnInit} from '@angular/core';
import {Jelo} from "../jelo";
import {MenzaService} from "../menza.service";
import {Racun} from "../racun";
import {Observable} from "rxjs";

@Component({
  selector: 'app-racun',
  templateUrl: './racun.component.html',
  styleUrls: ['./racun.component.css']
})
export class RacunComponent implements OnInit{
  jela: Jelo[] | undefined;
  racun: Racun | undefined;
  izdan: boolean = false;


  constructor(private menzaService: MenzaService) {
  }
  ngOnInit() {
    this.getJela();
    this.getRacun();
  }

  getRacun(): void{
    if(this.racun == undefined){
      this.newRacun();
    }
  }
  newRacun(){
    this.menzaService.newRacun().subscribe(racun=>{
      this.racun = racun;
    })
  }

  getJela(){
    this.menzaService.getJela().subscribe(jela => {
        this.jela = jela;
      }
    )
  }

  addJelo(jelo: Jelo, kolicina: number){
    this.menzaService.addJeloToRacun(jelo, this.racun?this.racun:undefined, kolicina).subscribe(
      racun => {
        this.racun = racun;
      }
    )
  }

  izdajRacun(){
    this.izdan = true;
    this.menzaService.izdajRacun(this.racun?this.racun:undefined).subscribe(
      racun =>{
        this.racun = racun;
      }
    )
  }

  noviRacun(){
    this.racun = undefined;
    this.izdan = false
    this.ngOnInit();
  }
}
