package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IndexController extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		/*
		 * ArrayList<Trade> trades = Trade.q().all();
		 * for (Trade t : trades) {
		 * System.out.println(t);
		 * }
		 */

		String msg = null; // Error handeling

		if (req.getParameter("action").equals("addSecurity")) {
			msg = "addSecurity";
			// TODO Handle adding new Securities
		}

		if (req.getParameter("action").equals("buySell")) {
			msg = "buySell";
			// TODO Handle buy and sell
		}

		if (req.getParameter("action").equals("listSecurity")) {
			msg = "listSecurity";
			// TODO Handle listing
		}

		try {
			RequestDispatcher rd = req
					.getRequestDispatcher("index.jsp?message=" + msg);
			rd.forward(req, resp);
		} catch (ServletException e) {
			System.out.print(e.getMessage());
		} catch (IOException e) {
			System.out.print(e.getMessage());
		}
	}
}