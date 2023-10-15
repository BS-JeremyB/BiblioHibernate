package be.bstorm.services;

import be.bstorm.entities.Auteur;
import be.bstorm.entities.Livre;
import be.bstorm.repositories.CRUDRepository;
import be.bstorm.repositories.impl.CRUDRepositoryImpl;

import java.util.List;

public class LivreServiceImpl {

    private final CRUDRepository<Livre> crudRepo;
    private final CRUDRepository<Auteur> crudRepoAuteur;

    public LivreServiceImpl() {
        this.crudRepo = new CRUDRepositoryImpl<>("Livre", Livre.class);
        this.crudRepoAuteur = new CRUDRepositoryImpl<>("Auteur", Auteur.class);
    }

    public void createLivre(Livre livre) {
        // Verifier si l'auteur existe déjà
        Auteur existingAuteur = findAuteurByName(livre.getAuteur().getNom(), livre.getAuteur().getPrenom());
        if (existingAuteur != null) {
            //S'il l'auteur existe déjà, je vais le lier à mon livre
            livre.setAuteur(existingAuteur);
        } else {
            //S'il n'existe pas je vais d'abord le créer
            crudRepoAuteur.create(livre.getAuteur());
        }

        // Création du livre
        crudRepo.create(livre);
    }

    private Auteur findAuteurByName(String nom, String prenom) {
        List<Auteur> auteurs = crudRepoAuteur.getALL();
        for (Auteur auteur : auteurs) {
            if (auteur.getNom().equals(nom) && auteur.getPrenom().equals(prenom)) {
                return auteur;
            }
        }
        return null; // retourne null si aucun auteur n'est trouvé
    }

    public List<Livre> getAllLivres() {
        return crudRepo.getALL();
    }

    public Livre getLivreById(int id) {
        return crudRepo.getById(id);
    }

    public void updateLivre(Livre livre) {
        crudRepo.update(livre.getId(), livre);
    }

    public void deleteLivre(int id) {
        crudRepo.Delete(id);
    }
}
