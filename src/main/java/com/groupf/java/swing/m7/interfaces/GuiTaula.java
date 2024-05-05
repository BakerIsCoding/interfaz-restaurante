package com.groupf.java.swing.m7.interfaces;

import com.groupf.java.swing.m7.database.DatabaseController;
import static com.groupf.java.swing.m7.interfaces.InitFrame.translationsObject;
import com.groupf.java.swing.m7.messages.MessageBox;
import com.groupf.java.swing.m7.entity.Pedido;
import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import javax.swing.JButton;
import javax.swing.Popup;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Marc Baker Eduardo
 */
public class GuiTaula extends javax.swing.JFrame {

    private String numTaula;
    private Integer menuValue;
    private List<List<String>> menuCompleto; //Lista de listas para manejar el menú completo dividido por categorías.
    private HashMap<String, Double> precioPlatos = new HashMap<String, Double>();
    private HashMap<String, String> tipoPlatos = new HashMap<String, String>();
    private GuiCambrerFrame finstance;

    public GuiTaula(GuiCambrerFrame cambrerInstance) {
        finstance = cambrerInstance;
        this.menuValue = InitFrame.menuValue;
        initComponents();
        setupCategoryButtons();
        setupPlateButtons();

        //Establecemos el menú completo en nuestra interfaz.
        setMenuCompleto(menuCompleto);
        doTraductions();

    }

