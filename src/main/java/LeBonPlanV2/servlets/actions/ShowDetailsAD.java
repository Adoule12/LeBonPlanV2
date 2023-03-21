package LeBonPlanV2.servlets.actions;
import LeBonPlanV2.beans.DAOUtilisateur;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Action pour afficher les informations complémentaire d'une annonce
 */
public class ShowDetailsAD implements Action{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, DAOUtilisateur daoBonPlan) throws ServletException, IOException {
        List<List> listInfoAd;
        String idAD_STRING=request.getParameter("idAD");
        Integer idAD = Integer.parseInt(idAD_STRING);
        listInfoAd = daoBonPlan.getADInfo(idAD);
        request.setAttribute("listInfoAd",listInfoAd);
        HttpSession session = request.getSession( true );
        if(session.getAttribute("moderation")!=null){
            if(session.getAttribute("moderation").toString().equals("true")){
                request.setAttribute("from","moderationDetailsAD");
            }
        }else{
            request.setAttribute("from","false");
        }
        request.getRequestDispatcher("/jsp/adInformations.jsp").forward(request, response);
        return null;
    }
}
