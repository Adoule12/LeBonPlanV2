package LeBonPlanV2.servlets.actions;

import LeBonPlanV2.beans.DAOUtilisateur;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Action pour afficher le menu de connexion
 */
public class Home implements Action{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, DAOUtilisateur daoBonPlan) throws ServletException, IOException {
        List<List> listAd = new ArrayList<>();
        listAd = daoBonPlan.filtreAd(null,null,null,null,null,null);
        request.setAttribute("nbAd",listAd.size());
        request.getRequestDispatcher("/jsp/home.jsp").forward(request, response);
        return null;
    }
}
