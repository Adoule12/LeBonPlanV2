package LeBonPlanV2.beans;

import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

class DAOUtilisateurMariaDBTest {

    private final DAOFactory daoFactory;
    private DAOUtilisateur daoBonPlan;

    DAOUtilisateurMariaDBTest() {
        this.daoFactory = DAOFactory.getInstance();
    }

    @Test
    void create(){
        daoBonPlan = daoFactory.getUtilisateurDao("MariaDB");

        String mail = "nouveau.compte@gmail.com";
        String password = "password";
        String lastname = "Nolan";
        String firstname = "Babin";
        Date birthday = Date.valueOf("2000-02-09");
        String phoneNumber = "073484294";
        String picture = "imgProfil\\FLHR3009.JPG";

        if(mail.equals("nouveau.compte@gmail.com")){
            String erreur = daoBonPlan.create(mail,password,lastname,firstname,birthday,phoneNumber,picture);
            assertEquals("ok",erreur);
            daoBonPlan.deleteUser("nouveau.compte@gmail.com");

            birthday = Date.valueOf("2010-02-09");
            String erreurDate = daoBonPlan.create(mail,password,lastname,firstname,birthday,phoneNumber,picture);
            assertEquals("vous êtes trop jeune",erreurDate);
            birthday = Date.valueOf("2000-02-09");
        }


        mail = "bab1nono85@gmail.com";
        if(mail.equals("bab1nono85@gmail.com")){
            String erreur = daoBonPlan.create(mail,password,lastname,firstname,birthday,phoneNumber,picture);
            assertEquals(" mail deja existant",erreur);

            birthday = Date.valueOf("2010-02-09");
            String erreurDate = daoBonPlan.create(mail,password,lastname,firstname,birthday,phoneNumber,picture);
            assertEquals("vous êtes trop jeune mail deja existant",erreurDate);
            birthday = Date.valueOf("2000-02-09");
        }


        mail = "nouveau.compte@gmail.com";
        phoneNumber = "123456789";
        if(phoneNumber.equals("123456789")){
            String erreur = daoBonPlan.create(mail,password,lastname,firstname,birthday,phoneNumber,picture);
            assertEquals(" numero de telephone deja existant",erreur);
            daoBonPlan.deleteUser("nouveau.compte@gmail.com");

            birthday = Date.valueOf("2010-02-09");
            String erreurDate = daoBonPlan.create(mail,password,lastname,firstname,birthday,phoneNumber,picture);
            assertEquals("vous êtes trop jeune numero de telephone deja existant",erreurDate);
            birthday = Date.valueOf("2000-02-09");
        }

        mail = "bab1nono85@gmail.com";
        phoneNumber = "123456789";
        if(phoneNumber.equals("123456789") && mail.equals("bab1nono85@gmail.com")){
            String erreur = daoBonPlan.create(mail,password,lastname,firstname,birthday,phoneNumber,picture);
            assertEquals(" mail deja existant numero de telephone deja existant",erreur);

            birthday = Date.valueOf("2010-02-09");
            String erreurDate = daoBonPlan.create(mail,password,lastname,firstname,birthday,phoneNumber,picture);
            assertEquals("vous êtes trop jeune mail deja existant numero de telephone deja existant",erreurDate);
            birthday = Date.valueOf("2000-02-09");

        }



    }

    @Test
    void read(){
        daoBonPlan = daoFactory.getUtilisateurDao("MariaDB");

        String mail = "nolan@gmail.com";
        String password = "password";


        if(password.equals("password") ){
            String login = daoBonPlan.read(mail,password);
            assertEquals("ok",login);
        }

        password = "pas password";
        if(!password.equals("password")){
            String login = daoBonPlan.read(mail,password);
            assertEquals("erreur de connexion",login);
        }

        mail = "test@gmail.com";
        password = "password";
        if(password.equals("password") ){
            String login = daoBonPlan.read(mail,password);
            assertEquals("vous avez ete bani",login);
        }

    }

    /*@Test
    void editUser(){

        daoBonPlan = daoFactory.getUtilisateurDao("MariaDB");

        String mail = "nolan@gmail.com";
        String mailM = "";
        String lastname = "";
        String firstname = "";
        Date birthday = Date.valueOf("2023-02-27");
        String picture = "vide";


        mailM = "update@gmail.com";
        if(mailM.equals("update@gmail.com")){
            String erreur = daoBonPlan.editUser(mail,mailM,lastname,firstname,birthday,picture);
            assertEquals("mailUpdate",erreur);


        }

    }*/



}