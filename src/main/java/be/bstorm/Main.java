package be.bstorm;

import be.bstorm.entities.Auteur;
import be.bstorm.entities.Livre;
import be.bstorm.services.LivreServiceImpl;

import java.time.LocalDate;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        LivreServiceImpl livreService = new LivreServiceImpl();

        // Création d'un auteur
        Auteur newAuteur = new Auteur();
        newAuteur.setNom("Rowling");
        newAuteur.setPrenom("J. K.");

        //Création de livres
        Livre livre = new Livre();
        livre.setTitre("Harry Potter à l'école des sorciers");
        livre.setDateAchat(LocalDate.now());

        Livre livre2 = new Livre();
        livre2.setTitre("Harry Potter et la chambre des secrets");
        livre2.setDateAchat(LocalDate.now());

        //Lier le livre 1 & 2 au même auteur
        livre.setAuteur(newAuteur);
        livre2.setAuteur((newAuteur));

        //Ajout du livre 1, l'auteur n'existe donc pas encore
        livreService.createLivre(livre);

        //Ajout du livre 2, l'autre existe depuis l'ajout du livre 1
        livreService.createLivre(livre2);
    }
}