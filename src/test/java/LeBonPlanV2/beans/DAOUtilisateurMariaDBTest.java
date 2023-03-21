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

    @Test
    void editUser(){

        daoBonPlan = daoFactory.getUtilisateurDao("MariaDB");

        String mail = "test@gmail.com";
        String mailM = "";
        String lastname = "";
        String firstname = "";
        Date birthday = Date.valueOf("2023-02-27");
        String picture = "vide";


        mailM = "update@gmail.com";
        if(mailM.equals("update@gmail.com")){
            String erreur = daoBonPlan.editUser(mail,mailM,lastname,firstname,birthday,picture);
            assertEquals("mailUpdate",erreur);

            mail = "update@gmail.com";
            mailM = "test@gmail.com";
            daoBonPlan.editUser(mail,mailM,lastname,firstname,birthday,picture);


        }

        lastname = "updateLastname";
        mailM = "";
        if(lastname.equals("updateLastname")){
            String erreur = daoBonPlan.editUser(mail,mailM,lastname,firstname,birthday,picture);
            assertEquals("lastnameUpdate",erreur);

            lastname= "Nolan";
            daoBonPlan.editUser(mail,mailM,lastname,firstname,birthday,picture);
        }

        firstname = "updateFirstname";
        lastname = "";
        if(firstname.equals("updateFirstname")){
            String erreur = daoBonPlan.editUser(mail,mailM,lastname,firstname,birthday,picture);
            assertEquals("firstnameUpdate",erreur);

            firstname= "Babin";
            daoBonPlan.editUser(mail,mailM,lastname,firstname,birthday,picture);
        }

        firstname = "";
        birthday = Date.valueOf("2002-01-12");

        if(birthday.equals("2002-01-12")){
            String erreur = daoBonPlan.editUser(mail,mailM,lastname,firstname,birthday,picture);
            assertEquals("ageUpdate",erreur);

            birthday= Date.valueOf("2023-02-27");
            daoBonPlan.editUser(mail,mailM,lastname,firstname,birthday,picture);
        }


        birthday= Date.valueOf("2023-02-27");
        picture = "imgProfil/profil.jpg";

        if(picture.equals("imgProfil/profil.jpg")){
            String erreur = daoBonPlan.editUser(mail,mailM,lastname,firstname,birthday,picture);
            assertEquals("pictureUpdate",erreur);

            picture = "vide";
            daoBonPlan.editUser(mail,mailM,lastname,firstname,birthday,picture);
        }

        picture = "vide";
        birthday = Date.valueOf("2012-01-12");

        if(birthday.equals("2012-01-12")){
            String erreur = daoBonPlan.editUser(mail,mailM,lastname,firstname,birthday,picture);
            assertEquals("vous êtes trop jeune",erreur);
        }
        birthday = Date.valueOf("2023-02-27");
        mailM = "bab1nono85@gmail.com";
        if(mailM.equals("bab1nono85@gmail.com")){
            String erreur = daoBonPlan.editUser(mail,mailM,lastname,firstname,birthday,picture);
            assertEquals("mail deja existant",erreur);

        }

        mailM = "update2@gmail.com";
        lastname = "update2Lastname";
        firstname = "update2Firstname";
        birthday = Date.valueOf("2002-02-12");
        picture = "imgProfil/profil2.jpg";
        if(mailM.equals("update2@gmail.com") && lastname.equals("update2Lastname") && firstname.equals("update2Firstname") && birthday.equals("2002-02-12") && picture.equals("imgProfil/profil2.jpg")){
            String erreur = daoBonPlan.editUser(mail,mailM,lastname,firstname,birthday,picture);
            assertEquals("lastnameUpdatefirstnameUpdateageUpdatemailUpdatepictureUpdate",erreur);

            mail = "update@gmail.com";
            mailM = "test@gmail.com";
            lastname= "Nolan";
            firstname= "Babin";
            birthday= Date.valueOf("2023-02-27");
            picture = "vide";
            daoBonPlan.editUser(mail,mailM,lastname,firstname,birthday,picture);


        }
    }

    @Test
    void postAd(){

        daoBonPlan = daoFactory.getUtilisateurDao("MariaDB");

        String title = "Voiture";
        float price = Float.parseFloat("1000.99");
        String picture = "imgProfil/profil.jpg";
        String description = "Une belle voiture";
        String city = "Angers";
        int owner = 12;
        int category = 1;
        int conditions = 1;

        String message = daoBonPlan.postAd(title,price,picture,description,city,owner,category,conditions);
        assertEquals("true",message);

    }



}