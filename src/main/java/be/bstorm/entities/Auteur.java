package be.bstorm.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Auteur extends Personne {

    @OneToMany(mappedBy = "auteur")
    Set<Livre> livres = new HashSet<>();
}
