/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import dao.AccountDao;
import dao.CurrencyDao;
import dao.TransferDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.SecureRandom;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;
import model.Currency;
import model.Transfer;

@WebServlet("/")
public class AccountController extends HttpServlet {

   private static final long serialVersionUID = 1L;
    private AccountDao AccountDao;
    private CurrencyDao CurrencyDao;
    static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    static SecureRandom rnd = new SecureRandom();
    private TransferDao TransferDao;
    public void init() {
        AccountDao = new AccountDao();
        TransferDao = new TransferDao();
        CurrencyDao=new CurrencyDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String action = request.getServletPath();
         System.out.println(action);
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String action = request.getServletPath();
       System.out.println(action);
        try {
            switch (action) {
               
                case "/insert":
                    insertAccount(request, response);
                    break;
                case "/delete":
                    deleteAccount(request, response);
                    break;
                
                case "/update":
                    updateAccount(request, response);
                    break;
                case "/transfer":
                    insertTransfer(request, response);
                    break;
                case "/transfer-list":
                    listTransfer(request, response);
                    break;
                case "/list":
                    listAccount(request, response);
                    break;
                case "/newtransfer":
                    showNewForm(request, response);
                    break;
                case "/newacc":
                    showAddAccForm(request, response);
                    break;
                case "/logout":
                    Logout(request, response);
                    break;
                default:
                   RequestDispatcher dispatcher = request.getRequestDispatcher("wallet/login.jsp");
                   dispatcher.forward(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listAccount(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException {
        List <Account> listAcc = AccountDao.getAllAcc();
        request.setAttribute("listAcc", listAcc);
      
        RequestDispatcher dispatcher = request.getRequestDispatcher("/viewaccounts.jsp");
        dispatcher.forward(request, response);
    }
   
    public String RandAcc(int len){
    StringBuilder sb = new StringBuilder(len);
      for(int i = 0; i < len; i++)
      sb.append(AB.charAt(rnd.nextInt(AB.length())));
      return sb.toString();
    }
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
         List <Account> listAcc = AccountDao.getAllAcc();
        request.setAttribute("listAcc", listAcc);
		RequestDispatcher dispatcher = request.getRequestDispatcher("transfer.jsp");
		dispatcher.forward(request, response);
	}
    private void showAddAccForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
         
        List <Currency> listCurrens = CurrencyDao.getAllCurrency();
        request.setAttribute("listAcc", listCurrens);
		RequestDispatcher dispatcher = request.getRequestDispatcher("addaccount.jsp");
		dispatcher.forward(request, response);
	}

    private void insertAccount(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        String currency = request.getParameter("currency");
        HttpSession session1=request.getSession();
        double balance = Double.parseDouble(request.getParameter("amount"));
       String accname=(String)session1.getAttribute("username");
         SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String created_at=dateFormat.format(new Date());
        String accno=RandAcc(8);
        Account account = new Account(accno,balance,accname,currency,created_at);
        AccountDao.saveAccount(account);
        response.sendRedirect("list");
    }

    private void updateAccount(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
         HttpSession session1=request.getSession();
        int id = Integer.parseInt(request.getParameter("id"));
        String currency = request.getParameter("currency");
        double balance = Double.parseDouble(request.getParameter("amount"));
        String accname=(String)session1.getAttribute("username");
         SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String created_at=dateFormat.format(new Date());
        String accno=RandAcc(8);
        Account account = new Account(id,accno,balance,accname,currency,created_at);

        AccountDao.updateAccount(account);
        response.sendRedirect("/viewaccounts.jsp");
    }
      private void listTransfer(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException {
        List <Transfer> listTrans = TransferDao.getAllTrans();
        request.setAttribute("listTrans", listTrans);
      
        RequestDispatcher dispatcher = request.getRequestDispatcher("/viewtransfers.jsp");
        dispatcher.forward(request, response);
    }

  
    private void insertTransfer(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        String to_acc = request.getParameter("to_account");
        double amount = Double.parseDouble(request.getParameter("amount"));
         SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String created_at=dateFormat.format(new Date());
        String from_acc=request.getParameter("from_account");
        Transfer transfer = new Transfer(to_acc,from_acc,amount,created_at);
        TransferDao.saveTransfer(transfer);
        response.sendRedirect("transfer-list");
    }
    
    protected void Logout(HttpServletRequest request, HttpServletResponse response)  
                                throws ServletException, IOException {  
            response.setContentType("text/html");  
            PrintWriter out=response.getWriter();  
              
            //request.getRequestDispatcher("link.html").include(request, response);  
              
            HttpSession session=request.getSession();  
            session.invalidate();  
             RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
            dispatcher.forward(request, response);
            out.print("You are successfully logged out!");  
              
            out.close();  
    }  

    private void deleteAccount(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        AccountDao.deleteAccount(id);
        response.sendRedirect("list");
    }
}
