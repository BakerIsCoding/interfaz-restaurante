
package com.groupf.java.swing.m7.database;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Baker
 */
public class DatabaseConvertor {

    public void showUsers(ResultSet rs) {
        if (rs != null) {
            try {
                while (rs.next()) {
                    // Suponiendo que la tabla de usuarios tiene las columnas id, nombre y edad
                    int id = rs.getInt("id");
                    String nombre = rs.getString("usuario");
                    String password = rs.getString("password");
                    String tipus = rs.getString("Tipus");
                    

                    System.out.println("ID: " + id + ", Nombre: " + nombre + ", Contraseña" + password + ", Tipo:" + tipus);
                }
            } catch (SQLException ex) {
                System.out.println("Error al leer los datos de los usuarios: " + ex.getMessage());
            } finally {
                try {
                    rs.close(); // Importante cerrar el ResultSet después de su uso
                } catch (SQLException ex) {
                    System.out.println("Error al cerrar el ResultSet: " + ex.getMessage());
                }
            }
        } else {
            System.out.println("No se pudieron recuperar los datos de los usuarios.");
        }
    }
}
