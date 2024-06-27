package com.groupf.java.swing.m7.database;

import com.groupf.java.swing.m7.entity.Pedido;
import com.groupf.java.swing.m7.messages.MessageBox;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.json.JSONObject;

public class DatabaseController {

    private static DatabaseController instance;
    private Connection conn = null;

    // URL para la base de datos SQLite
    private final String url = "jdbc:sqlite:database.db";

    private DatabaseController() {
        try {
            conn = DriverManager.getConnection(url);
            if (conn != null) {
                System.out.println("Conectado a la base de datos SQLite");

                // Crear tabla llicencia
                String sqlCrearTablaLlicencia = "CREATE TABLE IF NOT EXISTS llicencia (llicencia TEXT PRIMARY KEY)";
                ejecutarSQL(sqlCrearTablaLlicencia);

                // Crear tabla menu
                String sqlCrearTablaMenu = "CREATE TABLE IF NOT EXISTS menu (nombre INTEGER PRIMARY KEY AUTOINCREMENT, json TEXT NOT NULL, data DATE NOT NULL)";
                ejecutarSQL(sqlCrearTablaMenu);

                // Crear tabla pedidos
                String sqlCrearTablaPedidos = "CREATE TABLE IF NOT EXISTS pedidos (tableid INTEGER PRIMARY KEY, pedidojson TEXT NOT NULL, isServido INTEGER NOT NULL, isPagado INTEGER NOT NULL)";
                ejecutarSQL(sqlCrearTablaPedidos);

                // Crear tabla settings
                String sqlCrearTablaSettings = "CREATE TABLE IF NOT EXISTS settings (id INTEGER PRIMARY KEY, tema INTEGER NOT NULL, lang TEXT NOT NULL, FOREIGN KEY (id) REFERENCES usuario (id))";
                ejecutarSQL(sqlCrearTablaSettings);
                
                // Crear tabla usuario
                String sqlCrearTablaUsuario = "CREATE TABLE IF NOT EXISTS usuario (id INTEGER PRIMARY KEY AUTOINCREMENT, usuario TEXT NOT NULL, password TEXT NOT NULL, tipus TEXT NOT NULL)";
                ejecutarSQL(sqlCrearTablaUsuario);
                
                // Insertar datos en llicencia
                String[] insertsLlicencia = {
                        "INSERT INTO llicencia (llicencia) VALUES ('05yOz6KvD'), ('3YOxE8yqJ'), ('fu3T551Vt'), ('g7msmZ7j8'), ('GAaz24egY'),\n" +
                        "('i9roj7vyT'), ('iiKwUFI9i'), ('jitmkgeZP'), ('loZJgTeV0'), ('mxCk7FgGI'),\n" +
                        "('mydsFBtO9'), ('npY933jhw'), ('pA1j10atW'), ('q5Zo0x4uM'), ('qFktp0Zeu'),\n" +
                        "('rHB8gJsdW'), ('TwzV5XEmT'), ('uFf2xwZiN'), ('wOnOsN8He'), ('y1PIo5NMP');"
                       
                };
                ejecutarInserts(insertsLlicencia);

                // Insertar datos en menu
                String[] insertsMenu = {
                        "INSERT INTO menu (nombre, json, data) VALUES (1, '[{\"Plato\":\"Amanida amb formatge \",\"Precio\":5.5,\"Tipo\":\"primer\",\"Alérgenos\":\"lactics\"},{\"Plato\":\"Truita de patates\",\"Precio\":5.5,\"Tipo\":\"primer\",\"Alérgenos\":\"ou\"},{\"Plato\":\"Canelons\",\"Precio\":6.0,\"Tipo\":\"primer\",\"Alérgenos\":\"lactics, carn, gluten\"},{\"Plato\":\"Coca de recapte\",\"Precio\":6.0,\"Tipo\":\"primer\",\"Alérgenos\":\"peix, gluten\"},{\"Plato\":\"Parrillada de verdures\",\"Precio\":5.5,\"Tipo\":\"primer\",\"Alérgenos\":\"\"},{\"Plato\":\"Sopa de pollastre\",\"Precio\":5.5,\"Tipo\":\"primer\",\"Alérgenos\":\"carn\"},{\"Plato\":\"Croquetes de cigrons\",\"Precio\":6.0,\"Tipo\":\"segon\",\"Alérgenos\":\"gluten, lactics\"},{\"Plato\":\"Bacallà a la llauna\",\"Precio\":7.0,\"Tipo\":\"segon\",\"Alérgenos\":\"peix\"},{\"Plato\":\"Entrecot al Roquefort\",\"Precio\":7.0,\"Tipo\":\"segon\",\"Alérgenos\":\"carn, lactics\"},{\"Plato\":\"Costelles de xai\",\"Precio\":7.0,\"Tipo\":\"segon\",\"Alérgenos\":\"carn\"},{\"Plato\":\"Calamars Farcits\",\"Precio\":6.5,\"Tipo\":\"segon\",\"Alérgenos\":\"peix, carns\"},{\"Plato\":\"Milfulls de boniato i bolets\",\"Precio\":7.0,\"Tipo\":\"segon\",\"Alérgenos\":\"\"},{\"Plato\":\"Macedònia de fruita\",\"Precio\":1.0,\"Tipo\":\"postre\",\"Alérgenos\":\"\"},{\"Plato\":\"Pastís de formatge\",\"Precio\":1.5,\"Tipo\":\"postre\",\"Alérgenos\":\"lactics, gluten, ou\"},{\"Plato\":\"Mousse de xocolata\",\"Precio\":1.5,\"Tipo\":\"postre\",\"Alérgenos\":\"ou\"},{\"Plato\":\"Sorbet de llimona\",\"Precio\":1.0,\"Tipo\":\"postre\",\"Alérgenos\":\"\"}]', '2024-03-26'),\n" +
                                                                                                        "(2, '[{\"Plato\":\"Amanida verda\",\"Precio\":5.0,\"Tipo\":\"primer\",\"Alérgenos\":\"\"},{\"Plato\":\"Escudella\",\"Precio\":5.5,\"Tipo\":\"primer\",\"Alérgenos\":\"carn, gluten\"},{\"Plato\":\"Macarrons a la catalana\",\"Precio\":6.0,\"Tipo\":\"primer\",\"Alérgenos\":\"lactics, carn, gluten, ou\"},{\"Plato\":\"Espinacs a la catalana\",\"Precio\":5.5,\"Tipo\":\"primer\",\"Alérgenos\":\"\"},{\"Plato\":\"Fabada asturiana\",\"Precio\":5.5,\"Tipo\":\"primer\",\"Alérgenos\":\"carn\"},{\"Plato\":\"Arrós a la cubana\",\"Precio\":5.5,\"Tipo\":\"primer\",\"Alérgenos\":\"ou\"},{\"Plato\":\"Canelons d’espinacs\",\"Precio\":7.0,\"Tipo\":\"segon\",\"Alérgenos\":\"gluten, lactics\"},{\"Plato\":\"Sardines al forn\",\"Precio\":6.5,\"Tipo\":\"segon\",\"Alérgenos\":\"peix\"},{\"Plato\":\"Llibrets de llom\",\"Precio\":6.5,\"Tipo\":\"segon\",\"Alérgenos\":\"carn, gluten\"},{\"Plato\":\"Bunyols de Bacallà\",\"Precio\":7.0,\"Tipo\":\"segon\",\"Alérgenos\":\"peix\"},{\"Plato\":\"Botifarra amb mongetes\",\"Precio\":6.5,\"Tipo\":\"segon\",\"Alérgenos\":\"carn\"},{\"Plato\":\"Hamburguesa de remolatxa \",\"Precio\":7.0,\"Tipo\":\"segon\",\"Alérgenos\":\"\"},{\"Plato\":\"Macedònia de fruita\",\"Precio\":1.0,\"Tipo\":\"postre\",\"Alérgenos\":\"\"},{\"Plato\":\"Iogurt amb maduixes\",\"Precio\":1.5,\"Tipo\":\"postre\",\"Alérgenos\":\"lactics\"},{\"Plato\":\"Pastís de xocolata\",\"Precio\":1.5,\"Tipo\":\"postre\",\"Alérgenos\":\"gluten, ou\"},{\"Plato\":\"Sorbet de mandarina\",\"Precio\":1.0,\"Tipo\":\"postre\",\"Alérgenos\":\"\"}]', '2024-04-03')"
                        
                };
                ejecutarInserts(insertsMenu);

                // Insertar datos en settings
                String[] insertsSettings = {
                        "INSERT INTO settings (id, tema, lang) VALUES (1, 0, 'cat'), (2, 0, 'es'), (3, 0, 'es');"
                        // Insertar más datos según sea necesario
                };
                ejecutarInserts(insertsSettings);
                
                // Insertar datos en usuarios
                String[] insertsUsers = {
                    "INSERT INTO usuario (id, usuario, password, tipus) VALUES (1, 'test', 'test1234', 'cambrer'), (2, 'camarero', '123456', 'cambrer'), (3, 'cocinero', '123456', 'cuiner');"
                
                         };
                ejecutarInserts(insertsUsers);
            }
        } catch (SQLException ex) {
            MessageBox msg = new MessageBox();
            msg.errorMessageBox("Error al conectar a la base de datos", "Error al conectarse con la base de datos: \n" + ex.getMessage());
        }
    }

