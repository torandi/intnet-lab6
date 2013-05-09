package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Order;
import model.Security;
import model.Trade;
import model.ValidationException;

public class IndexController extends HttpServlet {
	
	private String error = null;
	private String success = null;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String action = req.getParameter("action");

		
		try {
			if (action != null) {
				if (action.equals("addSecurity")) {
					// TODO Duplicate entries?
					Security secObj = new Security(); // Create new Security object
					// Smart, new update -> ID
					secObj.setName(req.getParameter("securityName")); // Fetch from field
					secObj.commit(); // Write object to database
					success = "The security "+secObj.getName()+" was created.";
				}
	
				if (action.equals("buySell")) {
	
					Order ordObj = new Order();
	
					ordObj.setSecurityId(Integer.parseInt(req
							.getParameter("security"))); // Id från dropDown värdet
					// Hämta från Sec
					ordObj.setType(Boolean.parseBoolean(req
							.getParameter("buyOrSell")));
					ordObj.setUid(req.getParameter("uid"));
					ordObj.setPrice(Float.parseFloat(req.getParameter("price")));
					ordObj.setAmount(Integer.parseInt(req.getParameter("amount")));
					ordObj.commit();
					if(ordObj.match()) {
						success = "Your order was placed, and a matching sell order was found.";
					} else {
						success = "Your order was placed.";
					}
				}
	
				if (action.equals("listSecurity")) {
	
					Trade trdObj = new Trade();
					/*
					 * if (Boolean.parseBoolean(req.getParameter("buyOrSell")) == true) { // Buy
					 * ordObj.setUid(req.getParameter("uid"));
					 * } else { // Sell
					 * ordObj.setUid(req.getParameter("uid"));
					 * }
					 */
				}
			}
		} catch (ValidationException e){
			success = null;
			error = e.getMessage();
		} catch (Exception e){
			success = null;
			error = "An error occurred: "+e.getMessage();
		}
		render(req, resp);
	};

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		/*
		 * ArrayList<Trade> trades = Trade.q().all();
		 * for (Trade t : trades) {
		 * System.out.println(t);
		 * }
		 */
		render(req, resp);

	}

	public void render(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		ArrayList<Security> dropDown = Security.q().all(); // Popullera Dropdown
		req.getServletContext().setAttribute("securities", dropDown);
		
		req.getServletContext().setAttribute("success", success);
		req.getServletContext().setAttribute("error", error);

		RequestDispatcher rd = req.getRequestDispatcher("index.jsp?message="
				+ req.getParameter("action"));
		rd.forward(req, resp);
	}
}