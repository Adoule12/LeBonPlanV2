package LeBonPlanV2.servlets.actions;

import LeBonPlanV2.beans.DAOUtilisateur;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class EditAd implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, DAOUtilisateur daoBonPlan) throws ServletException, IOException {
        Object idAD = request.getParameter("a");
        if(idAD != null){
            int idAD_ext = Integer.parseInt(String.valueOf(idAD));
            System.out.println("cooolll : "+idAD_ext);

            String delete = request.getParameter("delete");

            System.out.println("delete"+delete);
            if(delete!=null){
                daoBonPlan.deleteAd(idAD_ext,false);
            }else {
                String title = request.getParameter("title");
                String priceEXTRACT = request.getParameter("price");
                Float price = null;
                String picture = request.getParameter("picture");
                String description = request.getParameter("description");
                String city = request.getParameter("city");
                String categoryEXTRACT = request.getParameter("category");
                Integer category = null;
                String conditionsEXTRACT = request.getParameter("conditions");
                Integer conditions = null;
                System.out.println("price" + priceEXTRACT + "euro");
                if (priceEXTRACT != null && priceEXTRACT != "") {
                    System.out.println("Vrai");
                    price = Float.parseFloat(priceEXTRACT);
                    System.out.println("Vrai2");
                }

                if (categoryEXTRACT != null && categoryEXTRACT != "") {
                    System.out.println("Vrai aussi");
                    category = Integer.parseInt(categoryEXTRACT);
                }

                if (conditionsEXTRACT != null && conditionsEXTRACT != "") {
                    System.out.println("Vrai egalement");
                    conditions = Integer.parseInt(request.getParameter("conditions"));
                }

                if (title != null || price != null || picture != null || description != null || city != null || category != null || conditions != null) {
                    System.out.println("je suis dedans");
                    daoBonPlan.editAd(idAD_ext, title, price, picture, description, city, category, conditions);

                }
            }
            String grade = String.valueOf(request.getSession().getAttribute("email"));
            if (daoBonPlan.checkAdmin(grade)) {
                request.getRequestDispatcher("/jsp/adminView.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("/jsp/clientView.jsp").forward(request, response);
            }


        }else {
            Object Owner = request.getSession().getAttribute("email");
            int id = daoBonPlan.emailToId((String) Owner);

            List<List> listAd = daoBonPlan.myAd(id);

            request.setAttribute("listAd", listAd);

            request.getRequestDispatcher("/jsp/editAd.jsp").forward(request, response);
        }
        return null;

    }
}
