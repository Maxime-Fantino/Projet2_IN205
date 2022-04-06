package com.ensta.librarymanager.servlet;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.services.implementation.EmpruntService;


@WebServlet("/emprunt_return")
public class EmpruntReturnServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private EmpruntService empruntService = EmpruntService.getInstance();
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setAttribute("listeEmpruntsCurrent", this.empruntService.getListCurrent());

		} catch (ServiceException e) {
			e.printStackTrace();
		}
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/View/emprunt_return.jsp").forward(request, response);		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
		try{
			empruntService.returnBook(Integer.parseInt(request.getParameter("idEmprunt")));
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}
