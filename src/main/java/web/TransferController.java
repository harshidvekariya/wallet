/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;
import dao.TransferDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Transfer;
import com.fasterxml.jackson.databind.ObjectMapper; 
import com.fasterxml.jackson.databind.ObjectWriter;
import com.google.gson.Gson;  
import java.util.HashMap;
import java.util.Map;
@WebServlet("/transfers")
public class TransferController extends HttpServlet {

   private static final long serialVersionUID = 1L;
    private TransferDao TransferDao;
    public void init() {
        TransferDao = new TransferDao();
    }

 private void sendAsJson(HttpServletResponse response,Object obj) throws IOException {
		
		response.setContentType("application/json");
		
		String json = new Gson().toJson(obj);
                //ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
                //String json = ow.writeValueAsString(obj);
		     
		PrintWriter out = response.getWriter();
		  
		out.print(json);
		out.flush();
	}
 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		 String pathInfo = request.getServletPath();
		List <Transfer> listTrans = TransferDao.getAllTrans();
//                  Map<Integer, String> map = new HashMap<>();
//               for (Transfer stu : listTrans) {
//                map.put(stu.getId(), stu.getToAcc());
//                }
		if(pathInfo.equals("/transfers")){
                System.out.println(pathInfo);
			sendAsJson(response, listTrans);
			return;
		}

//		String[] splits = pathInfo.split("/transfers");
//		
//		if(splits.length != 2) {
//			
//			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
//			return;
//		}

//		String modelId = splits[1];
//		
//		if(!map.containsKey(modelId)) {
//			
//			response.sendError(HttpServletResponse.SC_NOT_FOUND);
//			return;
//		}
//		
//		sendAsJson(response, map.get(modelId));

		
		
		
	}
}
