package com.ensta.librarymanager.services.implementation;

import com.ensta.librarymanager.services.interfaces.iMembreService;
import com.ensta.librarymanager.utils.Abonnement;

import org.h2.store.LobStorageBackend;

import com.ensta.librarymanager.dao.implementation.MembreDao;
import com.ensta.librarymanager.modele.Membre;
import com.ensta.librarymanager.exception.ServiceException;
import java.util.ArrayList;
import java.util.Iterator;

public class MembreService implements iMembreService{

    private static MembreService instance;
    private MembreService(){}
    public static MembreService getInstance(){
        if (instance == null){
            instance = new MembreService();
        }
        return instance;
    }

    public ArrayList<Membre> getList() throws ServiceException{
        try{
            return MembreDao.getInstance().getList();
        } catch (Exception e){ 
            e.printStackTrace();
            throw new ServiceException();
        }
    }

	public ArrayList<Membre> getListMembreEmpruntPossible() throws ServiceException{
        try{
            ArrayList<Membre> listeMembre = MembreDao.getInstance().getList();
            for (Iterator<Membre> iterator = listeMembre.iterator(); iterator.hasNext(); ) {
                Membre membre = iterator.next();
                if(!EmpruntService.getInstance().isEmpruntPossible(membre)) {
                    iterator.remove();
                }
            }
            return listeMembre;
        } catch (Exception e){ 
            e.printStackTrace();
            throw new ServiceException();
        }
    }

	public Membre getById(int id) throws ServiceException{
        try{
            return MembreDao.getInstance().getById(id);
        } catch (Exception e){ 
            e.printStackTrace();
            throw new ServiceException();
        }
    }

	public int create(String nom, String prenom, String adresse, String email, String telephone, Abonnement abonnement) throws ServiceException{
        try{
            if(nom == "" || prenom == ""){throw new ServiceException();}
            // Le Nom est bien mis en majuscule
            return MembreDao.getInstance().create(nom.toUpperCase(), prenom, adresse, email, telephone, abonnement);
        } catch (Exception e){ 
            e.printStackTrace();
            throw new ServiceException();
        }
    }

	public void update(Membre membre) throws ServiceException{
        try{
            if(membre.getNom() == "" || membre.getPrenom() == ""){throw new ServiceException();}
            // Le Nom est bien mis en majuscule
            membre.setNom(membre.getNom().toUpperCase());
            MembreDao.getInstance().update(membre);
        } catch (Exception e){ 
            e.printStackTrace();
            throw new ServiceException();
        }
    }

	public void delete(int id) throws ServiceException{
        try{
            MembreDao.getInstance().delete(id);
        } catch (Exception e){ 
            e.printStackTrace();
            throw new ServiceException();
        }
    }

	public int count() throws ServiceException{
        try{
            return MembreDao.getInstance().count();
        } catch (Exception e){ 
            e.printStackTrace();
            throw new ServiceException();
        }
    }
}
