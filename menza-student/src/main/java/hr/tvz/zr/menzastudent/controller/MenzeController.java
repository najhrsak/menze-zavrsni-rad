package hr.tvz.zr.menzastudent.controller;

import hr.tvz.zr.menzastudent.database.Database;
import hr.tvz.zr.menzastudent.model.*;
import hr.tvz.zr.menzastudent.service.MenzeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.SimpleTimeZone;
import java.util.UUID;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("menze")
public class MenzeController {
    MenzeService menzeService;

    @GetMapping("nazivi")
    public List<Menza> nazivi(){
        return menzeService.getNaziviMenza();
    }

    @GetMapping("menza/{naziv}")
    public ResponseEntity<Menza> menzaByName(@PathVariable String naziv){
        return menzeService.getMenzaByNaziv(naziv).map(Menza -> ResponseEntity.status(HttpStatus.FOUND).body(Menza))
                .orElseGet(()->ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("menza/posjecenost/{naziv}")
    public Double posjecenostMenze(@PathVariable String naziv){
        Optional<Double> p = menzeService.getPosjecenostMenze(naziv);
        return p.orElse(-1.0);
    }
    @GetMapping ("guzva/{naziv}")
    public Integer getGuzva(@PathVariable("naziv") String naziv){
        Optional<Integer> g = menzeService.getGuzva(naziv);
        return g.orElse(2);
    }
}
