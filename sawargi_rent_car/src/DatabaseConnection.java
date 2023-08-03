/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
/**
 *
 * @author Administrator
 */
public class DatabaseConnection {
public static Connection getKoneksi(String host, String port, String username, String password, String db) {
String konString = "jdbc:mysql://" + host + ":" + port + "/" + db;
Connection koneksi = null;
try {
Class.forName("com.mysql.jdbc.Driver");
koneksi = (Connection) DriverManager.getConnection(konString, username, password);
System.out.println("Koneksi Berhasil");
} catch (ClassNotFoundException | SQLException ex) {
JOptionPane.showMessageDialog(null, "Koneksi Database Error");
koneksi = null;
}
return koneksi;
}    
}
