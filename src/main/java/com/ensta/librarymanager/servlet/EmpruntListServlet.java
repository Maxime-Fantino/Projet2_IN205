package com.ensta.librarymanager.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.services.implementation.EmpruntService;


@WebServlet("/emprunt_list")
public class EmpruntListServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private EmpruntService empruntService = EmpruntService.getInstance();
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setAttribute("listeTousEmprunts", this.empruntService.getList());
			request.setAttribute("listeEmpruntsCurrent", this.empruntService.getListCurrent());


		} catch (ServiceException e) {
			e.printStackTrace();
		}
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/View/emprunt_list.jsp").forward(request, response);		
	}
}
