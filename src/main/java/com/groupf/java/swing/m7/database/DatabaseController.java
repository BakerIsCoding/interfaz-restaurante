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
    private final String url = "jdbc:mysql://localhost:3306/canpedro";
    private final String user = "admin";
    private final String password = "f@VxoYbq(/0Qo2b]";

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

    // Método para seleccionar todos los datos de la tabla usuario
    public ResultSet selectAllUsers() {
        String consulta = "SELECT * FROM usuario"; // Asegúrate de que el nombre de la tabla sea correcto
        try {
            PreparedStatement ps = conn.prepareStatement(consulta);
            return ps.executeQuery();
        } catch (SQLException ex) {
            System.out.println("Error al seleccionar los datos de los usuarios: " + ex.getMessage());
            return null;
        }
    }

    public boolean existeLicencia(String licencia) {
        String consultaSQL = "SELECT COUNT(*) FROM llicencia WHERE llicencia = ?";
        try ( PreparedStatement ps = conn.prepareStatement(consultaSQL)) {
            ps.setString(1, licencia);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
        } catch (SQLException ex) {
            System.err.println("Error al verificar la existencia de la licencia: " + ex.getMessage());
        }
        return false;
    }
    
    public boolean existeUsuario(String user, String pass) {
        String consultaSQL = "SELECT COUNT(*) FROM usuario WHERE usuario = ? AND password = ?";
        try (PreparedStatement ps = conn.prepareStatement(consultaSQL)) {
            ps.setString(1, user);
            ps.setString(2, pass);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
        } catch (SQLException ex) {
            System.err.println("Error al verificar usuario o contraseña: " + ex.getMessage());
        }
        return false;
    }
    
    public boolean registerUser(String nombre, String contraseña, String tipo) {
        String consultaSQL = "INSERT INTO usuario (usuario, password, tipus) VALUES (?, ?, ?);";
        try ( PreparedStatement ps = conn.prepareStatement(consultaSQL)) {
            ps.setString(1, nombre);
            ps.setString(2, contraseña);
            ps.setString(3, tipo);

            int affectedRows = ps.executeUpdate();

            return affectedRows > 0;
        } catch (SQLException ex) {
            System.err.println("Error al insertar un registro " + ex.getMessage());
        }
        return false;
    }

    public boolean saveConfig(String uid, Integer theme, String lang) {
        String consultaSQL = "INSERT INTO settings (id, tema, lang) VALUES (?, ?, ?);";
        try ( PreparedStatement ps = conn.prepareStatement(consultaSQL)) {
            ps.setString(1, uid);
            ps.setInt(2, theme);
            ps.setString(3, lang);

            int affectedRows = ps.executeUpdate();

            return affectedRows > 0;
        } catch (SQLException ex) {
            System.err.println("Error al insertar la configuración: " + ex.getMessage());
        }
        return false;
    }

    public boolean updateConfig(String uid, Integer theme, String lang) {
        String updateSQL = "UPDATE settings SET tema = ?, lang = ? WHERE id = ?;";
        try ( PreparedStatement ps = conn.prepareStatement(updateSQL)) {
            ps.setInt(1, theme);
            ps.setString(2, lang);
            ps.setString(3, uid);

            int affectedRows = ps.executeUpdate();

            return affectedRows > 0;
        } catch (SQLException ex) {
            System.err.println("Error al actualizar la configuración: " + ex.getMessage());
        }
        return false;
    }

    public boolean existsConfig(String uid) {
        String existsSQL = "SELECT COUNT(*) FROM settings WHERE id = ?;";
        try ( PreparedStatement ps = conn.prepareStatement(existsSQL)) {
            ps.setString(1, uid);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
        } catch (SQLException ex) {
            System.err.println("Error al verificar la existencia del registro: " + ex.getMessage());
            return false;
        }
        return false;
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
