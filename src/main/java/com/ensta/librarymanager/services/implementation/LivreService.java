package com.ensta.librarymanager.services.implementation;

import com.ensta.librarymanager.services.interfaces.iLivreService;
import com.ensta.librarymanager.dao.implementation.LivreDao;
import com.ensta.librarymanager.modele.Livre;
import com.ensta.librarymanager.exception.ServiceException;
import java.util.ArrayList;
import java.util.Iterator;


public class LivreService implements iLivreService{

    private static LivreService instance;
    private LivreService(){}
    public static LivreService getInstance(){
        if (instance == null){
            instance = new LivreService();
        }
        return instance;
    }

    public ArrayList<Livre> getList() throws ServiceException{
        try{
            return LivreDao.getInstance().getList();
        } catch (Exception e){ 
            e.printStackTrace();
            throw new ServiceException();
        }
    }

	public ArrayList<Livre> getListDispo() throws ServiceException{
        try{
            ArrayList<Livre> listeLivresDispos =  LivreDao.getInstance().getList();
            for (Iterator<Livre> iterator = listeLivresDispos.iterator(); iterator.hasNext(); ) {
                Livre livre = iterator.next();
                if(EmpruntService.getInstance().isLivreDispo(livre.getPrimaryKey())) {
                    iterator.remove();
                }
            }
            return listeLivresDispos;
        } catch (Exception e){ 
            e.printStackTrace();
            throw new ServiceException();
        }
    }

	public Livre getById(int id) throws ServiceException{
        try{
            return LivreDao.getInstance().getById(id);
        } catch (Exception e){ 
            e.printStackTrace();
            throw new ServiceException();
        }
    }

	public int create(String titre, String auteur, String isbn) throws ServiceException{
        try{
            if(titre == ""){throw new ServiceException();}
            return LivreDao.getInstance().create(titre, auteur, isbn);
        } catch (Exception e){ 
            e.printStackTrace();
            throw new ServiceException();
        }
    }

	public void update(Livre livre) throws ServiceException{
        try{
            if(livre.getTitre() == ""){throw new ServiceException();}
            LivreDao.getInstance().update(livre);
        } catch (Exception e){ 
            e.printStackTrace();
            throw new ServiceException();
        }
    }

	public void delete(int id) throws ServiceException{
        try{
            LivreDao.getInstance().delete(id);
        } catch (Exception e){ 
            e.printStackTrace();
            throw new ServiceException();
        }
    }

	public int count() throws ServiceException{
        try{
            return LivreDao.getInstance().count();
        } catch (Exception e){ 
            e.printStackTrace();
            throw new ServiceException();
        }
    }
}
