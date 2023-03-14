package LeBonPlanV2.servlets.actions;

import LeBonPlanV2.beans.DAOUtilisateur;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Login implements Action{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, DAOUtilisateur daoBonPlan) throws ServletException, IOException {
        String etat ="";
        String email = request.getParameter("email");
        String mdp = request.getParameter("password");
        List<List> listAd = new ArrayList<>();
        if(email != null && mdp !=null){
            String login =daoBonPlan.read(email,mdp);
            if(login.equals("ok")){
                daoBonPlan.deleteAd(10,true);
                HttpSession session = request.getSession( true );
                session.setAttribute( "email", email );
                if(daoBonPlan.checkAdmin(email) ){
                    System.out.println("admin");
                    etat="admin";
                    listAd = daoBonPlan.filtreAd(null,null,null,null,null,null);
                    session.setAttribute( "price", null );
                    session.setAttribute( "categorie", null );
                    session.setAttribute( "conditions", null );
                    session.setAttribute( "tris", null );
                    request.setAttribute("listAd",listAd);
                    request.setAttribute("content",etat); // DEMANDER PROF SI CA LEGAL ET ACCEPTE
                    request.getRequestDispatcher("/jsp/adminView.jsp").forward(request, response);
                }else{
                    System.out.println("CLients");
                    etat="client";
                    listAd = daoBonPlan.filtreAd(null,null,null,null,null,1);
                    System.out.println(listAd);
                    session.setAttribute( "price", null );
                    session.setAttribute( "categorie", null );
                    session.setAttribute( "conditions", null );
                    session.setAttribute( "tris", null );
                    request.setAttribute("listAd",listAd);
                    request.setAttribute("content",etat); // DEMANDER PROF SI CA LEGAL ET ACCEPTE
                    request.getRequestDispatcher("/jsp/clientView.jsp").forward(request, response);
                }
                request.setAttribute("content",etat); // DEMANDER PROF SI CA LEGAL ET ACCEPTE
                request.getRequestDispatcher("/jsp/connected.jsp").forward(request, response);
            }else {
                etat="erreur";
                request.setAttribute("info",login);

            }
        }
        request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
        return etat;
    }
}
