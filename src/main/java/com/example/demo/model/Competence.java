package com.example.demo.model;

import com.example.demo.view.VueCompetence;
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
public class Competence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({VueUtilisateur.class, VueCompetence.class})
    private Integer id;

    @JsonView({VueUtilisateur.class, VueCompetence.class})
    private String nom;

    @ManyToMany(mappedBy = "listeCompetence")
    @JsonView(VueCompetence.class)
    private Set<Utilisateur> listeUtilisateur = new HashSet<>();
}