    private void loadMenu() {
        DatabaseController db = DatabaseController.getInstance();

        String consulta = "SELECT json FROM menu WHERE nombre = '" + this.menuValue + "'";
        ResultSet rs = db.ejecutarConsulta(consulta);

        if (rs == null) {
            System.err.println("ResultSet es null, revisar ejecutarConsulta.");
            return;
        }

        List<String> primeros = new ArrayList<>();
        List<String> segundos = new ArrayList<>();
        List<String> postres = new ArrayList<>();

        try {
            if (rs.next()) {
                String jsonString = rs.getString("json");
                JSONArray jsonArray = new JSONArray(jsonString);

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String tipo = jsonObject.getString("Tipo");

                    switch (tipo) {
                        case "primer":
                            primeros.add(jsonObject.getString("Plato"));
                            precioPlatos.put(jsonObject.getString("Plato"), jsonObject.getDouble("Precio"));
                            tipoPlatos.put(jsonObject.getString("Plato"), "primero");
                            break;
                        case "segon":
                            segundos.add(jsonObject.getString("Plato"));
                            precioPlatos.put(jsonObject.getString("Plato"), jsonObject.getDouble("Precio"));
                            tipoPlatos.put(jsonObject.getString("Plato"), "segundo");
                            break;
                        case "postre":
                            postres.add(jsonObject.getString("Plato"));
                            precioPlatos.put(jsonObject.getString("Plato"), jsonObject.getDouble("Precio"));
                            tipoPlatos.put(jsonObject.getString("Plato"), "postre");
                            break;
                    }
                }
            }
            rs.close();
        } catch (SQLException | JSONException e) {
            e.printStackTrace();
        }
        //Finalmente, actualiza las listas en la GUI.
        List<List<String>> menuCompleto = Arrays.asList(primeros, segundos, postres);
        setMenuCompleto(menuCompleto);
    }

    private void guardarLogComanda(JSONObject pedido) {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        String logDirectoryPath = "logs"; // Ruta del directorio de logs
        File logDirectory = new File(logDirectoryPath);

        // Verifica si el directorio existe, si no, intenta crearlo
        if (!logDirectory.exists()) {
            logDirectory.mkdirs();
        }

        String nombreArchivo = logDirectoryPath + File.separator + new SimpleDateFormat("yyyyMMdd").format(date) + "_Taula-" + this.numTaula + ".log";
        File logFile = new File(nombreArchivo);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFile, true))) {
            writer.write("Hora: " + formatter.format(date));
            writer.newLine();
            writer.write("Comanda: ");
            writer.newLine();
            writer.write(pedido.toString(4));
            writer.newLine();
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo log: " + e.getMessage());
        }
    }

    //CARGA EL PEDIDO!!!!!!!!! EN PROCESO
    private void loadPedido() {
        DatabaseController db = DatabaseController.getInstance();

        String consulta = "SELECT pedidojson FROM pedidos WHERE tableid = '" + this.numTaula + "'";
        ResultSet rs = db.ejecutarConsulta(consulta);

        try {
            if (rs != null && rs.next()) {
                String jsonString = rs.getString("pedidojson");
                JSONObject pedidoJson = new JSONObject(jsonString);
                JSONArray jsonArray = pedidoJson.getJSONArray("items");
                //JSONArray jsonArray = new JSONArray(jsonString);
                System.out.println("Añadiendo comanda: " + jsonArray);

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    int quantitat = jsonObject.getInt("Quantitat");
                    String plat = jsonObject.getString("Plat");
                    System.out.println("Añadiendo plato: " + plat);
                    for (int j = 0; j < quantitat; j++) {
                        agregarPlatoATabla(plat);
                    }
                }
                disableButtons();
                jButtonEnviar.setEnabled(false);
                int tableid = Integer.parseInt(numTaula);
                if (db.isPagado(tableid)) {
                    jButtonPagar.setEnabled(false);
                }
                DefaultTableModel modelTotal = (DefaultTableModel) jTableTotal.getModel();
                if (modelTotal.getRowCount() > 0) {
                    modelTotal.setValueAt(translationsObject.getString("gui_table_sent"), 0, 0);
                } else {
                    modelTotal.addRow(new Object[]{translationsObject.getString("gui_table_sent"), "", ""});
                    actualizarTotal();
                }
                rs.close(); // Cerrar ResultSet aquí
            } else {
                System.out.println("No hay pedidos para la mesa " + this.numTaula);
            }
        } catch (SQLException | JSONException e) {
            e.printStackTrace();
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void disableButtons() {
        jButtonPlat1.setEnabled(false);
        jButtonPlat2.setEnabled(false);
        jButtonPlat3.setEnabled(false);
        jButtonPlat4.setEnabled(false);
        jButtonPlat5.setEnabled(false);
        jButtonPlat6.setEnabled(false);
        jButtonPrimers.setEnabled(false);
        jButtonSegons.setEnabled(false);
        jButtonPostres.setEnabled(false);
    }

    private void doTraductions() {

        //Table Up
        TableColumnModel columnModelUp = jTableComanda.getColumnModel();
        columnModelUp.getColumn(0).setHeaderValue(translationsObject.getString("gui_table_up_quantity"));
        columnModelUp.getColumn(1).setHeaderValue(translationsObject.getString("gui_table_up_dish"));
        columnModelUp.getColumn(2).setHeaderValue(translationsObject.getString("gui_table_up_price"));
        jTableComanda.repaint();

        //Table Down
        TableColumnModel columnModelDown = jTableTotal.getColumnModel();
        columnModelDown.getColumn(0).setHeaderValue(translationsObject.getString("gui_table_down_state"));
        columnModelDown.getColumn(1).setHeaderValue(translationsObject.getString("gui_table_down_iva"));
        columnModelDown.getColumn(2).setHeaderValue(translationsObject.getString("gui_table_down_total"));
        jTableComanda.repaint();

        jButtonTornar.setText(translationsObject.getString("gui_button_go_back"));
        jButtonEnviar.setText(translationsObject.getString("gui_button_send"));
        jButtonPagar.setText(translationsObject.getString("gui_button_pay"));

        jButtonPrimers.setText(translationsObject.getString("gui_button_first"));
        jButtonSegons.setText(translationsObject.getString("gui_button_second"));
        jButtonPostres.setText(translationsObject.getString("gui_button_dessert"));
    }

    private void setupCategoryButtons() {
        jButtonPrimers.addActionListener(evt -> actualizarPlatos(0)); // Primeros platos
        jButtonSegons.addActionListener(evt -> actualizarPlatos(1));  // Segundos platos
        jButtonPostres.addActionListener(evt -> actualizarPlatos(2)); // Postres
    }

    private void setupPlateButtons() {
        jButtonPlat1.addActionListener(evt -> agregarPlatoATabla(jButtonPlat1.getText()));
        jButtonPlat2.addActionListener(evt -> agregarPlatoATabla(jButtonPlat2.getText()));
        jButtonPlat3.addActionListener(evt -> agregarPlatoATabla(jButtonPlat3.getText()));
        jButtonPlat4.addActionListener(evt -> agregarPlatoATabla(jButtonPlat4.getText()));
        jButtonPlat5.addActionListener(evt -> agregarPlatoATabla(jButtonPlat5.getText()));
        jButtonPlat6.addActionListener(evt -> agregarPlatoATabla(jButtonPlat6.getText()));
    }

    private void agregarPlatoATabla(String nombrePlato) {
        DefaultTableModel model = (DefaultTableModel) jTableComanda.getModel();
        boolean platoEncontrado = false;

        //Busca si el plato ya existe en la tabla.
        for (int i = 0; i < model.getRowCount(); i++) {
            String plato = model.getValueAt(i, 1).toString();
            if (plato.equals(nombrePlato)) {
                int cantidad = (Integer) model.getValueAt(i, 0);
                model.setValueAt(cantidad + 1, i, 0); //Incrementa la cantidad.
                platoEncontrado = true;
                break;
            }
        }

        //Si el plato no está en la tabla, lo añade.
        if (!platoEncontrado) {
            double precio = calcularPrecioDelPlato(nombrePlato);
            model.addRow(new Object[]{1, nombrePlato, precio}); //Añade el plato con cantidad 1.
        }
        actualizarTotal();
    }

    private double calcularPrecioDelPlato(String nombrePlato) {
        Double precio = precioPlatos.get(nombrePlato);
        return precio;
    }

    private void actualizarTotal() {
        DefaultTableModel modelComanda = (DefaultTableModel) jTableComanda.getModel();
        DefaultTableModel modelTotal = (DefaultTableModel) jTableTotal.getModel();

        double total = 0.0;
        for (int i = 0; i < modelComanda.getRowCount(); i++) {
            int cantidad = (Integer) modelComanda.getValueAt(i, 0); //Columna de cantidad.
            double precio = (Double) modelComanda.getValueAt(i, 2); //Columna de precio.
            total += cantidad * precio;
        }

        //Actualiza el jTableTotal con el nuevo total.
        if (modelTotal.getRowCount() == 0) {
            modelTotal.addRow(new Object[]{translationsObject.getString("gui_table_in_service"), "", ""}); //Agrega una fila si no existe.
            modelTotal.setValueAt(total, 0, 2); //Actualiza el total.
            modelTotal.setValueAt(total / 10, 0, 1); //Actualiza el IVA.
        } else {
            modelTotal.setValueAt(total, 0, 2); //Actualiza el total.
            modelTotal.setValueAt(total / 10, 0, 1); //Actualiza el IVA.
        }
    }

    public void setNumTaula(String numeroMesa) {
        this.numTaula = numeroMesa; //Asigna el valor de numeroMesa a la variable numMesa de la clase.
        loadMenu();
        loadPedido();
        actualizarPlatos(0);
        jLabel1.setText(translationsObject.getString("gui_table_titulo") + " " + numTaula);
    }

    public void setMenuCompleto(List<List<String>> menuCompleto) {
        this.menuCompleto = menuCompleto; //Asigna el menú completo a la variable de la clase.
    }

    //Método para actualizar nombres de botones según la categoría seleccionada.
    public void actualizarPlatos(int categoria) {
        if (menuCompleto != null && menuCompleto.size() > categoria) {
            List<String> platos = menuCompleto.get(categoria);
            List<JButton> botonesPlatos = Arrays.asList(jButtonPlat1, jButtonPlat2, jButtonPlat3, jButtonPlat4, jButtonPlat5, jButtonPlat6);
            //Recorre todos los botones de platos.
            for (int i = 0; i < botonesPlatos.size(); i++) {
                if (i < platos.size()) {
                    //Si hay un plato correspondiente, establece el nombre y hace el botón visible.
                    botonesPlatos.get(i).setText(platos.get(i));
                    botonesPlatos.get(i).setVisible(true);
                } else {
                    //Si no hay más platos, deja el botón en blanco y lo oculta.
                    botonesPlatos.get(i).setText("");
                    botonesPlatos.get(i).setVisible(false); // O opcionalmente podrías elegir mantenerlos visibles pero deshabilitados con botonesPlatos.get(i).setEnabled(false);
                }
            }
        } else {
            System.out.println(translationsObject.getString("gui_error_update_dishes_names_2"));
        }
    }

    private JSONObject obtenerComanda() {
        DefaultTableModel model = (DefaultTableModel) jTableComanda.getModel();
        JSONArray jsonArray = new JSONArray();
        JSONObject finalObject = new JSONObject();

        for (int i = 0; i < model.getRowCount(); i++) {
            JSONObject jsonObject = new JSONObject();
            try {
                Integer quantitat = (Integer) model.getValueAt(i, 0);
                String plat = (String) model.getValueAt(i, 1);
                String tipo = tipoPlatos.get(plat);

                jsonObject.put("Quantitat", quantitat);
                jsonObject.put("Plat", plat);
                jsonObject.put("Tipo", tipo);

                jsonArray.put(jsonObject);  //Añadir el objeto JSON al array.
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        try {
            finalObject.put("items", jsonArray);  //Añadir el array al objeto JSON bajo la clave "items".
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return finalObject;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelTaula = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPaneComanda = new javax.swing.JScrollPane();
        jTableComanda = new javax.swing.JTable();
        jButtonTornar = new javax.swing.JButton();
        jButtonPagar = new javax.swing.JButton();
        jButtonEnviar = new javax.swing.JButton();
        jScrollPaneTotal = new javax.swing.JScrollPane();
        jTableTotal = new javax.swing.JTable();
        jPanelPlats = new javax.swing.JPanel();
        jButtonPrimers = new javax.swing.JButton();
        jButtonSegons = new javax.swing.JButton();
        jButtonPostres = new javax.swing.JButton();
        jButtonPlat1 = new javax.swing.JButton();
        jButtonPlat2 = new javax.swing.JButton();
        jButtonPlat3 = new javax.swing.JButton();
        jButtonPlat4 = new javax.swing.JButton();
        jButtonPlat5 = new javax.swing.JButton();
        jButtonPlat6 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Taula " + numTaula);

        jTableComanda.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTableComanda.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Quantitat", "Plat", "Preu"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableComanda.setColumnSelectionAllowed(true);
        jTableComanda.getTableHeader().setReorderingAllowed(false);
        jScrollPaneComanda.setViewportView(jTableComanda);
        jTableComanda.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTableComanda.getAccessibleContext().setAccessibleName("");

        jButtonTornar.setBackground(new java.awt.Color(52, 167, 251));
        jButtonTornar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButtonTornar.setForeground(new java.awt.Color(255, 255, 255));
        jButtonTornar.setText("<-- Tornar");
        jButtonTornar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTornarActionPerformed(evt);
            }
        });

        jButtonPagar.setBackground(new java.awt.Color(52, 167, 251));
        jButtonPagar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButtonPagar.setForeground(new java.awt.Color(255, 255, 255));
        jButtonPagar.setText("Pagar ");
        jButtonPagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPagarActionPerformed(evt);
            }
        });

        jButtonEnviar.setBackground(new java.awt.Color(52, 167, 251));
        jButtonEnviar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButtonEnviar.setForeground(new java.awt.Color(255, 255, 255));
        jButtonEnviar.setText("Enviar");
        jButtonEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEnviarActionPerformed(evt);
            }
        });

        jTableTotal.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTableTotal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Estat", "10% IVA", "Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableTotal.setColumnSelectionAllowed(true);
        jTableTotal.getTableHeader().setReorderingAllowed(false);
        jScrollPaneTotal.setViewportView(jTableTotal);
        jTableTotal.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        jButtonPrimers.setBackground(new java.awt.Color(255, 255, 153));
        jButtonPrimers.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButtonPrimers.setText("Primers");
        jButtonPrimers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPrimersActionPerformed(evt);
            }
        });

        jButtonSegons.setBackground(new java.awt.Color(153, 255, 153));
        jButtonSegons.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButtonSegons.setText("Segons");
        jButtonSegons.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSegonsActionPerformed(evt);
            }
        });

        jButtonPostres.setBackground(new java.awt.Color(102, 255, 255));
        jButtonPostres.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButtonPostres.setText("Postres");
        jButtonPostres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPostresActionPerformed(evt);
            }
        });

        jButtonPlat1.setBackground(new java.awt.Color(255, 255, 153));
        jButtonPlat1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButtonPlat1.setText("Plat 1");

        jButtonPlat2.setBackground(new java.awt.Color(255, 255, 153));
        jButtonPlat2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButtonPlat2.setText("Plat 2");
        jButtonPlat2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPlat2ActionPerformed(evt);
            }
        });

        jButtonPlat3.setBackground(new java.awt.Color(255, 255, 153));
        jButtonPlat3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButtonPlat3.setText("Plat 3");

        jButtonPlat4.setBackground(new java.awt.Color(255, 255, 153));
        jButtonPlat4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButtonPlat4.setText("Plat 4");

        jButtonPlat5.setBackground(new java.awt.Color(255, 255, 153));
        jButtonPlat5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButtonPlat5.setText("Plat 5");

        jButtonPlat6.setBackground(new java.awt.Color(255, 255, 153));
        jButtonPlat6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButtonPlat6.setText("Plat 6");

        javax.swing.GroupLayout jPanelPlatsLayout = new javax.swing.GroupLayout(jPanelPlats);
        jPanelPlats.setLayout(jPanelPlatsLayout);
        jPanelPlatsLayout.setHorizontalGroup(
            jPanelPlatsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPlatsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelPlatsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonPlat1, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                    .addComponent(jButtonPlat3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonPlat2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelPlatsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonPlat6, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                    .addComponent(jButtonPlat5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonPlat4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelPlatsLayout.createSequentialGroup()
                .addGap(0, 36, Short.MAX_VALUE)
                .addComponent(jButtonPrimers, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jButtonSegons, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jButtonPostres, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );
        jPanelPlatsLayout.setVerticalGroup(
            jPanelPlatsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelPlatsLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(jPanelPlatsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonPlat1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonPlat4, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addGroup(jPanelPlatsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonPlat5, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonPlat2, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addGroup(jPanelPlatsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonPlat3, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonPlat6, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanelPlatsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonPostres, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonPrimers, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonSegons, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
        );

        javax.swing.GroupLayout jPanelTaulaLayout = new javax.swing.GroupLayout(jPanelTaula);
        jPanelTaula.setLayout(jPanelTaulaLayout);
        jPanelTaulaLayout.setHorizontalGroup(
            jPanelTaulaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanelTaulaLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanelTaulaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanelTaulaLayout.createSequentialGroup()
                        .addComponent(jButtonTornar)
                        .addGap(44, 44, 44)
                        .addComponent(jButtonEnviar)
                        .addGap(44, 44, 44)
                        .addComponent(jButtonPagar))
                    .addComponent(jScrollPaneComanda, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPaneTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(jPanelPlats, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanelTaulaLayout.setVerticalGroup(
            jPanelTaulaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelTaulaLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1)
                .addGap(25, 25, 25)
                .addGroup(jPanelTaulaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelTaulaLayout.createSequentialGroup()
                        .addComponent(jScrollPaneComanda, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPaneTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanelPlats, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanelTaulaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonTornar)
                    .addComponent(jButtonPagar)
                    .addComponent(jButtonEnviar))
                .addGap(19, 19, 19))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelTaula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelTaula, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonTornarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTornarActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButtonTornarActionPerformed

    private void jButtonPagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPagarActionPerformed
        DatabaseController db = DatabaseController.getInstance();
        MessageBox msg = new MessageBox();
        int tableid = Integer.parseInt(numTaula);

        if (!db.isServido(tableid)) {
            msg.errorMessageBox("Error al Pagar", "No se han servido todos los platos.");
        } else {

            if (db.markAsPaid(tableid)) {
                Pedido pedido = new Pedido();
                pedido.setTableid(tableid);
                msg.successMessageBox("Pago Realizado", "Se ha pagado correctamente.");
                jButtonPagar.setEnabled(false);
                db.deletePedidoFromTableId(pedido.getTableid());
                GuiCambrerFrame.helper(pedido, 0, finstance);
                GuiCambrerFrame.stateButton2 = 0;
            } else {
                msg.errorMessageBox("Error al Pagar", "Error al realizar el pago.");
            }
        }
    }//GEN-LAST:event_jButtonPagarActionPerformed

    private void jButtonEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEnviarActionPerformed
        DefaultTableModel modelTotal = (DefaultTableModel) jTableTotal.getModel();
        if (modelTotal.getRowCount() > 0) {
            modelTotal.setValueAt(translationsObject.getString("gui_table_sent"), 0, 0);
            DatabaseController db = DatabaseController.getInstance();
            JSONObject pedido = obtenerComanda();
            String pedidoJson = pedido.toString();

            int tableid = Integer.parseInt(numTaula);
            jButtonEnviar.setEnabled(false);
            disableButtons();

            if (db.insertarPedido(tableid, pedidoJson)) {
                System.out.println("Pedido insertado correctamente en la base de datos.");
                guardarLogComanda(pedido);
            } else {
                System.out.println("Error al insertar el pedido en la base de datos.");
            }

        }
    }//GEN-LAST:event_jButtonEnviarActionPerformed
    //Método de acción para el botón de segundos.
    private void jButtonSegonsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSegonsActionPerformed
        actualizarPlatos(1);
        Color colorSegons = jButtonSegons.getBackground();
        jButtonPlat1.setBackground(colorSegons);
        jButtonPlat2.setBackground(colorSegons);
        jButtonPlat3.setBackground(colorSegons);
        jButtonPlat4.setBackground(colorSegons);
        jButtonPlat5.setBackground(colorSegons);
        jButtonPlat6.setBackground(colorSegons);
    }//GEN-LAST:event_jButtonSegonsActionPerformed

    private void jButtonPlat2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPlat2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonPlat2ActionPerformed
    //Método de acción para el botón de primeros.
    private void jButtonPrimersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPrimersActionPerformed
        actualizarPlatos(0);
        Color colorPrimers = jButtonPrimers.getBackground();
        jButtonPlat1.setBackground(colorPrimers);
        jButtonPlat2.setBackground(colorPrimers);
        jButtonPlat3.setBackground(colorPrimers);
        jButtonPlat4.setBackground(colorPrimers);
        jButtonPlat5.setBackground(colorPrimers);
        jButtonPlat6.setBackground(colorPrimers);

    }//GEN-LAST:event_jButtonPrimersActionPerformed
    //Método de acción para el botón de postres.
    private void jButtonPostresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPostresActionPerformed
        actualizarPlatos(2);
        Color colorPostres = jButtonPostres.getBackground();
        jButtonPlat1.setBackground(colorPostres);
        jButtonPlat2.setBackground(colorPostres);
        jButtonPlat3.setBackground(colorPostres);
        jButtonPlat4.setBackground(colorPostres);
    }//GEN-LAST:event_jButtonPostresActionPerformed

    public static void main(String args[]) {

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                GuiCambrerFrame cambrerInstance = new GuiCambrerFrame();
                new GuiTaula(cambrerInstance).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonEnviar;
    private javax.swing.JButton jButtonPagar;
    private javax.swing.JButton jButtonPlat1;
    private javax.swing.JButton jButtonPlat2;
    private javax.swing.JButton jButtonPlat3;
    private javax.swing.JButton jButtonPlat4;
    private javax.swing.JButton jButtonPlat5;
    private javax.swing.JButton jButtonPlat6;
    private javax.swing.JButton jButtonPostres;
    private javax.swing.JButton jButtonPrimers;
    private javax.swing.JButton jButtonSegons;
    private javax.swing.JButton jButtonTornar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanelPlats;
    private javax.swing.JPanel jPanelTaula;
    private javax.swing.JScrollPane jScrollPaneComanda;
    private javax.swing.JScrollPane jScrollPaneTotal;
    private javax.swing.JTable jTableComanda;
    private javax.swing.JTable jTableTotal;
    // End of variables declaration//GEN-END:variables
}
