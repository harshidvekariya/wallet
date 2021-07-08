/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;  

import dao.UserDao;

@WebServlet("/login")
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDao loginDao;

    public void init() {
        loginDao = new UserDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.sendRedirect("login.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try {
            authenticate(request, response);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void authenticate(HttpServletRequest request, HttpServletResponse response)
    throws Exception {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
//        if (loginDao.validate(username, password)) {
//            RequestDispatcher dispatcher = request.getRequestDispatcher("dashboard.jsp");
//            System.out.println("Login success!!!");
//            request.setAttribute("username", username);
//            request.setAttribute("message", "Login Success.....");
//            dispatcher.forward(request, response);
//        } 
      if(username.trim().length() >= 0 && username != null &&
       password.trim().length() >= 0 && password != null && password.equals("1234") && username.equals("login1")) {
         RequestDispatcher dispatcher = request.getRequestDispatcher("dashboard.jsp");
         HttpSession session=request.getSession();  
         session.setAttribute("username",username);    
            request.setAttribute("message", "Login Success.....");
            String action = request.getServletPath();
           System.out.println(action);
            dispatcher.forward(request, response);
       }
        else {
            request.setAttribute("errorMessage", "Wrong password or username..");
           request.getRequestDispatcher("/login.jsp").forward(request, response);
        
        }
    }
}