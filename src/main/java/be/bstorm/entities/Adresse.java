package be.bstorm.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Adresse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String rue;
    private  String numero;
    private int CP;
    private String Ville;

    @OneToOne
    @JoinColumn(name = "client_id")
    private Client client;
}
