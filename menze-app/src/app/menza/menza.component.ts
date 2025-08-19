import {Component, Input, OnInit} from '@angular/core';
import {Menza} from "../menza";
import {MenzaService} from "../menza.service";
import {Observable} from "rxjs";

@Component({
  selector: 'app-menza',
  templateUrl: './menza.component.html',
  styleUrls: ['./menza.component.css']
})
export class MenzaComponent{
  @Input() public menza: Menza | undefined;

  constructor(private menzaService: MenzaService) {
  }

  /*ngOnInit(): void{
    //this.getMenza();
  }
  public getMenza(): void{
    if(this.naziv!== undefined) {
      this.menzaService.getMenzaByNaziv(this.naziv).subscribe(menza => this.menza = menza);
    }
  }*/
}
