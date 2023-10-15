package be.bstorm.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Client extends Personne {

    @ManyToMany(mappedBy = "clients")
    private Set<Livre> livres = new HashSet<>();

    @OneToOne(mappedBy = "client", cascade = CascadeType.ALL)
    private Adresse adresse;

}
