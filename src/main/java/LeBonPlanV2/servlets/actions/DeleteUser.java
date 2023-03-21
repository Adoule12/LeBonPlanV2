package LeBonPlanV2.servlets.actions;

import LeBonPlanV2.beans.DAOUtilisateur;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Action pour supprimer un compte (admin ou pas, uniquement sur le mail passé dans la session
 */
public class DeleteUser implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, DAOUtilisateur daoBonPlan) throws ServletException, IOException {
        String email  = String.valueOf(request.getSession().getAttribute("email"));
        HttpSession session = request.getSession();
        session.invalidate();
        daoBonPlan.deleteUser(email);
        forward(request,response,"jsp/home.jsp");
        return null;

    }
}
