package LeBonPlanV2.beans;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DAOUtilisateurMariaDB implements DAOUtilisateur{

    private final DAOFactory daoFactory;
    DAOUtilisateurMariaDB(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    /**
     * Fonction pour la création et l'ajout d'un compte dans la table de donné user.
     * Elle prend en compte différent règle d'admissions (être majeur, email unique dans la table user).
     * @param mail
     * @param password
     * @param lastname
     * @param firstname
     * @param birthday
     * @param phoneNumber
     * @return erreur (String) message d'erreur si les règles d'admissions ne sont pas respèctées.
     */
    @Override
    public String create(String mail, String password,String lastname, String firstname, Date birthday, String phoneNumber) {
        String erreur = "";
        int Age=18;
        boolean mailOk = true;
        boolean ageOk = true;
        boolean phoneOk = true;
        long millis=System.currentTimeMillis();
        Date actualDate = new Date(millis);
        Date legalAge = new Date(millis);

        Calendar c = Calendar.getInstance();
        c.setTime(actualDate);
        c.add(Calendar.YEAR, -Age);
        legalAge.setTime( c.getTime().getTime() );
        if(birthday.after(legalAge)){
            ageOk = false;
            erreur = erreur + "vous êtes trop jeune ";
        }



        try (Connection connexion = daoFactory.getConnection();
             PreparedStatement preparedStatement = connexion.prepareStatement(
                     "SELECT mail FROM user WHERE mail=?;")) {
            preparedStatement.setString(1, mail);
            ResultSet resultat = preparedStatement.executeQuery();
            if(resultat.next()){
                if (resultat.getString("mail").equals(mail)) {
                    mailOk = false;
                    erreur = erreur +"\n mail déja existant";
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try (Connection connexion = daoFactory.getConnection();
             PreparedStatement preparedStatement = connexion.prepareStatement(
                     "SELECT phoneNumber FROM user WHERE phoneNumber=?;")) {
            preparedStatement.setString(1, phoneNumber);
            ResultSet resultat = preparedStatement.executeQuery();
            if(resultat.next()){
                if (resultat.getString("phoneNumber").equals(phoneNumber)) {
                    phoneOk = false;
                    erreur = erreur +"\n numero de telephone déja existant";
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (mailOk && ageOk && phoneOk) {
            try (Connection connexion = daoFactory.getConnection();
                 PreparedStatement preparedStatement = connexion.prepareStatement(
                         "INSERT INTO user(mail, password,lastname,firstname,birthday,phoneNumber) VALUES(?, ?,?,?,?,?);")) {
                preparedStatement.setString(1, mail);
                preparedStatement.setString(2, BCrypt.hashpw(password, BCrypt.gensalt()));
                preparedStatement.setString(3, lastname);
                preparedStatement.setString(4, firstname);
                preparedStatement.setDate(5, birthday);
                preparedStatement.setString(6, phoneNumber);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();

            }
        }
        return erreur;
    }

    /**
     * Fonction pour connaitre état d'un compte dans la table user
     * @param mail
     * @param password
     * @return message (string) sur état du compte dans la table user
     */
    @Override
    public String read(String mail, String password){
        String login = "erreur de connexion";
        try (Connection connexion = daoFactory.getConnection();
             PreparedStatement preparedStatement = connexion.prepareStatement(
                     "SELECT * FROM user WHERE mail=?;")) {
            preparedStatement.setString(1, mail);
            ResultSet resultat = preparedStatement.executeQuery();
            if(resultat.next()) {
                if(resultat.getInt("blacklisted") != 1) {
                    if (BCrypt.checkpw(password, resultat.getString("password"))) {
                        login = "ok";
                    } else {
                        login = "erreur de connexion";
                    }
                }else {
                    login = "vous avez ete bani";
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return login;
    }
    @Override
    public String editUser(String mail, String mailM, String lastname, String firstname, Date birthday) {
        String erreur = "";
        if(!lastname.isEmpty()) {
            try (Connection connexion = daoFactory.getConnection();
                 PreparedStatement preparedStatement = connexion.prepareStatement(
                         "UPDATE user SET lastname =? WHERE mail=?;")) {
                preparedStatement.setString(1, lastname);
                preparedStatement.setString(2, mail);
                preparedStatement.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(!firstname.isEmpty()) {
            try (Connection connexion = daoFactory.getConnection();
                 PreparedStatement preparedStatement = connexion.prepareStatement(
                         "UPDATE user SET firstname =? WHERE mail=?;")) {
                preparedStatement.setString(1, firstname);
                preparedStatement.setString(2, mail);
                preparedStatement.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(!birthday.equals("2023-02-27")) {
            int Age = 18;
            boolean ageOk = true;
            long millis = System.currentTimeMillis();
            Date actualDate = new Date(millis);
            Date legalAge = new Date(millis);
            Calendar c = Calendar.getInstance();
            c.setTime(actualDate);
            c.add(Calendar.YEAR, -Age);
            legalAge.setTime(c.getTime().getTime());
            if (birthday.after(legalAge)) {
                ageOk = false;
                erreur = erreur+ "vous êtes trop jeune";
            }
            if (ageOk) {
                try (Connection connexion = daoFactory.getConnection();
                     PreparedStatement preparedStatement = connexion.prepareStatement(
                             "UPDATE user SET birthday =? WHERE mail=?;")) {
                    preparedStatement.setDate(1, birthday);
                    preparedStatement.setString(2, mail);
                    preparedStatement.executeUpdate();

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        if(!mailM.isEmpty()) {
            boolean mailOk = true;
            try (Connection connexion = daoFactory.getConnection();
                 PreparedStatement preparedStatement = connexion.prepareStatement(
                         "SELECT mail FROM user WHERE mail=?;")) {
                preparedStatement.setString(1, mailM);
                ResultSet resultat = preparedStatement.executeQuery();
                if (resultat.next()) {
                    if (resultat.getString("mail").equals(mailM)) {
                        mailOk = false;
                        erreur = erreur +"mail deja existant";
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (mailOk) {
                try (Connection connexion = daoFactory.getConnection();
                     PreparedStatement preparedStatement = connexion.prepareStatement(
                             "UPDATE user SET mail =? WHERE mail=?;")) {
                    preparedStatement.setString(1, mailM);
                    preparedStatement.setString(2, mail);
                    preparedStatement.executeUpdate();

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return erreur;
    }

    /**
     * Fonction pour ajouter une annonce dans la table lisAd.
     * @param title
     * @param price
     * @param picture
     * @param description
     * @param city
     * @param owner
     * @param category
     * @param conditions
     * @return boolean si ajout dans la table correctement mené.
     */
    @Override
    public boolean postAd(String title, float price,String picture,String description, String city,int owner, int category, int conditions){
        long millis=System.currentTimeMillis();
        Date actualDate = new Date(millis);


        Calendar c = Calendar.getInstance();
        c.setTime(actualDate);

        try (Connection connexion = daoFactory.getConnection();
             PreparedStatement preparedStatement = connexion.prepareStatement(
                     "INSERT INTO listad(title,price, picture,description,city,owner,category,conditions,releaseDate) VALUES(?,?,?,?,?,?,?,?,?);")) {
            preparedStatement.setString(1, title);
            preparedStatement.setFloat(2, price);
            preparedStatement.setString(3, picture);
            preparedStatement.setString(4, description);
            preparedStatement.setString(5, city);
            preparedStatement.setInt(6, owner);
            preparedStatement.setInt(7, category);
            preparedStatement.setInt(8, conditions);
            preparedStatement.setDate(9,actualDate);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean postPic(String path, int id) {
        try (Connection connexion = daoFactory.getConnection();
             PreparedStatement preparedStatement = connexion.prepareStatement(
                     "UPDATE listad SET picture =? WHERE id=?;")) {
            preparedStatement.setString(1, path);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean editAd(int id,String title, Float price,String picture,String description, String city, Integer category, Integer conditions){
        if(!title.isEmpty()) {
            try (Connection connexion = daoFactory.getConnection();
                 PreparedStatement preparedStatement = connexion.prepareStatement(
                         "UPDATE listad SET title =? WHERE id=?;")) {
                preparedStatement.setString(1, title);
                preparedStatement.setInt(2, id);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(price !=null ) {
            try (Connection connexion = daoFactory.getConnection();
                 PreparedStatement preparedStatement = connexion.prepareStatement(
                         "UPDATE listad SET price =? WHERE id=?;")) {
                preparedStatement.setFloat(1, price);
                preparedStatement.setInt(2, id);
                preparedStatement.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        System.out.println("avant picture "+picture);
        if(!picture.equals("vide")) {
            System.out.println("picture");
            try (Connection connexion = daoFactory.getConnection();
                 PreparedStatement preparedStatement = connexion.prepareStatement(
                         "UPDATE listAd SET picture =? WHERE id=?;")) {
                preparedStatement.setString(1, picture);
                preparedStatement.setInt(2, id);
                preparedStatement.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(!description.isEmpty()) {
            try (Connection connexion = daoFactory.getConnection();
                 PreparedStatement preparedStatement = connexion.prepareStatement(
                         "UPDATE listAd SET description =? WHERE id=?;")) {
                preparedStatement.setString(1, description);
                preparedStatement.setInt(2, id);
                preparedStatement.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(!city.isEmpty()) {
            try (Connection connexion = daoFactory.getConnection();
                 PreparedStatement preparedStatement = connexion.prepareStatement(
                         "UPDATE listAd SET city =? WHERE id=?;")) {
                preparedStatement.setString(1, city);
                preparedStatement.setInt(2, id);
                preparedStatement.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(category !=null) {
            try (Connection connexion = daoFactory.getConnection();
                 PreparedStatement preparedStatement = connexion.prepareStatement(
                         "UPDATE listAd SET category =? WHERE id=?;")) {
                preparedStatement.setInt(1, category);
                preparedStatement.setInt(2, id);
                preparedStatement.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(conditions !=null ) {
            try (Connection connexion = daoFactory.getConnection();
                 PreparedStatement preparedStatement = connexion.prepareStatement(
                         "UPDATE listAd SET conditions =? WHERE id=?;")) {
                preparedStatement.setInt(1, conditions);
                preparedStatement.setInt(2, id);
                preparedStatement.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return true;
    }

    /**
     * Fonction pour recupérer l'ID d'un compte dans la table user avec son email.
     * @param email
     * @return id(Integer) l'id du compte associé à l'email donné.
     */
    @Override
    public int emailToId(String email){
        int id = 100;
        try (Connection connexion = daoFactory.getConnection();
             PreparedStatement preparedStatement = connexion.prepareStatement("SELECT id FROM user WHERE mail=?;")) {
            preparedStatement.setString(1, email);
            ResultSet resultat = preparedStatement.executeQuery();
            if(resultat.next()) {
                id = resultat.getInt("id");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id;
    }


    /**
     * Fonction pour tester si compte dans la table user associé à l'email donné possède le grade ADMIN ou non.
     * @param mail
     * @return admin (Boolean) true si compte associé à l'amil est admin ou false sinon.
     */
    @Override
    public boolean checkAdmin(String mail){
        boolean admin = false;
        try (Connection connexion = daoFactory.getConnection();
             PreparedStatement preparedStatement = connexion.prepareStatement(
                     "SELECT * FROM user WHERE mail=?;")) {
            preparedStatement.setString(1, mail);
            ResultSet resultat = preparedStatement.executeQuery();
            if(resultat.next()) {

                if(resultat.getString("grade").equals("1")){
                    admin=true;
                }
                else{
                    admin = false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admin;
    }

    /**
     * Fonction pour changer le grade d'un compte associé à l'email dans la table user.
     * Grade 0 ---> client classique
     * Grade 1 ---> Admin
     * @param mail
     * @param grade
     * @return Boolean si action dans la table user fait avec succès.
     */
    @Override
    public boolean switchGrade(String mail, Integer grade){
        try (Connection connexion = daoFactory.getConnection();
             PreparedStatement preparedStatement = connexion.prepareStatement(
                     "UPDATE user SET grade =? WHERE mail=?;")){
            preparedStatement.setInt(1, grade);
            preparedStatement.setString(2,mail);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * Fonction pour obtenir l'ensemble des comptes Client de la table user.
     * Les comptes donc le grade =0.
     * @return annuaire (List<String>) contenant les emails de chaque client (compte avec grade=0 dans la table user).
     */
    @Override
    public List<String> getClients(){
        List<String> annuaire = new ArrayList<>();
        try (Connection connexion = daoFactory.getConnection();
             Statement statement = connexion.createStatement();
             ResultSet resultat = statement.executeQuery(
                     "SELECT mail,lastname,firstname FROM user WHERE grade!=1")){
            while (resultat.next()) {
                String mail = resultat.getString("mail");
                annuaire.add(mail);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return annuaire;
    }

    /**
     * Fonction pour obtenir l'ensemble des admins excluant le compte associé à l'email donné en param.
     * NB: ne renvois pas le compte de l'email passé en param.
     * @param email
     * @return annuaire (List<String>) liste de String contenant l'ensemble des emails des comptes avec le grade admin (1).
     */
    @Override
    public List<String> getAdmins(String email){
        //doit tout renvoyer sauf soit meme
        List<String> annuaire = new ArrayList<>();
        try (Connection connexion = daoFactory.getConnection();
             PreparedStatement preparedStatement = connexion.prepareStatement(
                     "SELECT mail,lastname,firstname FROM user WHERE (grade!=0) AND (mail!=?)")) {
            preparedStatement.setString(1, email);
            ResultSet resultat = preparedStatement.executeQuery();
            while (resultat.next()) {

                String mail = resultat.getString("mail");
                annuaire.add(mail);
                System.out.println("annuaire"+annuaire);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return annuaire;
    }

    /**
     * Fonction pour supprimer le compte associé à l'email passé en param dans la table user.
     * @param email
     */
    @Override
    public void deleteUser(String email) {
        try (Connection connexion = daoFactory.getConnection();
             PreparedStatement preparedStatement = connexion.prepareStatement(
                     "DELETE FROM user WHERE mail = ? ;")) {
            preparedStatement.setString(1,email);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Fonction pour supprimer une annonce de la table listAd.
     * Fonction qui gère la validité dans le temps d'une annonce, si l'annonce date de plus de 30j elle est supprimée.
     * L'annonce supprimée est celle associée à l'id passé en param
     * @param id
     * @param date
     */
    @Override
    public void deleteAd(int id,boolean date) {
        if(date){
            long millis=System.currentTimeMillis();
            Date actualDate = new Date(millis);
            Date limiteDay = new Date(millis);
            Calendar c = Calendar.getInstance();
            c.setTime(actualDate);
            c.add(Calendar.DATE, -30);
            limiteDay.setTime( c.getTime().getTime() );
            List<Integer> listAd = new ArrayList<>();

            try (Connection connexion = daoFactory.getConnection();
                 Statement statement = connexion.createStatement();
                 PreparedStatement preparedStatement = connexion.prepareStatement(
                         "SELECT id,releaseDate FROM listad")) {
                ResultSet resultat = preparedStatement.executeQuery();
                while (resultat.next()) {
                    Date releaseDate = resultat.getDate("releaseDate");
                    if(releaseDate.before(limiteDay)) {
                        Integer Adid = resultat.getInt("id");
                        listAd.add(Adid);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if(!listAd.isEmpty()){
                for (int i = 0; i<listAd.size(); i++){
                    try (Connection connexion = daoFactory.getConnection();
                         PreparedStatement preparedStatement = connexion.prepareStatement(
                                 "DELETE FROM listad WHERE id = ? ;")) {
                        preparedStatement.setInt(1, listAd.get(i));
                        preparedStatement.executeUpdate();

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

            }
        }else {
            try (Connection connexion = daoFactory.getConnection();
                 PreparedStatement preparedStatement = connexion.prepareStatement(
                         "DELETE FROM listad WHERE id = ? ;")) {
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * Fonction pour obtenir l'ensemble des annonces associé dans la table listad associé à un id d'un compte de la table user.
     * @param idOwner
     * @return listAd (List<List>) uns liste de list adInfo(List<String >) contenant les informations en String lié aux annonces de l'id de l'owner des annonces.
     */
    @Override
    public List<List> myAd(int idOwner) {
        List<List> listAd = new ArrayList<>();
        try (Connection connexion = daoFactory.getConnection();
             Statement statement = connexion.createStatement();
             PreparedStatement preparedStatement = connexion.prepareStatement(
                     "SELECT title,id,price,picture,state FROM listad WHERE owner=?")) {
            preparedStatement.setInt(1, idOwner);
            ResultSet resultat = preparedStatement.executeQuery();
            while (resultat.next()) {
                String titleEXTRACT = resultat.getString("title");
                Integer id = resultat.getInt("id");
                Float price = resultat.getFloat("price");
                String image = resultat.getString("picture");
                Integer state = resultat.getInt("state");
                List<String > adInfo=new ArrayList<>();
                adInfo.add(titleEXTRACT);
                adInfo.add(id.toString());
                adInfo.add(price.toString());
                adInfo.add(image);
                adInfo.add(state.toString());

                listAd.add(adInfo);
            }
        } catch (SQLException e) {
                e.printStackTrace();
            }
        return listAd;
    }

    /**
     * Fonction pour obtenir des annonces de la table listAd en fonction de critère donné en paramètre.
     * Tris croissant ou de-croissant ou aucun.
     * @param priceMax
     * @param categorie
     * @param city
     * @param condition
     * @param tris
     * @param moderationState état de l'annonce (state) visible ou non pour client, 0---> pas visible, 1---> visible
     * @return listAd (List<List>) une list de list adInfo (List<String >) contenant les infos des annonces de la table listAd correspondant au filtre demandé en param.
     */
    @Override
    public List<List> filtreAd(Float priceMax, Integer categorie, String city, Integer condition,String tris, Integer moderationState ) {
        List<List> listAd = new ArrayList<>();
        boolean priceOK = false;
        boolean categoryOK = false;
        boolean conditionsOK = false;
        String sql="";
        System.out.println("moderationState"+moderationState);
        if(moderationState !=null){
            if(tris != null){
                System.out.println(moderationState);
                if(tris.equals("croissant")){
                    sql ="SELECT title,price,picture,id,category,conditions,owner FROM listad WHERE state="+moderationState+" ORDER BY price ASC";
                }
                if (tris.equals("des_croissant")) {
                    sql ="SELECT title,price,picture,id,category,conditions,owner FROM listad WHERE state="+moderationState+" ORDER BY price DESC";
                }
            }
            else {
                sql="SELECT title,price,picture,id,category,conditions,owner FROM listad WHERE state="+moderationState;
            }
        }else{
            if(tris != null){
                if(tris.equals("croissant")){
                    sql ="SELECT title,price,picture,id,category,conditions,owner FROM listad ORDER BY price ASC";
                }
                if (tris.equals("des_croissant")) {
                    sql ="SELECT title,price,picture,id,category,conditions,owner FROM listad ORDER BY price DESC";
                }
            }
            else {
                sql="SELECT title,price,picture,id,category,conditions,owner FROM listad";
            }

        }

        try (Connection connexion = daoFactory.getConnection();
             Statement statement = connexion.createStatement();
             ResultSet resultat = statement.executeQuery(
                     sql)){
            while (resultat.next()) {
                priceOK = false;
                categoryOK = false;
                conditionsOK = false;
                String titleEXTRACT = resultat.getString("title");
                Float priceEXTRACT = resultat.getFloat("price");
                String pictureEXTRACT = resultat.getString("picture");
                String picture = pictureEXTRACT;
                Integer id = resultat.getInt("id");
                Integer categoryEXTRACT = resultat.getInt("category");
                Integer conditionsEXTRACT = resultat.getInt("conditions");
                Integer owner = resultat.getInt("owner");
                if(priceMax !=null){
                    if(priceEXTRACT<priceMax){
                        priceOK = true;
                    }
                } else if (priceMax==null) {
                    priceOK = true;
                }

                if(categorie !=null){
                    if(categorie==categoryEXTRACT){
                        categoryOK = true;
                    }
                } else if (categorie==null) {
                    categoryOK = true;
                }

                if(condition !=null){
                    if(condition==conditionsEXTRACT){
                        conditionsOK = true;
                    }
                } else if (condition==null) {
                    conditionsOK = true;
                }
                if(priceOK && conditionsOK && categoryOK){
                    List<String > adInfo=new ArrayList<>();
                    adInfo.add(titleEXTRACT);
                    adInfo.add(priceEXTRACT.toString());
                    adInfo.add(picture);
                    adInfo.add(id.toString());
                    listAd.add(adInfo);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listAd;
    }

    /**
     *
     * @param ban
     * @return
     */
    public List<List> userBan(boolean ban) {
        List<List> listUser = new ArrayList<>();
        String sql ="";
        if(ban){
            sql ="SELECT mail,id FROM user WHERE blacklisted=1 AND grade=0";
        }else{
            sql ="SELECT mail,id FROM user WHERE blacklisted=0 AND grade=0";
        }

        try (Connection connexion = daoFactory.getConnection();
             Statement statement = connexion.createStatement();
             PreparedStatement preparedStatement = connexion.prepareStatement(
                     sql)) {
            ResultSet resultat = preparedStatement.executeQuery();
            while (resultat.next()) {
                String mailEXTRACT = resultat.getString("mail");
                Integer id = resultat.getInt("id");
                List<String > adInfo=new ArrayList<>();
                adInfo.add(mailEXTRACT);
                adInfo.add(id.toString());
                listUser.add(adInfo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listUser;

    }
    @Override
    public boolean ban(int id,int ban){
        if(ban<3) {
            try (Connection connexion = daoFactory.getConnection();
                 PreparedStatement preparedStatement = connexion.prepareStatement(
                         "UPDATE user SET blacklisted =? WHERE id=?;")) {
                preparedStatement.setInt(1, ban);
                preparedStatement.setInt(2, id);
                preparedStatement.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
            try (Connection connexion = daoFactory.getConnection();
                 PreparedStatement preparedStatement = connexion.prepareStatement(
                         "DELETE FROM user WHERE id=?;")) {
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    /**
     * Fonction pour récupérer ensemble des infos concernant l'id de l'annonce en param.
     * On entend par ensemble des infos, celle de l'ad mais aussi les informations concernent le propriétaire de l'annonce.
     * @param idAD
     * @return listInfos (List<List>) une liste contenant deux list(List<String>) listOwnerInfo et listADInfo contenant les informations complete de l'annonce.
     */
    @Override
    public List<List> getADInfo(Integer idAD) {
        List<String> listOwnerInfo = new ArrayList<>();
        List<String> listADInfo = new ArrayList<>();
        List<List> listInfos = new ArrayList<>();

        try (Connection connexion = daoFactory.getConnection();
             Statement statement = connexion.createStatement();
             ResultSet resultat = statement.executeQuery(
                     "SELECT title,price,id,city,description,category,conditions,owner,state FROM listad WHERE id ="+idAD)) {
            while (resultat.next()) {
                listADInfo.add(resultat.getString("title"));
                listADInfo.add(resultat.getString("price"));
                listADInfo.add(resultat.getString("id"));
                listADInfo.add(String.valueOf(resultat.getInt("state")));
                listADInfo.add(resultat.getString("city"));
                listADInfo.add(resultat.getString("description"));
                listOwnerInfo = getOwnerInfoByIDOwner(resultat.getInt("owner"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        listInfos.add(listADInfo);
        listInfos.add(listOwnerInfo);
        return listInfos;
    }

    /**
     * Fonction pour récuper info d'un compte associé a l'id dans la table user.
     * @param idOwner
     * @return listOwnerInfo (List<String>) contenant les informations du compte voulu.
     */
    @Override
    public List<String> getOwnerInfoByIDOwner(Integer idOwner) {
        List<String> listOwnerInfo = new ArrayList<>();

        try (Connection connexion = daoFactory.getConnection();
             Statement statement = connexion.createStatement();
             ResultSet resultat = statement.executeQuery(
                     "SELECT mail,lastname,firstname,phoneNumber FROM user WHERE id ="+idOwner)) {
            while (resultat.next()) {
                listOwnerInfo.add(resultat.getString("mail"));
                listOwnerInfo.add(resultat.getString("lastname"));
                listOwnerInfo.add(resultat.getString("firstname"));
                listOwnerInfo.add(Integer.toString(resultat.getInt("phoneNumber")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOwnerInfo;
    }

    /**
     * Fonction pour mettre à jour l'état de vidibilté d'une annonce avec son id donné en param.
     * state ---> 0, l'annonce n'est pas visible pour un client.
     * state ---> 1, l'annonce est visible par un client.
     * @param idAD
     * @param state
     */
    @Override
    public void updateAdState(int idAD, Integer state){
        if(state != null){
            try (Connection connexion = daoFactory.getConnection();
                 PreparedStatement preparedStatement = connexion.prepareStatement(
                         "UPDATE listad SET state =? WHERE id=?;")) {
                preparedStatement.setInt(1, state);
                preparedStatement.setInt(2, idAD);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }


}






