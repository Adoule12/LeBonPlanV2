package LeBonPlanV2.servlets;

import LeBonPlanV2.beans.DAOFactory;
import LeBonPlanV2.beans.DAOUtilisateur;
import LeBonPlanV2.servlets.actions.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
@WebServlet(name = "Controller", value = "/Controller")
public class Controller extends HttpServlet {

    private final Map<String, Action> actionMap = new HashMap<>();
    private DAOUtilisateur daoBonPlan;

    @Override
    public void init() {

        actionMap.put("login", new Login());
        actionMap.put("logout", new Logout());
        actionMap.put("signin", new Signin());
        actionMap.put("buy", new Buy());
        actionMap.put("deleteUser", new DeleteUser());
        actionMap.put("editUser", new EditUser());
        actionMap.put("home", new Home());
        actionMap.put("postAd", new PostAd());
        actionMap.put("editAd", new EditAd());
        actionMap.put("deleteAd", new DeleteAd());
        actionMap.put("switchL", new SwitchL());
        actionMap.put("switchR", new SwitchR());
        actionMap.put("adAdmin", new AdAdmin());
        actionMap.put("goViews", new GoViews());
        actionMap.put("showAd", new ShowAd());
        actionMap.put("showDetailsAD", new ShowDetailsAD());
        actionMap.put("goProfil", new GoProfil());
        actionMap.put("moderationAD", new GoModerationAD());
        actionMap.put("moderationUser", new ModerationUser());
        actionMap.put("adCompte", new AdCompte());


        DAOFactory daoFactory = DAOFactory.getInstance();
        daoBonPlan = daoFactory.getUtilisateurDao("MariaDB");


    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        System.out.println(id);
        if(id == null) {
            id="home";
        }
        String message = "Error";
        Action action = actionMap.get(id);
        if(action != null) {
            message = action.execute(request, response, daoBonPlan);
        } else {
            actionMap.get("home").execute(request, response, daoBonPlan);
        }
        request.setAttribute("content",message);
    }
}
