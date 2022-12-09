package com.example.demo.model;

import com.example.demo.view.VueUtilisateur;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Pays {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(VueUtilisateur.class)
    private Integer id;

    @JsonView(VueUtilisateur.class)
    private String nom;

    @OneToMany(mappedBy = "pays")
    private Set<Utilisateur> listeUtilisateur = new HashSet<>();
}
