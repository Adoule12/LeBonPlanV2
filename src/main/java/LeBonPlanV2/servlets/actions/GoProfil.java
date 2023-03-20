package LeBonPlanV2.servlets.actions;

import LeBonPlanV2.beans.DAOUtilisateur;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GoProfil implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, DAOUtilisateur daoBonPlan) throws ServletException, IOException {

        String email = String.valueOf(request.getSession().getAttribute("email"));
        List<String> info = new ArrayList<>();
        List<List> listAd = new ArrayList<>();
        int proprio = daoBonPlan.emailToId(email);
        info = daoBonPlan.getOwnerInfoByIDOwner(proprio);
        request.setAttribute("info",info);
        listAd = daoBonPlan.myAd(proprio);
        request.setAttribute("listAd",listAd);
        request.getRequestDispatcher("/jsp/profilView.jsp").forward(request, response);


        return null;
    }
}
