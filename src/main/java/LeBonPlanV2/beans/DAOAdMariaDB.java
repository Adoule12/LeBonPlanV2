package LeBonPlanV2.beans;
import org.mindrot.jbcrypt.BCrypt;
import java.sql.*;

public class DAOAdMariaDB implements DAOAd{
    private final DAOFactory daoFactory;
    DAOAdMariaDB(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
    @Override
    public void createAD(String personneAssocier , String title ,String price , String picture , String description , String city , String category ){
        try (Connection connexion = daoFactory.getConnection();
             PreparedStatement preparedStatement = connexion.prepareStatement(
                     "INSERT INTO listad(title , price , picture, description , city , owner , category) VALUES(?, ? , ?, ?, ?, ?, ?);")) {
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, price);
            preparedStatement.setString(3, picture);
            preparedStatement.setString(4, description);
            preparedStatement.setString(5, city);
            preparedStatement.setString(6, personneAssocier);
            preparedStatement.setString(7, category);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String id){
    }
}
