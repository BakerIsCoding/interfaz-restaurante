/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.groupf.java.swing.m7.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Baker
 */
public class DatabaseController {
    private Connection conn = null;
    private final String url = "jdbc:mysql://localhost:3306/nombreDeTuBaseDeDatos";
    private final String user = "tuUsuario";
    private final String password = "tuContraseña";

    public DatabaseController() {
        try {
            // Establecer la conexión con la base de datos
            conn = DriverManager.getConnection(url, user, password);
            if (conn != null) {
                System.out.println("Conectado a la base de datos");
            }
        } catch (SQLException ex) {
            System.out.println("Error al conectar a la base de datos: " + ex.getMessage());
        }
    }

    // Método para ejecutar consultas SELECT
    public ResultSet ejecutarConsulta(String consulta) {
        try {
            PreparedStatement ps = conn.prepareStatement(consulta);
            return ps.executeQuery();
        } catch (SQLException ex) {
            System.out.println("Error al ejecutar la consulta: " + ex.getMessage());
            return null;
        }
    }

    // Método para ejecutar INSERT, UPDATE, DELETE
    public boolean ejecutarActualizacion(String consulta) {
        try {
            PreparedStatement ps = conn.prepareStatement(consulta);
            int count = ps.executeUpdate();
            return count > 0;
        } catch (SQLException ex) {
            System.out.println("Error al ejecutar actualización: " + ex.getMessage());
            return false;
        }
    }

    // Método para cerrar la conexión
    public void cerrarConexion() {
        try {
            if (conn != null) {
                conn.close();
                System.out.println("Conexión cerrada");
            }
        } catch (SQLException ex) {
            System.out.println("Error al cerrar la conexión: " + ex.getMessage());
        }
    }
}
