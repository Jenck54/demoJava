package com.example.demo.model;

import com.example.demo.view.VueCompetence;
import com.example.demo.view.VueUtilisateur;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({VueUtilisateur.class, VueCompetence.class})
    private Integer id;

    @JsonView({VueUtilisateur.class, VueCompetence.class})
    private String nom;

    @JsonView({VueUtilisateur.class, VueCompetence.class})
    private String prenom;

    @ManyToOne(optional = false)
    @JsonView(VueUtilisateur.class)
    private Pays pays;

    @ManyToMany
    @JoinTable(name = "competence_utilisateur",
            joinColumns = @JoinColumn(name = "utilisateur_id"),
            inverseJoinColumns = @JoinColumn(name = "competence_id"))
    @JsonView(VueUtilisateur.class)
    private Set<Competence> listeCompetence = new HashSet<>();
}
