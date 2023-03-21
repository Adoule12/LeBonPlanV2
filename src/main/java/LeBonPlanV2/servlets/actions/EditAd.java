package LeBonPlanV2.servlets.actions;

import LeBonPlanV2.beans.DAOUtilisateur;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class EditAd implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, DAOUtilisateur daoBonPlan) throws ServletException, IOException {
        HttpSession session = request.getSession( true );
        Object idAD = request.getParameter("annonce");
        if(idAD != null && idAD !=""){
            int idAD_ext = Integer.parseInt(String.valueOf(idAD));

            String delete = request.getParameter("delete");
            if(delete!=null){
                daoBonPlan.deleteAd(idAD_ext,false);
            }else {
                String title = request.getParameter("title");
                String priceEXTRACT = request.getParameter("price");
                Float price = null;
                String description = request.getParameter("description");
                String city = request.getParameter("city");
                String categoryEXTRACT = request.getParameter("category");
                Integer category = null;
                String conditionsEXTRACT = request.getParameter("conditions");
                Integer conditions = null;
                String stateEXTRACT = request.getParameter("state");
                Integer state = null;
                if(stateEXTRACT!= null && !stateEXTRACT.equals("")){
                    state = Integer.parseInt(stateEXTRACT);
                }
                if (priceEXTRACT != null && !priceEXTRACT.isEmpty()) {
                    price = Float.parseFloat(priceEXTRACT);
                }

                if (categoryEXTRACT != null && !categoryEXTRACT.equals("")) {
                    category = Integer.parseInt(categoryEXTRACT);
                }

                if (conditionsEXTRACT != null && !conditionsEXTRACT.equals("")) {
                    conditions = Integer.parseInt(request.getParameter("conditions"));
                }

                if (title != null || price != null || description != null || city != null || category != null || conditions != null) {


                    String uploadPath = "C:\\Java\\LeBonPlanV2\\src\\main\\webapp\\img";
                    String uploadPathserver = request.getServletContext().getRealPath("") + File.separator + "img";
                    Part part = request.getPart("image_drop");
                    String fileName;
                    String picture = "vide";
                    if(part.getSubmittedFileName() !=null && !Objects.equals(part.getSubmittedFileName(), "")) {
                        fileName = part.getSubmittedFileName();
                        part.write(uploadPath + File.separator + fileName);
                        part.write(uploadPathserver + File.separator + fileName);
                        picture = "img" + File.separator + fileName;
                    }
                    daoBonPlan.editAd(idAD_ext, title, price, picture, description, city, category, conditions);
                    daoBonPlan.updateAdState(idAD_ext,state);
                }
            }
            String grade = String.valueOf(request.getSession().getAttribute("email"));
            if (daoBonPlan.checkAdmin(grade)) {
                if(session.getAttribute("moderation")!=null){
                    if(session.getAttribute("moderation").toString().equals("true")){
                        List<List> listAd;
                        listAd = daoBonPlan.filtreAd(null,null,null,null,null,null);
                        request.setAttribute("listAd",listAd);
                        request.getRequestDispatcher("/jsp/moderationAdView.jsp").forward(request, response);
                    }else {
                        session.setAttribute("moderation","false");
                        List<List> listAd;
                        listAd = daoBonPlan.filtreAd(null,null,null,null,null,null);
                        request.setAttribute("listAd",listAd);
                        request.getRequestDispatcher("/jsp/adminView.jsp").forward(request, response);
                    }
                } else {
                    session.setAttribute("moderation","false");
                    request.getRequestDispatcher("/jsp/adminView.jsp").forward(request, response);
                }            } else {
                daoBonPlan.updateAdState(idAD_ext,0);
                List<List> listAd;
                listAd = daoBonPlan.filtreAd(null,null,null,null,null,1);
                request.setAttribute("listAd",listAd);
                request.getRequestDispatcher("/jsp/clientView.jsp").forward(request, response);
            }


        }else {
            if(session.getAttribute("moderation")!=null){
                if(session.getAttribute("moderation").toString().equals("true")){
                    List<List> listInfoAd;
                    String idAD_STRING=request.getParameter("idAD");
                    listInfoAd = daoBonPlan.getADInfo(Integer.parseInt(String.valueOf(idAD_STRING)));
                    request.setAttribute("listInfoAd",listInfoAd);
                    request.getRequestDispatcher("/jsp/moderationEditAdView.jsp").forward(request, response);
                }
            }
            Object Owner = request.getSession().getAttribute("email");
            int id = daoBonPlan.emailToId((String) Owner);

            List<List> listAd = daoBonPlan.myAd(id);

            request.setAttribute("listAd", listAd);

            request.getRequestDispatcher("/jsp/editAd.jsp").forward(request, response);

        }
        return null;

    }
}
