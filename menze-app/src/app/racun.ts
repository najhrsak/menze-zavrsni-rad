import {Jelo} from "./jelo";

export interface Racun {
  uuid: string;
  menza: string;
  jela: Jelo[];
  suma: number;
  blagajna: string;
  vrijemeIzdavanja: Date;
}
