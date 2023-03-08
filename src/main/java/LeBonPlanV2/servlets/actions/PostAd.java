package LeBonPlanV2.servlets.actions;

import LeBonPlanV2.beans.DAOUtilisateur;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.lang.Float.parseFloat;

public class PostAd implements Action{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, DAOUtilisateur daoBonPlan) throws ServletException, IOException {
        String title = request.getParameter("title");
        String priceEXTRACT = request.getParameter("price");
        Float price = null;
        String picture = request.getParameter("picture");
        String description = request.getParameter("description");
        String city = request.getParameter("city");
        String proprio = String.valueOf(request.getSession().getAttribute("email"));
        int owner = daoBonPlan.emailToId(proprio);
        String categoryEXTRACT = request.getParameter("category");
        Integer category = null;
        String conditionsEXTRACT = request.getParameter("conditions");
        Integer conditions = null;

        if (priceEXTRACT != null){
            price = Float.parseFloat(priceEXTRACT);
            System.out.println("Vrai");
        }

        if (categoryEXTRACT != null){
            category = Integer.parseInt(categoryEXTRACT);
            System.out.println("Vrai aussi");
        }

        if (conditionsEXTRACT != null){
            conditions = Integer.parseInt(request.getParameter("conditions"));
            System.out.println("Vrai egalement");
        }


        if (title !=null){
            daoBonPlan.postAd(title,price,picture,description,city,owner,category,conditions);
            System.out.println("Je suis bien rentré");

            String grade =   String.valueOf(request.getSession().getAttribute("email"));

            if(daoBonPlan.checkAdmin(grade) ){

                request.getRequestDispatcher("/jsp/adminView.jsp").forward(request, response);
            }else{

                request.getRequestDispatcher("/jsp/clientView.jsp").forward(request, response);
            }
        }
        else{
            request.getRequestDispatcher("/jsp/postAdView.jsp").forward(request, response);
        }


        return null;

    }
}
