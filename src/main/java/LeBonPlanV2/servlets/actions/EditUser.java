package LeBonPlanV2.servlets.actions;

import LeBonPlanV2.beans.DAOUtilisateur;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.sql.Date;

public class EditUser implements Action{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, DAOUtilisateur daoBonPlan) throws ServletException, IOException {
        String erreur = "";
        String message;

        String date = request.getParameter("birthday");
        Date birthday = Date.valueOf("2023-02-27");
        if(date != null && date !=""){
            birthday = Date.valueOf(request.getParameter("birthday"));
        }
        System.out.println("la date 1.5"+birthday);
        //a modifier
        String emailM = request.getParameter("email");
        String email  = String.valueOf(request.getSession().getAttribute("email"));
        String lastname = request.getParameter("lastname");
        String firstname = request.getParameter("firstname");

        if(emailM != null || lastname != null || firstname !=null ){

            String uploadPath = "C:\\Users\\axoul\\Documents\\B2\\Java\\IdeaProjects\\LeBonPlanV2\\src\\main\\webapp\\imgProfil";
            String uploadPathserver = request.getServletContext().getRealPath("") + File.separator + "imgProfil";
            System.out.println(uploadPath);
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            Part part = request.getPart("image_drop");
            String fileName;
            String picture = "vide";
            if(part.getSubmittedFileName() !=null && part.getSubmittedFileName() != "") {
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

