package LeBonPlanV2.servlets.actions;

import LeBonPlanV2.beans.DAOUtilisateur;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Action pour enlever le grade admin
 */
public class DelAdmin implements Action{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, DAOUtilisateur daoBonPlan) throws ServletException, IOException {
        String message ="";
        String email = request.getParameter("email");
        if(email != null){
            if(daoBonPlan.switchGrade(email,0)){
                message = "good";
            }else {
                message="erreur";
            }
        }
        String emailAd = String.valueOf(request.getSession().getAttribute("email"));
        request.setAttribute("list",daoBonPlan.getAdmins(emailAd));
        request.getRequestDispatcher("/jsp/delAdminView.jsp").forward(request, response);
        return message;

    }
}
