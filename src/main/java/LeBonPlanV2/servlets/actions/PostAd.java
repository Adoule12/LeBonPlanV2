package LeBonPlanV2.servlets.actions;

import LeBonPlanV2.beans.DAOUtilisateur;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.FileAttribute;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Float.parseFloat;

public class PostAd implements Action{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, DAOUtilisateur daoBonPlan) throws ServletException, IOException {

        List<List> listAd = new ArrayList<>();
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

            String uploadPath = "C:\\Users\\Tanguy Suteau\\IdeaProjects\\LeBonPlanV2PLUS\\src\\main\\webapp\\img";
            String uploadPathserver = request.getServletContext().getRealPath("") + File.separator + "img";
            System.out.println(uploadPath);
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String fileName = "drapeau-ville-angers-logo-flagsonline.jpg";

            Part part = request.getPart("image_drop");
            System.out.println("part "+part.getSubmittedFileName());
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
