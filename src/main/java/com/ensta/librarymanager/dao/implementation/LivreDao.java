package com.ensta.librarymanager.dao.implementation;

import com.ensta.librarymanager.dao.interfaces.iLivreDao;
import com.ensta.librarymanager.exception.DaoException;
import com.ensta.librarymanager.modele.Livre;
import com.ensta.librarymanager.persistence.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class LivreDao implements iLivreDao {

    private static LivreDao instance;
    private LivreDao(){}
    public static LivreDao getInstance(){
        if (instance == null){
            instance = new LivreDao();
        }
        return instance;
    }

    public ArrayList<Livre> getList() throws DaoException, SQLException{
        Connection conn = ConnectionManager.getConnection();
        PreparedStatement stnt = conn.prepareStatement(
            "SELECT e.id AS id, idMembre, nom, prenom, adresse, email, telephone, abonnement, "
            + "idLivre, titre, auteur, isbn, dateEmprunt, dateRetour FROM emprunt AS e INNER JOIN "
            + "membre ON membre.id = e.idMembre INNER JOIN livre ON livre.id = e.idLivre ORDER BY dateRetour DESC;" );
        ResultSet listEmprunts = stnt.executeQuery();

        ArrayList<Livre> listRes = new ArrayList<Livre>();
        while(listEmprunts.next()){
            listRes.add(new Livre(listEmprunts.getInt("id"), listEmprunts.getString("titre"), listEmprunts.getString("auteur"), listEmprunts.getString("isbn")));
        }

        
        return listRes;
    }

	public Livre getById(int id) throws DaoException, SQLException{
        Connection conn = ConnectionManager.getConnection();
        PreparedStatement stnt = conn.prepareStatement( "SELECT id, titre, auteur, isbn FROM livre WHERE id = ?;" );
        stnt.setInt( 1, id );
        ResultSet livreResultSet = stnt.executeQuery();
        livreResultSet.next();
        
        Livre livre = new Livre(id, livreResultSet.getString("titre"), livreResultSet.getString("auteur"), livreResultSet.getString("isbn"));

        
        return livre;
    }

	public int create(String titre, String auteur, String isbn) throws DaoException, SQLException{
        Connection conn = ConnectionManager.getConnection();
        PreparedStatement stnt = conn.prepareStatement("INSERT INTO livre(titre, auteur, isbn) VALUES (?, ?, ?);");
        stnt.setString(1, titre);
        stnt.setString(2, auteur);
        stnt.setString(3, isbn);
        

        return stnt.executeUpdate();
    }

	public void update(Livre livre) throws DaoException, SQLException{
        Connection conn = ConnectionManager.getConnection();
        PreparedStatement stnt = conn.prepareStatement( "UPDATE livre SET titre = ?, auteur = ?, isbn = ? WHERE id = ?;" );
        stnt.setString(1, livre.getTitre());
        stnt.setString(2, livre.getAuteur());
        stnt.setString(3, livre.getIsbn());
        stnt.setInt(4, livre.getPrimaryKey());
        stnt.executeUpdate();
             
        
    }

	public void delete(int id) throws DaoException, SQLException{
        Connection conn = ConnectionManager.getConnection();
        PreparedStatement stnt = conn.prepareStatement( "DELETE FROM livre WHERE id = ?;" );
        stnt.setInt( 1, id );
        stnt.executeUpdate();

        
    }

	public int count() throws DaoException, SQLException{
        Connection conn = ConnectionManager.getConnection();
        PreparedStatement stnt = conn.prepareStatement( "SELECT COUNT(id) AS count FROM livre;" );
        ResultSet livreResultSet = stnt.executeQuery();
        livreResultSet.next();

        return livreResultSet.getInt(1);
    }
}
