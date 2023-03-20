package LeBonPlanV2.servlets.actions;

import LeBonPlanV2.beans.DAOUtilisateur;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.Objects;

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

            String uploadPath = "C:\\Users\\axoul\\Documents\\B2\\Java\\IdeaProjects\\LeBonPlanV2\\src\\main\\webapp\\imgProfil";
            String uploadPathserver = request.getServletContext().getRealPath("") + "imgProfil";
            String fileName = "profil.jpg";

            Part part = request.getPart("image_drop");
            if (part.getSubmittedFileName() != null && !Objects.equals(part.getSubmittedFileName(), "")){

                fileName = part.getSubmittedFileName();
                part.write(uploadPath + File.separator + fileName);
                part.write(uploadPathserver + File.separator + fileName);


            }
            String picture = "imgProfil" + File.separator + fileName;

            erreur = daoBonPlan.create(email,mdp, lastname,firstname,birthday,phone, picture);
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
