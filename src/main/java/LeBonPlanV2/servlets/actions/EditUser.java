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

/**
 * Action pour mettre a jour les informations personelle d'un compte
 */
public class EditUser implements Action{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, DAOUtilisateur daoBonPlan) throws ServletException, IOException {
        String erreur = "";

        String date = request.getParameter("birthday");
        Date birthday = Date.valueOf("2023-02-27");
        if(date != null && !date.equals("")){
            birthday = Date.valueOf(date);
        }
        String emailM = request.getParameter("email");
        String email  = String.valueOf(request.getSession().getAttribute("email"));
        String lastname = request.getParameter("lastname");
        String firstname = request.getParameter("firstname");

        if(emailM != null || lastname != null || firstname !=null ){

            String uploadPath = "C:\\Users\\Tanguy Suteau\\IdeaProjects\\LeBonPlanV2PLUS\\src\\main\\webapp\\imgProfil";
            String uploadPathserver = request.getServletContext().getRealPath("") + File.separator + "imgProfil";

            Part part = request.getPart("image_drop");
            String fileName;
            String picture = "vide";
            if(part.getSubmittedFileName() !=null && !Objects.equals(part.getSubmittedFileName(), "")) {
                fileName = part.getSubmittedFileName();
                part.write(uploadPath + File.separator + fileName);
                part.write(uploadPathserver + File.separator + fileName);
                picture = "imgProfil" + File.separator + fileName;
            }
            erreur = daoBonPlan.editUser(email,emailM, lastname,firstname,birthday,picture);
            if(erreur.equals("")){
                String grade =   String.valueOf(request.getSession().getAttribute("email"));
                if(daoBonPlan.checkAdmin(grade) ){
                    request.getRequestDispatcher("/jsp/adminView.jsp").forward(request, response);
                }else{
                    request.getRequestDispatcher("/jsp/clientView.jsp").forward(request, response);
                }
            }else{
                request.setAttribute("info", erreur);
            }
        }
        request.getRequestDispatcher("/jsp/edit.jsp").forward(request, response);

        return erreur;

    }
}

