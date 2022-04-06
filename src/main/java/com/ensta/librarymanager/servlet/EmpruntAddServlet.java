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
import com.ensta.librarymanager.services.implementation.LivreService;
import com.ensta.librarymanager.services.implementation.MembreService;


@WebServlet("/emprunt_add")
public class EmpruntAddServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private MembreService membreService = MembreService.getInstance();
	private LivreService livreService = LivreService.getInstance();
	private EmpruntService empruntService = EmpruntService.getInstance();
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setAttribute("listeLivresDispos", this.livreService.getListDispo());
			request.setAttribute("listeMembresDispos", this.membreService.getListMembreEmpruntPossible());

		} catch (ServiceException e) {
			e.printStackTrace();
		}
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/View/emprunt_add.jsp").forward(request, response);		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
		try{
			empruntService.create(Integer.parseInt(request.getParameter("idMembre")), Integer.parseInt(request.getParameter("idLivre")), LocalDate.now());
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}
