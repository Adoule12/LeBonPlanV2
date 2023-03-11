package LeBonPlanV2.servlets.actions;

import LeBonPlanV2.beans.DAOUtilisateur;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ModerationUser implements Action{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, DAOUtilisateur daoBonPlan) throws ServletException, IOException {
        String idUserBan = request.getParameter("debannir");
        String idUserunBan = request.getParameter("bannir");
        String idUserForDelete = request.getParameter("deleteUser");
        if(idUserunBan!=null) {

            System.out.println("iduser :" + idUserunBan+" a bannir");
            daoBonPlan.ban(Integer.parseInt(idUserunBan),1);
        }
        if(idUserBan!=null) {
            System.out.println("iduser :" + idUserBan+" a débannir");
            daoBonPlan.ban(Integer.parseInt(idUserBan),0);
        }
        if(idUserForDelete!=null) {
            System.out.println("iduser :" + idUserForDelete+" a delete");
            daoBonPlan.ban(Integer.parseInt(idUserForDelete),3);
        }
        List<List> listban = daoBonPlan.userBan(true);
        List<List> listunban = daoBonPlan.userBan(false);
        request.setAttribute("listuser", listunban);
        request.setAttribute("listblacklisted", listban);

        request.getRequestDispatcher("/jsp/moderationUser.jsp").forward(request, response);

        return null;
    }
}
