package LeBonPlanV2.servlets.actions;

import LeBonPlanV2.beans.DAOUtilisateur;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Action pour déconnecter un compte
 */
public class Logout implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, DAOUtilisateur daoBonPlan) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        List<List> listAd = new ArrayList<>();
        listAd = daoBonPlan.filtreAd(null,null,null,null,null,null);
        request.setAttribute("nbAd",listAd.size());
        forward(request,response,"jsp/home.jsp");
        return null;

    }
}
