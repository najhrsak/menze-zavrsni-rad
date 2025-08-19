import {Component, OnInit} from '@angular/core';
import {Menza} from "../menza";
import {MenzaService} from "../menza.service";

@Component({
  selector: 'app-info',
  templateUrl: './info.component.html',
  styleUrls: ['./info.component.css']
})
export class InfoComponent implements OnInit{
  public menza: Menza | undefined;
  public isGuzva: boolean = false;

  constructor(private menzaService: MenzaService) {
  }
  ngOnInit() {
    this.getMenza();
  }
  getMenza(): void{
    this.menzaService.getCurrentMenza().subscribe(menza => {
      this.menza = menza;
      console.log("Menza ==> ", this.menza);
    });
  }
  edit(info: string, radnoVrijeme: string, meni:string): void{
    // @ts-ignore
    this.menza.info=info.trim();
    // @ts-ignore
    this.menza.radnoVrijeme = radnoVrijeme.trim();
    // @ts-ignore
    this.menza.meni =meni.trim();
    this.menzaService.updateMenza(this.menza).subscribe(menza=> this.menza = menza);
  }

  public jeGuzva(){
    this.isGuzva = true;
  }
  public nijeGuzva(){
    this.isGuzva = false;
  }

  setGuzva(number: number) {
    this.menzaService.setGuzva(this.menza, number).subscribe(menza=>{
      this.menza = menza;
    });
    if(number == 1)
      this.jeGuzva()
    else
      this.nijeGuzva()

  }
}
