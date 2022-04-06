package com.ensta.librarymanager.services.implementation;

import com.ensta.librarymanager.dao.implementation.EmpruntDao;
import com.ensta.librarymanager.dao.implementation.LivreDao;
import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.modele.Emprunt;
import com.ensta.librarymanager.modele.Membre;
import com.ensta.librarymanager.services.interfaces.iEmpruntService;
import com.ensta.librarymanager.persistence.ConnectionManager;
import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class EmpruntService implements iEmpruntService{

    private static EmpruntService instance;
    private EmpruntService(){}
    public static EmpruntService getInstance(){
        if (instance == null){
            instance = new EmpruntService();
        }
        return instance;
    }

    public ArrayList<Emprunt> getList() throws ServiceException{
        try{
            return EmpruntDao.getInstance().getList();
        } catch (Exception e){ 
            e.printStackTrace();
            throw new ServiceException();
        }
    }

	public ArrayList<Emprunt> getListCurrent() throws ServiceException{
        try{
            return EmpruntDao.getInstance().getListCurrent();
        } catch (Exception e){ 
            e.printStackTrace();
            throw new ServiceException();
        }
    }

	public ArrayList<Emprunt> getListCurrentByMembre(int idMembre) throws ServiceException{
        try{
            return EmpruntDao.getInstance().getListCurrentByMembre(idMembre);
        } catch (Exception e){ 
            e.printStackTrace();
            throw new ServiceException();
        }
    }
	public ArrayList<Emprunt> getListCurrentByLivre(int idLivre) throws ServiceException{
        try{
            return EmpruntDao.getInstance().getListCurrentByLivre(idLivre);
        } catch (Exception e){ 
            e.printStackTrace();
            throw new ServiceException();
        }
    }

	public Emprunt getById(int id) throws ServiceException{
        try{
            return EmpruntDao.getInstance().getById(id);
        } catch (Exception e){ 
            e.printStackTrace();
            throw new ServiceException();
        }
    }

	public void create(int idMembre, int idLivre, LocalDate dateEmprunt) throws ServiceException{
        try{
            EmpruntDao.getInstance().create(idMembre, idLivre, dateEmprunt);
        } catch (Exception e){ 
            e.printStackTrace();
            throw new ServiceException();
        }
    }

	public void returnBook(int id) throws ServiceException{
        try{
            Emprunt emprunt = EmpruntDao.getInstance().getById(id);
            emprunt.setDateRetour(LocalDate.now());
            EmpruntDao.getInstance().update(emprunt);
        } catch (Exception e){ 
            e.printStackTrace();
            throw new ServiceException();
        }
    }

	public int count() throws ServiceException{
        try{
            return EmpruntDao.getInstance().count();
        } catch (Exception e){ 
            e.printStackTrace();
            throw new ServiceException();
        }
    }
    
	public boolean isLivreDispo(int idLivre) throws ServiceException{
        try{
            return EmpruntDao.getInstance().getListCurrentByLivre(idLivre).size() == 0;
        } catch (Exception e){ 
            e.printStackTrace();
            throw new ServiceException();
        }
    }

	public boolean isEmpruntPossible(Membre membre) throws ServiceException{
        try{
            return EmpruntDao.getInstance().getListCurrentByMembre(membre.getPrimaryKey()).size() < membre.getAbonnement().capaciteEmprunt;
        } catch (Exception e){ 
            e.printStackTrace();
            throw new ServiceException();
        }
    }
}
