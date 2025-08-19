import {Component, Directive, OnInit} from '@angular/core';
import {MenzaService} from "../menza.service";
import {MenzaComponent} from "../menza/menza.component";
import {Menza} from "../menza";
import {Observable} from "rxjs";
import {NgModel} from "@angular/forms";

@Component({
  selector: 'app-nazivi',
  templateUrl: './nazivi.component.html',
  styleUrls: ['./nazivi.component.css']
})

export class NaziviComponent implements OnInit{
  public menze: Menza[] | undefined;
  public selectedMenza: Menza | undefined;
  private sveMenze: Menza[] | undefined;

  constructor(private menzaService: MenzaService) {
  }

  ngOnInit(): void{
    this.getNazivi();
  }

  getNazivi(): void{
    this.menzaService.getNazivi().subscribe(menze => {
      this.sveMenze = menze;
      this.menze = menze;
    });
  }

  onSelect(menza: Menza): void{
    this.selectedMenza = menza;
  }

  searchText: string = '';
  textChange(){
    this.menze = this.sveMenze?.filter((val) => val.naziv.toLowerCase().includes(this.searchText.toLowerCase()))
  }

}
