package LeBonPlanV2.servlets.actions;

import LeBonPlanV2.beans.DAOUtilisateur;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class DeleteAd implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, DAOUtilisateur daoBonPlan) throws ServletException, IOException {
        String id  = String.valueOf(request.getSession().getAttribute("id"));
        int Adid = Integer.parseInt(id);
        daoBonPlan.deleteAd(Adid, false);
        forward(request,response,"jsp/home.jsp");
        return null;

    }
}
