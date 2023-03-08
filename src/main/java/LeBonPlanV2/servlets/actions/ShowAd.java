package LeBonPlanV2.servlets.actions;
import LeBonPlanV2.beans.DAOUtilisateur;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ShowAd implements Action {
    public String execute(HttpServletRequest request, HttpServletResponse response, DAOUtilisateur daoBonPlan) throws ServletException, IOException {
        List<List> listAd = new ArrayList<>();

        String price=request.getParameter("price");
        Float priceMax =null;

        String categorieEXTRACT = request.getParameter("categorie");
        Integer categorie = null;

        String conditionsEXTRACT = request.getParameter("conditions");
        Integer conditions = null;

        String resetEXTRACTJSP = request.getParameter("resetButton");
        if(resetEXTRACTJSP == null){//pour contrer erreur quand on reset pas car il est null si pas coché
            resetEXTRACTJSP="";
        }
        if(resetEXTRACTJSP.equals("Reset")){
            HttpSession session = request.getSession( true );
            session.setAttribute( "price", null );
            session.setAttribute( "categorie", null );
            session.setAttribute( "conditions", null );
        }else{
            if(price != null){
                HttpSession session = request.getSession( true );
                session.setAttribute( "price", price );
                priceMax= Float.parseFloat(session.getAttribute("price").toString());

            }
            if(categorieEXTRACT !=null){
                HttpSession session = request.getSession( true );
                session.setAttribute( "categorie", categorieEXTRACT );
                categorie = Integer.parseInt(session.getAttribute("categorie").toString());

            }
            if(conditionsEXTRACT !=null){
                HttpSession session = request.getSession( true );
                session.setAttribute( "conditions", conditionsEXTRACT );
                conditions = Integer.parseInt(session.getAttribute("conditions").toString());
            }
            HttpSession session = request.getSession( true );
            if(session.getAttribute("price")!=null){
                priceMax= Float.parseFloat(session.getAttribute("price").toString());
            }
            if(session.getAttribute("categorie")!=null){
                categorie = Integer.parseInt(session.getAttribute("categorie").toString());
            }
            if(session.getAttribute("conditions")!=null){
                conditions = Integer.parseInt(session.getAttribute("conditions").toString());
            }
        }


        listAd = daoBonPlan.filtreAd(priceMax,categorie,null,conditions);
        System.out.println(listAd);
        request.setAttribute("listAd",listAd);
        request.getRequestDispatcher("/jsp/clientView.jsp").forward(request, response);

        return "dfgh";
    }
}