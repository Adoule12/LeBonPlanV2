package LeBonPlanV2.beans;

import java.util.Date;

public interface DAOAd {

   void createAD(String personneAssocier , String title ,String price , String picture , String description , String city , String category );
   void delete(String id);
   //void findAD(String category);
   //void edit(String title ,String price , String picture , String description , String city , String category , String state);

   }

/*
    concernant table AD
    creatAD (deposer annonces)
    SuppreAD
    findAD aller chercher ad avec critère de selection (prix, état,catégorie....),
    IMPORTANT check ou pas(accepté par un admin) donc validée ou pas
    modération (juste admin) visibilité annononce, plus la modification
    modifier annonces(prix, titre, état catégorie, description,...) tous lors du ce qui est inscrit lors du depot
    findUser renvois info du vendeur pour l'annonce
*/