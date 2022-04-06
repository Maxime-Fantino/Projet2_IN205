package com.ensta.librarymanager.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ensta.librarymanager.services.implementation.LivreService;
import com.ensta.librarymanager.services.implementation.MembreService;
import com.ensta.librarymanager.utils.Abonnement;


@WebServlet("/membre_add")
public class MembreAddServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private MembreService membreService = MembreService.getInstance();
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		this.getServletContext().getRequestDispatcher("/WEB-INF/View/membre_add.jsp").forward(request, response);		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);		
		try{
			membreService.create(request.getParameter("nom"), request.getParameter("prenom"), request.getParameter("adresse"), request.getParameter("email"), request.getParameter("telephone"), Abonnement.BASIC);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}
