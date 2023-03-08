package LeBonPlanV2.servlets.actions;

import LeBonPlanV2.beans.DAOUtilisateur;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoViews implements Action{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, DAOUtilisateur daoBonPlan) throws ServletException, IOException {
        String grade =   String.valueOf(request.getSession().getAttribute("email"));
        System.out.println(grade);
        if(daoBonPlan.checkAdmin(grade) ){
            System.out.println("Admin");
            request.getRequestDispatcher("/jsp/adminView.jsp").forward(request, response);
        }else{
            System.out.println("CLients");
            request.getRequestDispatcher("/jsp/clientView.jsp").forward(request, response);
        }
        request.getRequestDispatcher("/jsp/connected.jsp").forward(request, response);
        return null;
    }
}




