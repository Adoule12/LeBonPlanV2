package LeBonPlanV2.servlets.actions;

import LebBonPlan.beans.DAOUtilisateur;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoProfil implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, DAOUtilisateur daoBonPlan) throws ServletException, IOException {

        request.getRequestDispatcher("/jsp/profilView.jsp").forward(request, response);

        return null;
    }
}
