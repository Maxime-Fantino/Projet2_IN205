package com.ensta.librarymanager.dao.interfaces;

import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import com.ensta.librarymanager.exception.DaoException;
import com.ensta.librarymanager.modele.Emprunt;

public interface iEmpruntDao {
	public List<Emprunt> getList() throws DaoException, SQLException;
	public List<Emprunt> getListCurrent() throws DaoException, SQLException;
	public List<Emprunt> getListCurrentByMembre(int idMembre) throws DaoException, SQLException;
	public List<Emprunt> getListCurrentByLivre(int idLivre) throws DaoException, SQLException;
	public Emprunt getById(int id) throws DaoException, SQLException;
	public void create(int idMembre, int idLivre, LocalDate dateEmprunt) throws DaoException, SQLException;
	public void update(Emprunt emprunt) throws DaoException, SQLException;
	public int count() throws DaoException, SQLException;
}
