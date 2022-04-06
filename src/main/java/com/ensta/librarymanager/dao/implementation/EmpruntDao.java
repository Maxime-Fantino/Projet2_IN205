package com.ensta.librarymanager.dao.implementation;

import com.ensta.librarymanager.dao.interfaces.iEmpruntDao;
import com.ensta.librarymanager.exception.DaoException;
import com.ensta.librarymanager.modele.Emprunt;
import com.ensta.librarymanager.dao.implementation.MembreDao;
import com.ensta.librarymanager.persistence.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Date;

public class EmpruntDao implements iEmpruntDao {

    private static EmpruntDao instance;
    private EmpruntDao(){}
    public static EmpruntDao getInstance(){
        if (instance == null){
            instance = new EmpruntDao();
        }
        return instance;
    }


    public ArrayList<Emprunt> getList() throws DaoException, SQLException {
        Connection conn = ConnectionManager.getConnection();
        PreparedStatement stnt = conn.prepareStatement(
            "SELECT e.id AS id, idMembre, nom, prenom, adresse, email, "
            + "telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, "
            + "dateRetour "
            + "FROM emprunt AS e "
            + "INNER JOIN membre ON membre.id = e.idMembre "
            + "INNER JOIN livre ON livre.id = e.idLivre "
            + "ORDER BY dateRetour DESC;");
        ResultSet listEmprunts = stnt.executeQuery();

        ArrayList<Emprunt> listRes = new ArrayList<Emprunt>();
        while(listEmprunts.next()){
            Date dateRetour = listEmprunts.getDate("dateRetour");
            listRes.add(new Emprunt(listEmprunts.getInt("id"), MembreDao.getInstance().getById(listEmprunts.getInt("idMembre")), LivreDao.getInstance().getById(listEmprunts.getInt("idLivre")), listEmprunts.getDate("dateEmprunt").toLocalDate(), (dateRetour==null)? null:dateRetour.toLocalDate()));
        }

        
        return listRes;
    };

    public ArrayList<Emprunt> getListCurrent() throws DaoException, SQLException{
        Connection conn = ConnectionManager.getConnection();
        PreparedStatement stnt = conn.prepareStatement(
            "SELECT e.id AS id, idMembre, nom, prenom, adresse, email, "
            + "telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, "
            + "dateRetour "
            + "FROM emprunt AS e "
            + "INNER JOIN membre ON membre.id = e.idMembre "
            + "INNER JOIN livre ON livre.id = e.idLivre "
            + "WHERE dateRetour IS NULL;");
        ResultSet listEmprunts = stnt.executeQuery();

        ArrayList<Emprunt> listRes = new ArrayList<Emprunt>();
        while(listEmprunts.next()){
            Date dateRetour = listEmprunts.getDate("dateRetour");
            listRes.add(new Emprunt(listEmprunts.getInt("id"), MembreDao.getInstance().getById(listEmprunts.getInt("idMembre")), LivreDao.getInstance().getById(listEmprunts.getInt("idLivre")), listEmprunts.getDate("dateEmprunt").toLocalDate(), (dateRetour==null)? null:dateRetour.toLocalDate()));
        }

        
        return listRes;
    };
    
    public ArrayList<Emprunt> getListCurrentByMembre( int idMembre ) throws DaoException, SQLException{
        Connection conn = ConnectionManager.getConnection();
        PreparedStatement stnt = conn.prepareStatement(
            "SELECT e.id AS id, idMembre, nom, prenom, adresse, email, "
            + "telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, "
            + "dateRetour "
            + "FROM emprunt AS e "
            + "INNER JOIN membre ON membre.id = e.idMembre "
            + "INNER JOIN livre ON livre.id = e.idLivre "
            + "WHERE dateRetour IS NULL AND membre.id = ?;");
        stnt.setInt( 1, idMembre );
        ResultSet listEmprunts = stnt.executeQuery();

        ArrayList<Emprunt> listRes = new ArrayList<Emprunt>();
        while(listEmprunts.next()){
            Date dateRetour = listEmprunts.getDate("dateRetour");
            listRes.add(new Emprunt(listEmprunts.getInt("id"), MembreDao.getInstance().getById(listEmprunts.getInt("idMembre")), LivreDao.getInstance().getById(listEmprunts.getInt("idLivre")), listEmprunts.getDate("dateEmprunt").toLocalDate(), (dateRetour==null)? null:dateRetour.toLocalDate()));
        }

        
        return listRes;
    };

