package LeBonPlanV2.servlets.actions;
import LeBonPlanV2.beans.DAOUtilisateur;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ShowDetailsAD implements Action{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, DAOUtilisateur daoBonPlan) throws ServletException, IOException {
        List<List> listInfoAd = new ArrayList<>();
        String idAD_STRING=request.getParameter("idAD");
        Integer idAD = Integer.parseInt(idAD_STRING);
        listInfoAd = daoBonPlan.getADInfo(idAD);
        System.out.println(listInfoAd);
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
