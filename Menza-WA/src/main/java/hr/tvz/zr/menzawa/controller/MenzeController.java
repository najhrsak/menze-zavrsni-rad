package hr.tvz.zr.menzawa.controller;

import hr.tvz.zr.menzawa.model.*;
import hr.tvz.zr.menzawa.service.MenzaLoginService;
import hr.tvz.zr.menzawa.service.MenzeService;
import hr.tvz.zr.menzawa.service.RacunService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("menze")
public class MenzeController {
    MenzeService menzeService;
    MenzaLoginService menzaLoginService;
    RacunService racunService;

    //dohvacanje liste svih menzi
    @GetMapping("nazivi")
    public List<Menza> nazivi(){
        return menzeService.getNaziviMenza();
    }

    //dohvacanje jedne menze preko njenog naziva
    @GetMapping("naziv/{naziv}")
    public ResponseEntity<Menza> menzaByName(@PathVariable String naziv){
        return menzeService.getMenzaByNaziv(naziv).map(menza -> ResponseEntity.status(HttpStatus.FOUND).body(menza))
                .orElseGet(()->ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("login")
    public ResponseEntity<Blagajna> menzaLogin(@RequestBody MenzaLoginRequest menzaLoginRequest){

        return menzaLoginService.login(menzaLoginRequest).map(Blagajna -> ResponseEntity.status(HttpStatus.FOUND).body(Blagajna))
               .orElseGet(()->ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    //azuriranje podataka menze
    @PutMapping("update/{uuid}")
    public ResponseEntity<Menza> update(@PathVariable("uuid") UUID uuid, @RequestBody @Valid MenzaDTO menza){
        return menzeService.update(uuid, menza).map(Menza -> ResponseEntity.status(HttpStatus.OK).body(Menza))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    //dohvacanje menze preko njenog ID-a
    @GetMapping("{uuid}")
    public ResponseEntity<Menza> menzaByID(@PathVariable("uuid") UUID uuid){
        return menzeService.getMenzaByUUID(uuid).map(Menza -> ResponseEntity.status(HttpStatus.FOUND).body(Menza))
                .orElseGet(()->ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    //kreiranje novog racuna, samo id, menza i blagajna
    @GetMapping("novi-racun")
    public ResponseEntity<Racun> createRacun(){
        if(menzaLoginService.currentlyLoggedIn().isPresent())
            return racunService.newRacun(menzaLoginService.currentlyLoggedIn().get())
                    .map(Racun -> ResponseEntity.status(HttpStatus.CREATED).body(Racun))
                    .orElseGet(()->ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
        return ResponseEntity.status(HttpStatus.LOCKED).build();
    }



    @GetMapping("trenutna-menza")
    public Menza getCurrent(){
        if(menzaLoginService.currentlyLoggedIn().isPresent()) {
            Blagajna blagajna = menzaLoginService.currentlyLoggedIn().get();
            return blagajna.getMenza();
        }
        return null;
    }

    //izdavanje kreiranog racuna, racunanje sume ispis liste jela i dodavanje vremena izdavanja
    @GetMapping("izdaj-racun/{uuid}")
    public ResponseEntity<RacunDTO> getRacun(@PathVariable("uuid") UUID uuid){
        return racunService.getRacunByID(uuid).map(Racun -> ResponseEntity.status(HttpStatus.OK).body(convertRacunToDTO(Racun)))
                .orElseGet(()-> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    private RacunDTO convertRacunToDTO(Racun racun){
        List<Jelo> jela = racun.getJela();
        List<JeloDTO> jelaDTO = new ArrayList<>();
        for (Jelo j: jela)
            jelaDTO.add(new JeloDTO(j.getNaziv(), j.getCijena().toString()+'€'));
        RacunDTO racunDTO = new RacunDTO(racun.getUuid(), racun.getMenza(), jelaDTO,
                racun.getSuma().toString()+'€', racun.getBlagajna(), racun.getVrijemeIzdavanja());
        return racunDTO;
    }

    //dodavanje odredene kolicine istog jela na racun preko njegovog ID-a
    @GetMapping("dodaj-jelo/{uuidR},{uuidJ},{kol}")
    public ResponseEntity<RacunDTO> addToRacun(@PathVariable("uuidR") UUID uuidRacuna,
                                            @PathVariable("uuidJ") UUID uuidJela,
                                            @PathVariable("kol") Integer kolicina){
        return racunService.addToRacun(uuidRacuna, uuidJela, kolicina).map(Racun -> ResponseEntity.status(HttpStatus.OK).body(Racun))
                .orElseGet(()-> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    //uklanjanje odredenog jela sa racuna preko njegovog ID-a
    @GetMapping("ukloni-jelo/{uuidR},{uuidJ}")
    public ResponseEntity<Racun> removeFromRacun(@PathVariable("uuidR") UUID uuidRacuna,
                                            @PathVariable("uuidJ") UUID uuidJela){
        return racunService.removeFromRacun(uuidRacuna, uuidJela).map(Racun -> ResponseEntity.status(HttpStatus.OK).body(Racun))
                .orElseGet(()-> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    //dohvacanje svih jela
    @GetMapping("jela")
    public List<Jelo> getJela(){
        return racunService.getJel();
    }

    @PutMapping("prijavi-guzvu/{uuid}")
    public ResponseEntity<Menza> prijaviGuzvu(@PathVariable("uuid") UUID uuid){
        return menzeService.prijaviGuzvu(uuid).map(menza -> ResponseEntity.status(HttpStatus.OK).body(menza))
                .orElseGet(()-> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    @PutMapping("odjavi-guzvu/{uuid}")
    public ResponseEntity<Menza> odjaviGuzvu(@PathVariable("uuid") UUID uuid){
        return menzeService.odjaviGuzvu(uuid).map(menza -> ResponseEntity.status(HttpStatus.OK).body(menza))
                .orElseGet(()-> ResponseEntity.status(HttpStatus.NOT_FOUND).build());    }

}
