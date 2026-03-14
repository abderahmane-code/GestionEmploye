package servlet;

import dao.EmployeDAO;
import model.Employe;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/EmployeServlet")
public class EmployeServlet extends HttpServlet {

    EmployeDAO dao;

    public void init(){
        dao=new EmployeDAO();
    }

    protected void doPost(HttpServletRequest request,HttpServletResponse response)
    throws ServletException,IOException{

        String nom=request.getParameter("nom");
        String prenom=request.getParameter("prenom");
        String poste=request.getParameter("poste");
        double salaire=Double.parseDouble(request.getParameter("salaire"));

        Employe emp=new Employe(nom,prenom,poste,salaire);

        try{
            dao.insertEmploye(emp);
        }catch(Exception e){
            e.printStackTrace();
        }

        response.sendRedirect("list");
    }

    protected void doGet(HttpServletRequest request,HttpServletResponse response)
    throws ServletException,IOException{

        List<Employe> list=dao.selectAllEmployes();

        request.setAttribute("listeEmployes",list);

        RequestDispatcher dispatcher=
        request.getRequestDispatcher("employe-list.jsp");

        dispatcher.forward(request,response);
    }
}