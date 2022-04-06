package com.ensta.librarymanager.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.modele.Livre;
import com.ensta.librarymanager.modele.Membre;
import com.ensta.librarymanager.services.implementation.EmpruntService;
import com.ensta.librarymanager.services.implementation.LivreService;
import com.ensta.librarymanager.services.implementation.MembreService;
import com.ensta.librarymanager.utils.Abonnement;


@WebServlet("/membre_details")
public class MembreDetailsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private MembreService membreService = MembreService.getInstance();
	private EmpruntService empruntService = EmpruntService.getInstance();
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setAttribute("listeMembres", this.membreService.getList());
			request.setAttribute("emprunts", this.empruntService.getList());

		} catch (ServiceException e) {
			e.printStackTrace();
		}
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/View/membre_details.jsp").forward(request, response);		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);		
		try{
            Membre membreUpdate = new Membre(Integer.parseInt(request.getParameter("idMembre")), request.getParameter("nom"), request.getParameter("prenom"), request.getParameter("adresse"), request.getParameter("email"), request.getParameter("telephone"), Abonnement.BASIC);
			membreService.update(membreUpdate);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}
