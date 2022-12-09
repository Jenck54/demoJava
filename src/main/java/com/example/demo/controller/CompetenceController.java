package com.example.demo.controller;

import com.example.demo.dao.CompetenceDao;
import com.example.demo.model.Competence;
import com.example.demo.view.VueCompetence;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class CompetenceController {

    @Autowired
    private CompetenceDao competenceDao;

    @GetMapping("/competence/{id}")
    @JsonView(VueCompetence.class)
    public ResponseEntity<Competence> getCompetence(@PathVariable int id) {
        Optional<Competence> competenceExistant = competenceDao.findById(id);

        if(competenceExistant.isPresent()) {
            return new ResponseEntity<>(competenceExistant.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/liste-competence")
    @JsonView(VueCompetence.class)
    public List<Competence> getListeCompetence() {
        return competenceDao.findAll();
    }

    @PostMapping("/competence")
    public ResponseEntity<Competence> ajoutCompetence(@RequestBody Competence competence) {
        //Si l'competence a un identifiant
        if(competence.getId() != null) {
            Optional<Competence> competenceExistant = competenceDao.findById(competence.getId());

            //L'competence a fournit un id existant dans la BDD (c'est un update)
            if(competenceExistant.isPresent()) {
                competenceDao.save(competence);

                return new ResponseEntity<>(competence, HttpStatus.OK);
            } else {
                //L'competence a fournit id qui n'existe pas dans la BDD (c'est une erreur)
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } else {
            competenceDao.save(competence);

            return new ResponseEntity<>(competence, HttpStatus.CREATED);
        }
    }

    @DeleteMapping("/competence/{id}")
    public ResponseEntity<Competence> supprimerCompetence(@PathVariable int id) {

        Optional<Competence> competenceExistant = competenceDao.findById(id);

        if(competenceExistant.isPresent()) {
            competenceDao.deleteById(id);

            return new ResponseEntity<>(competenceExistant.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
