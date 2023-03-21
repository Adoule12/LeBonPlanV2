package LeBonPlanV2.servlets.actions;
import LeBonPlanV2.beans.DAOUtilisateur;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Action pour ajouter un admin
 */
public class AdAdmin implements Action{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, DAOUtilisateur daoBonPlan) throws ServletException, IOException {
        String message ="";
        String email = request.getParameter("email");
        if(email != null){
            if(daoBonPlan.switchGrade(email,1)){
                message = "good";
            }else {
                message="erreur";
            }
        }
        request.setAttribute("list",daoBonPlan.getClients());
        request.getRequestDispatcher("/jsp/adAdminView.jsp").forward(request, response);
        return message;

    }


}
