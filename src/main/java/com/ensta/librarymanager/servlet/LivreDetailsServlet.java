package com.ensta.librarymanager.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.modele.Livre;
import com.ensta.librarymanager.services.implementation.LivreService;


@WebServlet("/livre_details")
public class LivreDetailsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private LivreService livreService = LivreService.getInstance();
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setAttribute("listeLivres", this.livreService.getList());

		} catch (ServiceException e) {
			e.printStackTrace();
		}
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/View/livre_details.jsp").forward(request, response);		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);		
		try{
            Livre livreUpdate = new Livre(Integer.parseInt(request.getParameter("id")), request.getParameter("titre"), request.getParameter("auteur"), request.getParameter("isbn"));
			livreService.update(livreUpdate);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}
