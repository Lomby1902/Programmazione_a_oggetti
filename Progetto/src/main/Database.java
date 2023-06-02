/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.sql.*;


/**
 *
 * @author giovanni
 */
public class Database {
    static Connection databaseConnection;
    
    
    public Database() {
            try{
               Class.forName("com.mysql.cj.jdbc.Driver");
               databaseConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Progetto","root","");
            }
            catch(ClassNotFoundException cnfe){
               System.err.println(cnfe);
            }
            catch(SQLException sqle){
               System.err.println(sqle);
   
            }
    }
    
    //Verifica se esiste l'utente
    public static boolean esisteUtente(String nickname, String password) throws SQLException{
        Statement statement = databaseConnection.createStatement();
        String sqlString ="SELECT * FROM Utenti WHERE Nickname = '"+ nickname + "' AND Password='" + password +"'" ;
        ResultSet result =statement.executeQuery(sqlString);
        int righe=0;
        //conta il numero di righe restituite dalla query
        while(result.next()) {
            righe++;
        }
        statement.close();
        return righe >0;
    }
    
    
    
    
    /**
     * Inserisce la chat privata nel database e ne restituisce l'id
     * @param Utente1
     * @return
     * @throws SQLException 
     */
    public static int inserisciChatPrivata(String Utente1) throws SQLException{
        Statement statement = databaseConnection.createStatement();
        //Inserisce la chat privata nella tabella delle chat private inserendo già il creatore della chat
        String sqlString1 ="INSERT INTO ChatPrivate(ID,Utente1,Utente2) VALUES(null, '"+Utente1 +"',null) "; 
        statement.executeUpdate(sqlString1);
        //Prende l'utlimo ID inserito e lo restituisce
        String sqlString2 ="SELECT max(ID) FROM ChatPrivate ";
        ResultSet result =statement.executeQuery(sqlString2);
        int val=-1;
        while(result.next()){
                  val=result.getInt(1);
               }
        String sqlString3 ="CREATE TABLE Privata" + val +"messaggi (time datetime NOT NULL, Mittente varchar(255), Testo varchar(255)) "; 
        statement.executeUpdate(sqlString3);
        statement.close();
        return val;
    }
}
