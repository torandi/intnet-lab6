package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Order;
import model.Security;
import model.ValidationException;

public class IndexController extends HttpServlet {

	private String error;
	private String success;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String action = req.getParameter("action");
		error = null;
		success = null;

		try {
			if (action != null) {
				if (action.equals("addSecurity")) {
					// TODO Duplicate entries?
					Security secObj = new Security(); // Create new Security object
					// Smart, new update -> ID
					secObj.setName(req.getParameter("securityName")); // Fetch from field
					secObj.commit(); // Write object to database
					success = "The security " + secObj.getName()
							+ " was created.";
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
					ordObj.setAmount(Integer.parseInt(req
							.getParameter("amount")));
					ordObj.commit();
					if (ordObj.match()) {
						success = "Your order was placed, and a matching sell order was found.";
					} else {
						success = "Your order was placed.";
					}
				}

				if (action.equals("listHistory")) {
					Integer s_id = Integer.parseInt(req
							.getParameter("security"));
					Security s = Security.q().from_id(s_id);
					req.getServletContext().setAttribute("history",
							s.getTrades());
					req.getServletContext().setAttribute("history_security",
							s.getName());
				}
			}
		} catch (ValidationException e) {
			success = null;
			error = e.getMessage();
		} catch (Exception e) {
			success = null;
			error = "An error occurred: " + e.getMessage();
		}
		render(req, resp);
	};

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		error = null;
		success = null;

		render(req, resp);
	}

	private void render(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		ArrayList<Security> dropDown = new ArrayList<Security>();
		try {
			dropDown = Security.q().all();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		req.getServletContext().setAttribute("securities", dropDown);

		req.getServletContext().setAttribute("success", success);
		req.getServletContext().setAttribute("error", error);

		RequestDispatcher rd = req.getRequestDispatcher("index.jsp?message="
				+ req.getParameter("action"));
		rd.forward(req, resp);
	}
}