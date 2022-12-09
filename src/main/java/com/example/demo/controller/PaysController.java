package com.example.demo.controller;

import com.example.demo.dao.PaysDao;
import com.example.demo.model.Pays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class PaysController {

    @Autowired
    private PaysDao paysDao;

    @GetMapping("/pays/{id}")
    public ResponseEntity<Pays> getPays(@PathVariable int id) {
        Optional<Pays> paysExistant = paysDao.findById(id);

        if(paysExistant.isPresent()) {
            return new ResponseEntity<>(paysExistant.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/liste-pays")
    public List<Pays> getListePays() {
        return paysDao.findAll();
    }

    @PostMapping("/pays")
    public ResponseEntity<Pays> ajoutPays(@RequestBody Pays pays) {
        //Si l'pays a un identifiant
        if(pays.getId() != null) {
            Optional<Pays> paysExistant = paysDao.findById(pays.getId());

            //L'pays a fournit un id existant dans la BDD (c'est un update)
            if(paysExistant.isPresent()) {
                paysDao.save(pays);

                return new ResponseEntity<>(pays, HttpStatus.OK);
            } else {
                //L'pays a fournit id qui n'existe pas dans la BDD (c'est une erreur)
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } else {
            paysDao.save(pays);

            return new ResponseEntity<>(pays, HttpStatus.CREATED);
        }
    }

    @DeleteMapping("/pays/{id}")
    public ResponseEntity<Pays> supprimerPays(@PathVariable int id) {

        Optional<Pays> paysExistant = paysDao.findById(id);

        if(paysExistant.isPresent()) {
            paysDao.deleteById(id);

            return new ResponseEntity<>(paysExistant.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
