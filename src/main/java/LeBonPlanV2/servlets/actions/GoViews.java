package LeBonPlanV2.servlets.actions;

import LeBonPlanV2.beans.DAOUtilisateur;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GoViews implements Action{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, DAOUtilisateur daoBonPlan) throws ServletException, IOException {
        String grade =   String.valueOf(request.getSession().getAttribute("email"));
        System.out.println(grade);
        List<List> listAd = new ArrayList<>();
        HttpSession session = request.getSession( true );
        listAd = daoBonPlan.filtreAd(null,null,null,null,null,null);
        session.setAttribute( "price", null );
        session.setAttribute( "categorie", null );
        session.setAttribute( "conditions", null );
        session.setAttribute( "tris", null );
        request.setAttribute("listAd",listAd);
        System.out.println(request.getParameter("from"));
        if(request.getParameter("from")!=null){
            if(request.getParameter("from").equals("moderation")){
                session.setAttribute("moderation","O");
            }
        }
        if(daoBonPlan.checkAdmin(grade) ){
            System.out.println("Admin");
            if(session.getAttribute("moderation").toString().equals("1")){
                request.getRequestDispatcher("/jsp/moderationAdView.jsp").forward(request, response);
            }else {
                request.getRequestDispatcher("/jsp/adminView.jsp").forward(request, response);
            }
        }else{
            System.out.println("CLients");
            request.getRequestDispatcher("/jsp/clientView.jsp").forward(request, response);
        }
        request.getRequestDispatcher("/jsp/connected.jsp").forward(request, response);
        return null;
    }
}




