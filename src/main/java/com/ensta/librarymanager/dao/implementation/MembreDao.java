package com.ensta.librarymanager.dao.implementation;

import com.ensta.librarymanager.dao.interfaces.iMembreDao;
import com.ensta.librarymanager.exception.DaoException;
import com.ensta.librarymanager.modele.Membre;
import com.ensta.librarymanager.utils.Abonnement;
import com.ensta.librarymanager.persistence.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MembreDao implements iMembreDao {

    private static MembreDao instance;
    private MembreDao(){}
    public static MembreDao getInstance(){
        if (instance == null){
            instance = new MembreDao();
        }
        return instance;
    }

    public ArrayList<Membre> getList() throws DaoException, SQLException{
        Connection conn = ConnectionManager.getConnection();
        PreparedStatement stnt = conn.prepareStatement(
            "SELECT id, nom, prenom, adresse, email, telephone, abonnement "
            + "FROM membre "
            + "ORDER BY nom, prenom;" );
        ResultSet listMembres = stnt.executeQuery();

        ArrayList<Membre> listRes = new ArrayList<Membre>();
        while(listMembres.next()){
            listRes.add(new Membre(listMembres.getInt("id"), listMembres.getString("nom"), listMembres.getString("prenom"), listMembres.getString("adresse"), listMembres.getString("email"), listMembres.getString("telephone"), Abonnement.valueOf(listMembres.getString("abonnement"))));
        }

        
        return listRes;
    }

	public Membre getById(int id) throws DaoException, SQLException{
        Connection conn = ConnectionManager.getConnection();
        PreparedStatement stnt = conn.prepareStatement( "SELECT id, nom, prenom, adresse, email, telephone, abonnement FROM membre WHERE id = ?;" );
        stnt.setInt( 1, id );
        ResultSet membreResultSet = stnt.executeQuery();
        membreResultSet.next();
        
        Membre membre = new Membre(membreResultSet.getInt("id"), membreResultSet.getString("nom"), membreResultSet.getString("prenom"), membreResultSet.getString("adresse"), membreResultSet.getString("email"), membreResultSet.getString("telephone"), Abonnement.valueOf(membreResultSet.getString("abonnement")));

        
        return membre;
    }

	public int create(String nom, String prenom, String adresse, String email, String telephone, Abonnement abonnement) throws DaoException, SQLException{
        Connection conn = ConnectionManager.getConnection();
        PreparedStatement stnt = conn.prepareStatement("INSERT INTO membre(nom, prenom, adresse, email, telephone, abonnement) VALUES (?, ?, ?, ?, ?, ?);");
        stnt.setString(1, nom);
        stnt.setString(2, prenom);
        stnt.setString(3, adresse);
        stnt.setString(4, adresse);
        stnt.setString(5, telephone);
        stnt.setString(6, abonnement.name());

        

        return stnt.executeUpdate();
    }

	public void update(Membre membre) throws DaoException, SQLException{
        Connection conn = ConnectionManager.getConnection();
        PreparedStatement stnt = conn.prepareStatement( "UPDATE membre SET nom = ?, prenom = ?, adresse = ?, email = ?, telephone = ?, abonnement = ? WHERE id = ?;" );
        stnt.setString(1, membre.getNom());
        stnt.setString(2, membre.getPrenom());
        stnt.setString(3, membre.getAdresse());
        stnt.setString(4, membre.getEmail());
        stnt.setString(5, membre.getTelephone());
        stnt.setString(6, membre.getAbonnement().name());
        stnt.setInt(6, membre.getPrimaryKey());
        stnt.executeUpdate();
             
        
    }

	public void delete(int id) throws DaoException, SQLException{
        Connection conn = ConnectionManager.getConnection();
        PreparedStatement stnt = conn.prepareStatement( "DELETE FROM membre WHERE id = ?;" );
        stnt.setInt( 1, id );
        stnt.executeUpdate();

        
    }

	public int count() throws DaoException, SQLException{
        Connection conn = ConnectionManager.getConnection();
        PreparedStatement stnt = conn.prepareStatement( "SELECT COUNT(id) AS count FROM membre;" );
        ResultSet membreResultSet = stnt.executeQuery();
        membreResultSet.next();

        
        return membreResultSet.getInt(1);
    }
}
