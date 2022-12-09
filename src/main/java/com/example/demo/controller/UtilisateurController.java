package com.example.demo.controller;

import com.example.demo.dao.UtilisateurDao;
import com.example.demo.model.Utilisateur;
import com.example.demo.view.VueUtilisateur;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class UtilisateurController {

    @Autowired
    private UtilisateurDao utilisateurDao;

    @GetMapping("/utilisateur/{id}")
    @JsonView(VueUtilisateur.class)
    public ResponseEntity<Utilisateur> getUtilisateur(@PathVariable int id) {
        Optional<Utilisateur> utilisateurExistant = utilisateurDao.findById(id);

        if(utilisateurExistant.isPresent()) {
            return new ResponseEntity<>(utilisateurExistant.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/liste-utilisateur")
    @JsonView(VueUtilisateur.class)
    public List<Utilisateur> getListeUtilisateur() {
        return utilisateurDao.findAll();
    }

    @PostMapping("/utilisateur")
    public ResponseEntity<Utilisateur> ajoutUtilisateur(@RequestBody Utilisateur utilisateur) {
        //Si l'utilisateur a un identifiant
        if(utilisateur.getId() != null) {
            Optional<Utilisateur> utilisateurExistant = utilisateurDao.findById(utilisateur.getId());

            //L'utilisateur a fournit un id existant dans la BDD (c'est un update)
            if(utilisateurExistant.isPresent()) {
                utilisateurDao.save(utilisateur);

                return new ResponseEntity<>(utilisateur, HttpStatus.OK);
            } else {
                //L'utilisateur a fournit id qui n'existe pas dans la BDD (c'est une erreur)
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } else {
            utilisateurDao.save(utilisateur);

            return new ResponseEntity<>(utilisateur, HttpStatus.CREATED);
        }
    }

    @DeleteMapping("/utilisateur/{id}")
    public ResponseEntity<Utilisateur> supprimerUtilisateur(@PathVariable int id) {

        Optional<Utilisateur> utilisateurExistant = utilisateurDao.findById(id);

        if(utilisateurExistant.isPresent()) {
            utilisateurDao.deleteById(id);

            return new ResponseEntity<>(utilisateurExistant.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
