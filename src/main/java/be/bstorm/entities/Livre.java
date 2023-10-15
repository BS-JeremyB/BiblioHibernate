package be.bstorm.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Livre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ISBN")
    private long id;
    private String titre;
    @Temporal(TemporalType.DATE)
    private LocalDate dateAchat;

    @ManyToMany
    @JoinTable(name = "Emprunt",
    joinColumns = @JoinColumn(name = "client_id"),
    inverseJoinColumns = @JoinColumn(name="ISBN"))
    private Set<Client> clients = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "auteur_id")
    private Auteur auteur;
}
