/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import clients_handel.connect.DBconnect;
import clients_handel.domain.ClientsDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Anik-TC
 */
public class ClientsService {
    
    private Connection connection;
    
    public void saveRecord(ClientsDao clientsDao){
        try {
            connection = DBconnect.getConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO client_list(locaion,compny_name,contact_pr,phone_no,email,collected_by) VALUES(?,?,?,?,?,?)");

            statement.setString(1, clientsDao.getLocation());
            statement.setString(2, clientsDao.getCompnyName());
            statement.setString(3, clientsDao.getContactPerson());
            statement.setString(4, clientsDao.getPhoneNumber());
            statement.setString(5, clientsDao.getEmail());
            statement.setString(6, clientsDao.getCollectedBy());

            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ClientsDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateRecord(ClientsDao clientsDao){
        try{
            connection = DBconnect.getConnection();
            PreparedStatement statement = connection.prepareStatement("UPDATE client_list SET locaion=?,compny_name=?,contact_pr=?,phone_no=?,email=?,collected_by=? WHERE id=?");

            statement.setString(1, clientsDao.getLocation());
            statement.setString(2, clientsDao.getCompnyName());
            statement.setString(3, clientsDao.getContactPerson());
            statement.setString(4, clientsDao.getPhoneNumber());
            statement.setString(5, clientsDao.getEmail());
            statement.setString(6, clientsDao.getCollectedBy());
            statement.setInt(7, clientsDao.getClientId());
            
            statement.executeUpdate();
        }catch(SQLException ex){
            Logger.getLogger(ClientsDao.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
    
    public List<ClientsDao> getData(){
        
        List<ClientsDao> list=new ArrayList<>();
        ClientsDao clientsDao;
        
        try{
            
            connection = DBconnect.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM client_list");
            ResultSet rs = statement.executeQuery();
            
            while (rs.next()) {
                clientsDao=new ClientsDao();
                
                clientsDao.setClientId(rs.getInt(0));
                clientsDao.setLocation(rs.getString(1));
                clientsDao.setCompnyName(rs.getString(2));
                clientsDao.setContactPerson(rs.getString(3));
                clientsDao.setPhoneNumber(rs.getString(3));
                clientsDao.setEmail(rs.getString(4));
                clientsDao.setCollectedBy(rs.getString(5));
                
                list.add(clientsDao);
            }
                    
            return list;
        }catch(SQLException ex){
            Logger.getLogger(ClientsDao.class.getName()).log(Level.SEVERE,null,ex);
        }
        return null;
    }
    
}
