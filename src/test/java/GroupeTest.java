
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GroupeTest {

    @Test
    public void testCreationPublicationEtComptage() {
        Administrateur admin = new Administrateur("1", "admin", "admin@mail.com");
        Groupe groupe = new Groupe("Test", admin);

        UtilisateurStandard user = new UtilisateurStandard("2", "user", "user@mail.com");
        groupe.ajouterUtilisateur(user);

        user.creerPublication("Bonjour à tous !");
        user.creerPublication("Deuxième message.");

        assertEquals(2, user.getPublications().size());
        assertEquals(2, groupe.compterPublications());
    }

    @Test
    public void testAjoutEtRecherchePublication() {
        Administrateur admin = new Administrateur("1", "admin", "admin@mail.com");
        Groupe groupe = new Groupe("Test", admin);

        UtilisateurStandard user = new UtilisateurStandard("2", "user", "user@mail.com");
        groupe.ajouterUtilisateur(user);

        user.creerPublication("Ceci est une recette de gâteau.");
        user.creerPublication("Ceci est une publication normale.");

        List<Publication> resultats = groupe.rechercherPublicationsParMotCle("recette");

        assertEquals(1, resultats.size());
        assertTrue(resultats.get(0).getContenu().contains("recette"));
    }

    @Test
    public void testCommentaireEtSuppression() {
        Administrateur admin = new Administrateur("1", "admin", "admin@mail.com");
        Groupe groupe = new Groupe("Test", admin);

        Moderateur modo = new Moderateur("3", "modo", "modo@mail.com");
        groupe.ajouterUtilisateur(modo);

        UtilisateurStandard user = new UtilisateurStandard("2", "user", "user@mail.com");
        groupe.ajouterUtilisateur(user);

        user.creerPublication("Une publication");
        Publication pub = user.getPublications().get(0);

        modo.commenter(pub, "Commentaire 1");
        modo.commenter(pub, "Commentaire 2");

        assertEquals(2, pub.getCommentaires().size());

        modo.supprimerCommentaire(pub, pub.getCommentaires().get(0));
        assertEquals(1, pub.getCommentaires().size());
    }

    @Test
    public void testSuppressionPublicationEtUtilisateur() {
        Administrateur admin = new Administrateur("1", "admin", "admin@mail.com");
        Groupe groupe = new Groupe("Test", admin);

        UtilisateurStandard user = new UtilisateurStandard("2", "user", "user@mail.com");
        groupe.ajouterUtilisateur(user);
        user.creerPublication("Post à supprimer");

        Publication pub = user.getPublications().get(0);
        admin.supprimerPublication(pub, groupe);
        assertEquals(0, user.getPublications().size());

        admin.supprimerUtilisateur(user, groupe);
        assertFalse(groupe.contientUtilisateur(user));
    }
}
