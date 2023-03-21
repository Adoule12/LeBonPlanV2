package LeBonPlanV2.servlets.actions;

import LeBonPlanV2.beans.DAOUtilisateur;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.List;
public class PostAd implements Action{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, DAOUtilisateur daoBonPlan) throws ServletException, IOException {

        List<List> listAd;
        String title = request.getParameter("title");
        String priceEXTRACT = request.getParameter("price");
        Float price = null;
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
        }
        if (categoryEXTRACT != null){
            category = Integer.parseInt(categoryEXTRACT);
        }
        if (conditionsEXTRACT != null){
            conditions = Integer.parseInt(request.getParameter("conditions"));
        }

        if (title !=null) {

            String uploadPath = "C:\\Users\\axoul\\Documents\\B2\\Java\\IdeaProjects\\LeBonPlanV2\\src\\main\\webapp\\img";
            String uploadPathserver = request.getServletContext().getRealPath("") + "img";
            String fileName = "imagenotfound.png";

            Part part = request.getPart("image_drop");
            if (part.getSubmittedFileName() != null && part.getSubmittedFileName() !=""){

            fileName = part.getSubmittedFileName();
            part.write(uploadPath + File.separator + fileName);
            part.write(uploadPathserver + File.separator + fileName);


            }
            String picture = "img" + File.separator + fileName;
            daoBonPlan.postAd(title,price,picture,description,city,owner,category,conditions);

            String grade = String.valueOf(request.getSession().getAttribute("email"));

            if (daoBonPlan.checkAdmin(grade)) {

                request.getRequestDispatcher("/jsp/adminView.jsp").forward(request, response);
            }else{
                listAd = daoBonPlan.filtreAd(null,null,null,null,null,1);
                request.setAttribute("listAd",listAd);
                request.getRequestDispatcher("/jsp/clientView.jsp").forward(request, response);
            }
        }else{
            request.getRequestDispatcher("/jsp/postAdView.jsp").forward(request, response);
        }


        return null;

    }
}
