package	com.ensta.librarymanager.services.interfaces;

import java.util.List;

import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.modele.Membre;
import com.ensta.librarymanager.utils.Abonnement;

public interface iMembreService {

	public List<Membre> getList() throws ServiceException;
	public List<Membre> getListMembreEmpruntPossible() throws ServiceException;
	public Membre getById(int id) throws ServiceException;
	public int create(String nom, String prenom, String adresse, String email, String telephone, Abonnement abonnement) throws ServiceException;
	public void update(Membre membre) throws ServiceException;
	public void delete(int id) throws ServiceException;
	public int count() throws ServiceException;
}
