package LeBonPlanV2.servlets.actions;

import LeBonPlanV2.beans.DAOUtilisateur;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

public class Signin implements Action{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, DAOUtilisateur daoBonPlan) throws ServletException, IOException {
        String etat ="";
        String date = request.getParameter("birthday");
        Date birthday = Date.valueOf("2023-02-27");
        if(date != null){
            birthday = Date.valueOf(request.getParameter("birthday"));
        }

        String email = request.getParameter("email");
        String mdp = request.getParameter("password");
        String lastname = request.getParameter("lastname");
        String firstname = request.getParameter("firstname");
        String phone = request.getParameter("phone");
        String erreur = "";
        if(email != null && mdp !=null && lastname!=null && firstname!=null && phone!=null){
            erreur = daoBonPlan.create(email,mdp, lastname,firstname,birthday,phone);
            if(erreur.equals("")){
                request.getRequestDispatcher("/jsp/home.jsp").forward(request, response);
            }else{
                request.setAttribute("info", erreur);
            }

        }
        request.getRequestDispatcher("/jsp/signin.jsp").forward(request, response);
        return etat;
    }
}