    public ArrayList<Emprunt> getListCurrentByLivre( int idLivre ) throws DaoException, SQLException{
        Connection conn = ConnectionManager.getConnection();
        PreparedStatement stnt = conn.prepareStatement(
            "SELECT e.id AS id, idMembre, nom, prenom, adresse, email, "
            + "telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, "
            + "dateRetour "
            + "FROM emprunt AS e "
            + "INNER JOIN membre ON membre.id = e.idMembre "
            + "INNER JOIN livre ON livre.id = e.idLivre "
            + "WHERE dateRetour IS NULL AND livre.id = ?;");
        stnt.setInt( 1, idLivre );
        ResultSet listEmprunts = stnt.executeQuery();

        ArrayList<Emprunt> listRes = new ArrayList<Emprunt>();
        while(listEmprunts.next()){
            Date dateRetour = listEmprunts.getDate("dateRetour");
            listRes.add(new Emprunt(listEmprunts.getInt("id"), MembreDao.getInstance().getById(listEmprunts.getInt("idMembre")), LivreDao.getInstance().getById(listEmprunts.getInt("idLivre")), listEmprunts.getDate("dateEmprunt").toLocalDate(), (dateRetour==null)? null:dateRetour.toLocalDate()));
        }

        
        return listRes;
    };

    public Emprunt getById( int id ) throws DaoException, SQLException{
Connection conn = ConnectionManager.getConnection();
        PreparedStatement stnt = conn.prepareStatement(
            "SELECT e.id AS idEmprunt, idMembre, nom, prenom, adresse, email, "
            + "telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, "
            + "dateRetour "
            + "FROM emprunt AS e "
            + "INNER JOIN membre ON membre.id = e.idMembre "
            + "INNER JOIN livre ON livre.id = e.idLivre "
            + "WHERE e.id = ?;"); 
        stnt.setInt( 1, id );
        ResultSet listEmprunts = stnt.executeQuery();

        Date dateRetour = listEmprunts.getDate("dateRetour");
        Emprunt emprunt = new Emprunt(listEmprunts.getInt("id"), MembreDao.getInstance().getById(listEmprunts.getInt("idMembre")), LivreDao.getInstance().getById(listEmprunts.getInt("idLivre")), listEmprunts.getDate("dateEmprunt").toLocalDate(), (dateRetour==null)? null:dateRetour.toLocalDate());

        
        return emprunt;
    };

    public void create( int idMembre, int idLivre, LocalDate dateEmprunt ) throws DaoException, SQLException{
        Connection conn = ConnectionManager.getConnection();
        PreparedStatement stnt = conn.prepareStatement("INSERT INTO emprunt(idMembre, idLivre, dateEmprunt, dateRetour) VALUES (?, ?, ?, ?);");
        stnt.setInt(1, idMembre);
        stnt.setInt(2, idLivre);
        stnt.setDate(3, java.sql.Date.valueOf(dateEmprunt));
        stnt.setDate(4, null);
        
    };

    public void update( Emprunt emprunt ) throws DaoException, SQLException{
        Connection conn = ConnectionManager.getConnection();
        PreparedStatement stnt = conn.prepareStatement( "UPDATE emprunt SET idMembre = ?, idLivre = ?, dateEmprunt = ?, dateRetour = ? WHERE id = ?;" );
        stnt.setInt(1, emprunt.getMembre().getPrimaryKey());
        stnt.setInt(2, emprunt.getLivre().getPrimaryKey());
        stnt.setDate(3, java.sql.Date.valueOf(emprunt.getDateEmprunt()));
        stnt.setDate(4, (emprunt.getDateRetour()==null)? null : java.sql.Date.valueOf(emprunt.getDateRetour()));
        stnt.setInt(5, emprunt.getPrimaryKey());

        stnt.executeUpdate();
    };

    public int count() throws DaoException, SQLException{
        Connection conn = ConnectionManager.getConnection();
        PreparedStatement stnt = conn.prepareStatement( "SELECT COUNT(id) AS count FROM emprunt;" );
        ResultSet empruntResultSet = stnt.executeQuery();
        empruntResultSet.next();

        return empruntResultSet.getInt(1);
    };
}
