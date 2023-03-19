package LeBonPlanV2.beans;
import java.sql.Date;
import java.util.List;
public interface DAOUtilisateur {
    String create(String mail, String password, String lastname, String firstname, Date birthday, String phone,String picture);
    String read(String mail, String password);
    boolean checkAdmin(String mail);
    boolean switchGrade(String mail, Integer grade);

    List<String> getClients();
    List<String> getAdmins(String email);
    void deleteUser(String email);
    void deleteAd(int id, boolean date);
    String editUser(String mail, String mailM,String lastname, String firstname, Date birthday, String picture);
    List<List> filtreAd(Float priceMax, Integer categorie, String city, Integer condition,String tris, Integer moderationState);
    List<List> myAd(int idOwner);
    List<List> userBan(boolean ban);
    boolean ban(int id,int ban);
    boolean postAd(String title,float price, String picture,String description, String city,int owner, int category, int conditions );
    boolean editAd(int id,String title,Float price, String picture,String description, String city, Integer category, Integer conditions);
    int emailToId(String email);
    List<List> getADInfo(Integer idAD);
    List<String> getOwnerInfoByIDOwner(Integer idOwner);


    void updateAdState(int idAD, Integer state);
}



/*
VENDREDI 3 MARS 80% TOURNE OBJECTIF Réussi
concernant la table utilisateur
-*create avec verification du compte(inscription) FAIT
_*read check si user connus connection(=) check renvoyé admin ou pas FAITTTTT
supprmier un compte(version perso)
modiff info personelle(nom, adresse numero....)(mdp pas sur pour la sécu si compte piraté...)
modération (uniquement pour admin) ban/verrouiller, donner droit admin,

concernant table AD
creatAD (deposer annonces)
SuppreAD
findAD aller chercher ad avec critère de selection (prix, état,catégorie....),IMPORTANT check ou pas(accepté par un admin) donc validée ou pas
modération (juste admin) visibilité annononce, plus la modification
modifier annonces(prix, titre, état catégorie, description,...) tous lors du ce qui est inscrit lors du depot
findUser renvois info du vendeur pour l'annonce


 */
