package com.ensta.librarymanager.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.services.implementation.MembreService;


@WebServlet("/membre_list")
public class MembreListServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private MembreService membreService = MembreService.getInstance();
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setAttribute("listeMembres", this.membreService.getList());

		} catch (ServiceException e) {
			e.printStackTrace();
		}
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/View/membre_list.jsp").forward(request, response);		
	}
}