    public static synchronized DatabaseController getInstance() {
        if (instance == null) {
            instance = new DatabaseController();
        }
        return instance;
    }

    private void ejecutarSQL(String sql) {
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Error al ejecutar SQL: " + ex.getMessage());
        }
    }

    private void ejecutarInserts(String[] inserts) {
        for (String insert : inserts) {
            ejecutarSQL(insert);
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

    public void updateIsServido(int tableid, boolean isServido) {
        String updateSQL = "UPDATE pedidos SET isServido = ? WHERE tableid = ?";

        try ( PreparedStatement ps = conn.prepareStatement(updateSQL)) {
            ps.setBoolean(1, isServido);
            ps.setInt(2, tableid);

            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("El estado de servido ha sido actualizado correctamente.");
            } else {
                System.out.println("No se encontró la mesa con id: " + tableid);
            }
        } catch (SQLException ex) {
            System.err.println("Error al actualizar el estado de servido: " + ex.getMessage());
        }
    }

    public Pedido obtainPedidos(List<Integer> tableidsExcluidos) {
        Boolean hasPedido = false;
        Pedido pedido = new Pedido();
        String consultaSQL;
        // Verifica si tableidsExcluidos está vacío
        if (tableidsExcluidos.isEmpty()) {
            // Construye una consulta SQL sin la condición 'NOT IN'
            consultaSQL = "SELECT tableid, pedidojson, isServido, isPagado FROM pedidos LIMIT 1";
        } else {
            // Construyendo la parte de la consulta SQL para 'NOT IN'
            String inSql = String.join(",", tableidsExcluidos.stream().map(id -> "?").toArray(String[]::new));
            consultaSQL = "SELECT tableid, pedidojson, isServido, isPagado FROM pedidos WHERE tableid NOT IN (" + inSql + ") LIMIT 1";
        }

        try ( PreparedStatement ps = conn.prepareStatement(consultaSQL)) {
            // Si tableidsExcluidos no está vacío, asignar cada tableid al PreparedStatement
            if (!tableidsExcluidos.isEmpty()) {
                int index = 1;
                for (Integer tableid : tableidsExcluidos) {
                    ps.setInt(index++, tableid);
                }
            }

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                hasPedido = true;

                pedido.setTableid(rs.getInt("tableid"));
                String jsonStr = rs.getString("pedidojson");
                JSONObject pedidoJson = new JSONObject(jsonStr); // Asegúrate de tener el import correcto para JSONObject
                System.out.println(jsonStr);
                pedido.setPedidoJson(pedidoJson);
                pedido.setIsServido(rs.getBoolean("isServido"));
                pedido.setIsPagado(rs.getBoolean("isPagado"));
            }

            if (hasPedido) {
                return pedido;
            }
        } catch (SQLException ex) {
            System.err.println("Error al obtener el pedido excluyendo tableids: " + ex.getMessage());
        }
        return null;
    }

    public List<Pedido> getPedidos() {
        String consultaSQL = "SELECT tableid, isServido, isPagado FROM pedidos";
        List<Pedido> pedidos = new ArrayList<>();

        try ( PreparedStatement ps = conn.prepareStatement(consultaSQL)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Pedido pedido = new Pedido();
                pedido.setTableid(rs.getInt("tableid"));
                pedido.setIsServido(rs.getBoolean("isServido"));
                pedido.setIsPagado(rs.getBoolean("isPagado"));
                // Dejamos pedidojson como null ya que no lo estamos manejando aquí
                pedidos.add(pedido);
            }
        } catch (SQLException ex) {
            System.err.println("Error al obtener la información de los pedidos: " + ex.getMessage());
        }
        return pedidos;
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

    public Integer getUserId(String user, String pass) {
        String consultaSQL = "SELECT id FROM usuario WHERE usuario = ? AND password = ?";
        try ( PreparedStatement ps = conn.prepareStatement(consultaSQL)) {
            ps.setString(1, user);
            ps.setString(2, pass);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("id"); // Asumiendo que el campo se llama 'id' en tu tabla.
            } else {
                System.err.println("No se encontró el usuario con esas credenciales.");
            }
        } catch (SQLException ex) {
            System.err.println("Error al verificar usuario o contraseña: " + ex.getMessage());
        }
        return null; // O podrías devolver -1 si prefieres trabajar con int y evitar los objetos Integer.
    }

    public boolean insertSettings(Integer id, Integer tema, String lang) {
        String consultaSQL = "INSERT INTO settings (id, tema, lang) VALUES (?, ?, ?)";
        try ( PreparedStatement ps = conn.prepareStatement(consultaSQL)) {
            // Asignar valores a los parámetros del PreparedStatement
            ps.setInt(1, id);
            ps.setInt(2, tema);
            ps.setString(3, lang);

            // Ejecutar la actualización
            int result = ps.executeUpdate();

            // Si result es 1, la inserción fue exitosa
            if (result == 1) {
                System.out.println("Inserción en settings realizada con éxito.");
                return true; // Retornar verdadero si la inserción fue exitosa
            } else {
                System.err.println("La inserción en settings falló.");
                return false; // Retornar falso si la inserción falló
            }
        } catch (SQLException ex) {
            System.err.println("Error al insertar en settings: " + ex.getMessage());
            return false; // Retornar falso si hubo una excepción SQL
        }
    }

    public boolean isUsernameTaken(String user) {
        String consultaSQL = "SELECT COUNT(*) FROM usuario WHERE usuario = ?";
        try ( PreparedStatement ps = conn.prepareStatement(consultaSQL)) {
            ps.setString(1, user);
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

    public boolean existeUsuario(String user, String pass) {
        String consultaSQL = "SELECT COUNT(*) FROM usuario WHERE usuario = ? AND password = ?";
        try ( PreparedStatement ps = conn.prepareStatement(consultaSQL)) {
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

    public String userType(String uid) {
        String consultaSQL = "SELECT tipus FROM usuario WHERE id = ?";
        try ( PreparedStatement ps = conn.prepareStatement(consultaSQL)) {
            ps.setString(1, uid);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("tipus");
            }
        } catch (SQLException ex) {
            System.err.println("Error al verificar usuario o contraseña: " + ex.getMessage());
        }
        return null;
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

    public Integer getThemeById(String uid) {
        String consultaSQL = "SELECT tema FROM settings WHERE id = ?;";
        try ( PreparedStatement ps = conn.prepareStatement(consultaSQL)) {
            ps.setString(1, uid);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("tema");
            } else {
                System.err.println("No se encontró el tema para el id: " + uid);
            }
        } catch (SQLException ex) {
            System.err.println("Error al consultar el tema: " + ex.getMessage());
        }
        return null;
    }

    public String getLangById(String uid) {
        String consultaSQL = "SELECT lang FROM settings WHERE id = ?;";
        try ( PreparedStatement ps = conn.prepareStatement(consultaSQL)) {
            ps.setString(1, uid);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getString("lang");
            } else {
                System.err.println("No se encontró el lenguaje para el id: " + uid);
            }
        } catch (SQLException ex) {
            System.err.println("Error al consultar el lenguaje: " + ex.getMessage());
        }
        return null; // O, de nuevo, lanzar una excepción si no se encuentra el id.
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

    // Método para insertar pedidos
    public boolean insertarPedido(int tableid, String pedidoJson) {
        String sql = "INSERT INTO pedidos (tableid, pedidojson, isServido, isPagado) VALUES (?, ?, false, false);";
        try ( PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, tableid);
            ps.setString(2, pedidoJson);

            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException ex) {
            System.err.println("Error al insertar el pedido: " + ex.getMessage());
            return false;
        }
    }

    public List<Integer> getExistingTableIds(List<Integer> localTableIds) {
        if (localTableIds.isEmpty()) {
            return Collections.emptyList();
        }
        // Convierte la lista de IDs en una cadena para la consulta SQL
        String idsString = localTableIds.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(","));

        String sql = "SELECT tableid FROM pedidos WHERE tableid IN (" + idsString + ");";
        List<Integer> existingTableIds = new ArrayList<>();
        try ( PreparedStatement ps = conn.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                existingTableIds.add(rs.getInt("tableid"));
            }
        } catch (SQLException ex) {
            System.err.println("Error al verificar los tableid existentes: " + ex.getMessage());
        }
        return existingTableIds;
    }

    public boolean isServido(int tableId) {
        String sql = "SELECT isServido FROM pedidos WHERE tableid = ?";
        try ( PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, tableId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getBoolean("isServido");
            }
        } catch (SQLException ex) {
            System.err.println("Error al comprobar si todos los platos han sido servidos: " + ex.getMessage());
        }
        return false;
    }

    public boolean isPagado(int tableId) {
        String sql = "SELECT isPagado FROM pedidos WHERE tableid = ?";
        try ( PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, tableId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getBoolean("isPagado");
            }
        } catch (SQLException ex) {
            System.err.println("Error al comprobar si todos los platos han sido servidos: " + ex.getMessage());
        }
        return false;
    }

    public boolean markAsPaid(int tableId) {
        String sql = "UPDATE pedidos SET isPagado = 1 WHERE tableid = ?";
        try ( PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, tableId);
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException ex) {
            System.err.println("Error al actualizar el estado de pagado: " + ex.getMessage());
        }
        return false;
    }

    public boolean deletePedidoFromTableId(int tableId) {
        String sql = "DELETE FROM pedidos WHERE tableid = ?;";
        try ( PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, tableId);

            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException ex) {
            System.err.println("Error al eliminar los pedidos para tableId " + tableId + ": " + ex.getMessage());
            return false;
        }
    }
}
