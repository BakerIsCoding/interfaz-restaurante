package com.groupf.java.swing.m7.interfaces;

import static com.groupf.java.swing.m7.interfaces.InitFrame.translationsObject;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Baker, Eduardo, Marc
 */
public class GuiCuiner extends javax.swing.JFrame {

    /**
     * Creates new form GuiCuiner
     */
    private JSONObject menu = new JSONObject(
            "{\"items\": [{\"Quantitat\": 1, \"Plat\": \"Gazpacho\", \"Tipo\": \"primero\"}, {\"Quantitat\": 1, \"Plat\": \"Patatas bravas\", \"Tipo\": \"primero\"}, {\"Quantitat\": 1, \"Plat\": \"Ensalada César\", \"Tipo\": \"segundo\"}, {\"Quantitat\": 1, \"Plat\": \"Sopa de tomate\", \"Tipo\": \"primero\"}]}");
    private Timer timer;

    public GuiCuiner() throws Exception {
        initComponents();
        startTimer();
        printTableByType(menu);
        doTraductions();

        // Action Listener para escuchar el click del botón
        buttonEspera.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeStateToWait();
            }
        });

        // Action Listener para escuchar el click del botón
        buttonPreparing.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeStateToPreparing();
            }
        });

        // Action Listener para escuchar el click del botón
        buttonListo.addActionListener(new ActionListener() { // Suponiendo que jButton3 es buttonListo
            @Override
            public void actionPerformed(ActionEvent e) {
                changeStateToReady();
            }
        });

    }

    // Según la fila que se haya seleccionado, se busca, y se cambia el estado a EN
    // ESPERA
    private void changeStateToWait() {
        updateStateForSelectedRow(tablePrimeros, "En espera");
        updateStateForSelectedRow(tableSegundos, "En espera");
        updateStateForSelectedRow(tablePostres, "En espera");
    }

    // Según la fila que se haya seleccionado, se busca, y se cambia el estado a EN
    // PREPARACIÓN
    private void changeStateToPreparing() {
        updateStateForSelectedRow(tablePrimeros, "En preparación");
        updateStateForSelectedRow(tableSegundos, "En preparación");
        updateStateForSelectedRow(tablePostres, "En preparación");
    }

    // Según la fila que se haya seleccionado, se busca, y se cambia el estado a
    // LISTO
    private void changeStateToReady() {
        updateStateForSelectedRowAndCheckOrders(tablePrimeros, "Listo");
        updateStateForSelectedRowAndCheckOrders(tableSegundos, "Listo");
        updateStateForSelectedRowAndCheckOrders(tablePostres, "Listo");
    }

    private void updateStateForSelectedRow(JTable table, String newState) {
        int selectedRow = table.getSelectedRow();
        // Verifica si hay una fila
        if (selectedRow != -1) {
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            // Actualiza el valor por el nuevo estado que se le haya pasado
            model.setValueAt(newState, selectedRow, 3);
        }
    }

    private void checkOrdersReadyByTable(int tableId) {
        boolean allReady = true;
        // Verifica en las tablas de Primeros
        allReady &= checkTableOrdersReady(tablePrimeros, tableId);
        // Verifica en las tablas de Segundos
        allReady &= checkTableOrdersReady(tableSegundos, tableId);
        // Verifica en las tablas de Postres
        allReady &= checkTableOrdersReady(tablePostres, tableId);

        if (allReady) {
            System.out.println("Todos los pedidos de la mesa " + tableId + " se han entregado.");
        }
    }

    private boolean checkTableOrdersReady(JTable table, int tableId) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        for (int i = 0; i < model.getRowCount(); i++) {
            int currentTableId = (Integer) model.getValueAt(i, 0);
            String estado = (String) model.getValueAt(i, 3);
            if (currentTableId == tableId && !"Listo".equals(estado)) {
                return false; // Si algún pedido no está listo es false
            }
        }
        return true; // Todos los pedidos de la mesa están listos
    }

    private void updateStateForSelectedRowAndCheckOrders(JTable table, String newState) {
        int selectedRow = table.getSelectedRow();
        // Verifica si hay una fila seleccionada
        if (selectedRow != -1) {
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            // Actualiza al nuevo estado
            model.setValueAt(newState, selectedRow, 3);
            // Se obtiene el id de la mesa
            int tableId = (Integer) model.getValueAt(selectedRow, 0);
            // Verifica si todos los pedidos de la mesa están listos
            checkOrdersReadyByTable(tableId);
        }
    }

    private void doTraductions() throws Exception {
        try {
            // Taula Primers
            TableColumnModel columnModelPrimers = tablePrimeros.getColumnModel();
            columnModelPrimers.getColumn(0).setHeaderValue(translationsObject.getString("gui_cuina_primers_taula"));
            columnModelPrimers.getColumn(1).setHeaderValue(translationsObject.getString("gui_cuina_primers_plat"));
            columnModelPrimers.getColumn(2).setHeaderValue(translationsObject.getString("gui_cuina_primers_temps"));
            columnModelPrimers.getColumn(3).setHeaderValue(translationsObject.getString("gui_cuina_primers_estat"));
            tablePrimeros.repaint();

            // Taula Segons
            TableColumnModel columnModelSegons = tableSegundos.getColumnModel();
            columnModelSegons.getColumn(0).setHeaderValue(translationsObject.getString("gui_cuina_segons_taula"));
            columnModelSegons.getColumn(1).setHeaderValue(translationsObject.getString("gui_cuina_segons_plat"));
            columnModelSegons.getColumn(2).setHeaderValue(translationsObject.getString("gui_cuina_segons_temps"));
            columnModelSegons.getColumn(3).setHeaderValue(translationsObject.getString("gui_cuina_segons_estat"));
            tableSegundos.repaint();

            // Taula Postres
            TableColumnModel columnModelPostres = tablePostres.getColumnModel();
            columnModelPostres.getColumn(0).setHeaderValue(translationsObject.getString("gui_cuina_postres_taula"));
            columnModelPostres.getColumn(1).setHeaderValue(translationsObject.getString("gui_cuina_postres_plat"));
            columnModelPostres.getColumn(2).setHeaderValue(translationsObject.getString("gui_cuina_postres_temps"));
            columnModelPostres.getColumn(3).setHeaderValue(translationsObject.getString("gui_cuina_postres_estat"));
            tablePostres.repaint();

            buttonPreparing.setText(translationsObject.getString("gui_button_preparando"));
            buttonListo.setText(translationsObject.getString("gui_button_listo"));
            buttonEspera.setText(translationsObject.getString("gui_button_espera"));

            title.setText(translationsObject.getString("gui_label_titulo"));
            labelPrimers.setText(translationsObject.getString("gui_label_primers"));
            labelSegons.setText(translationsObject.getString("gui_label_segons"));
            labelPostres.setText(translationsObject.getString("gui_label_postres"));
        } catch (Exception e) {
            throw e;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        title = new javax.swing.JLabel();
        scrollPostres = new javax.swing.JScrollPane();
        tablePostres = new javax.swing.JTable();
        scrollPrimeros = new javax.swing.JScrollPane();
        tablePrimeros = new javax.swing.JTable();
        scrollSegundos = new javax.swing.JScrollPane();
        tableSegundos = new javax.swing.JTable();
        labelSegons = new javax.swing.JLabel();
        labelPrimers = new javax.swing.JLabel();
        labelPostres = new javax.swing.JLabel();
        buttonEspera = new javax.swing.JButton();
        buttonPreparing = new javax.swing.JButton();
        buttonListo = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        title.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title.setText("Cuina");

        tablePostres.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tablePostres.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {

                },
                new String[] {
                        "Taula", "Plat", "Temps", "Estat"
                }) {
            Class[] types = new Class[] {
                    java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean[] {
                    false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        tablePostres.getTableHeader().setReorderingAllowed(false);
        scrollPostres.setViewportView(tablePostres);
        if (tablePostres.getColumnModel().getColumnCount() > 0) {
            tablePostres.getColumnModel().getColumn(0).setMinWidth(1);
            tablePostres.getColumnModel().getColumn(0).setPreferredWidth(1);
            tablePostres.getColumnModel().getColumn(1).setMinWidth(50);
            tablePostres.getColumnModel().getColumn(1).setPreferredWidth(50);
            tablePostres.getColumnModel().getColumn(2).setMinWidth(1);
            tablePostres.getColumnModel().getColumn(2).setPreferredWidth(1);
            tablePostres.getColumnModel().getColumn(3).setMinWidth(30);
            tablePostres.getColumnModel().getColumn(3).setPreferredWidth(30);
        }

        tablePrimeros.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tablePrimeros.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {

                },
                new String[] {
                        "Taula", "Plat", "Temps", "Estat"
                }) {
            Class[] types = new Class[] {
                    java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean[] {
                    false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        tablePrimeros.getTableHeader().setReorderingAllowed(false);
        scrollPrimeros.setViewportView(tablePrimeros);
        if (tablePrimeros.getColumnModel().getColumnCount() > 0) {
            tablePrimeros.getColumnModel().getColumn(0).setMinWidth(1);
            tablePrimeros.getColumnModel().getColumn(0).setPreferredWidth(1);
            tablePrimeros.getColumnModel().getColumn(1).setMinWidth(50);
            tablePrimeros.getColumnModel().getColumn(1).setPreferredWidth(50);
            tablePrimeros.getColumnModel().getColumn(2).setMinWidth(1);
            tablePrimeros.getColumnModel().getColumn(2).setPreferredWidth(1);
            tablePrimeros.getColumnModel().getColumn(3).setMinWidth(30);
            tablePrimeros.getColumnModel().getColumn(3).setPreferredWidth(30);
        }

        tableSegundos.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tableSegundos.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {

                },
                new String[] {
                        "Taula", "Plat", "Temps", "Estat"
                }) {
            Class[] types = new Class[] {
                    java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean[] {
                    false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        tableSegundos.getTableHeader().setReorderingAllowed(false);
        scrollSegundos.setViewportView(tableSegundos);
        if (tableSegundos.getColumnModel().getColumnCount() > 0) {
            tableSegundos.getColumnModel().getColumn(0).setMinWidth(1);
            tableSegundos.getColumnModel().getColumn(0).setPreferredWidth(1);
            tableSegundos.getColumnModel().getColumn(1).setMinWidth(50);
            tableSegundos.getColumnModel().getColumn(1).setPreferredWidth(50);
            tableSegundos.getColumnModel().getColumn(2).setMinWidth(1);
            tableSegundos.getColumnModel().getColumn(2).setPreferredWidth(1);
            tableSegundos.getColumnModel().getColumn(3).setMinWidth(30);
            tableSegundos.getColumnModel().getColumn(3).setPreferredWidth(30);
        }

        labelSegons.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        labelSegons.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelSegons.setText("Segons");

        labelPrimers.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        labelPrimers.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelPrimers.setText("Primers");

        labelPostres.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        labelPostres.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelPostres.setText("Postres");

        buttonEspera.setText("En espera");

        buttonPreparing.setText("En preparación");

        buttonListo.setText("Listo");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(title, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                                Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout
                                                .createSequentialGroup()
                                                .addGap(21, 21, 21)
                                                .addGroup(jPanel1Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(scrollSegundos,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 376,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(scrollPrimeros,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 376,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addGroup(jPanel1Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(labelSegons,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 376,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(labelPrimers,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 376,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(89, 89, 89)
                                .addGroup(jPanel1Layout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(jPanel1Layout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(labelPostres, javax.swing.GroupLayout.PREFERRED_SIZE, 376,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(scrollPostres, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        376, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(buttonEspera, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(buttonPreparing, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(buttonListo, javax.swing.GroupLayout.PREFERRED_SIZE, 119,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(title)
                                .addGap(22, 22, 22)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(labelPostres)
                                        .addComponent(labelPrimers))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(scrollPostres, javax.swing.GroupLayout.PREFERRED_SIZE, 186,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(scrollPrimeros, javax.swing.GroupLayout.PREFERRED_SIZE, 186,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28,
                                        Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(labelSegons)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(scrollSegundos, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        186, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(20, 20, 20))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(buttonEspera,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 64,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(buttonListo,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 64,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(buttonPreparing,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 64,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(90, 90, 90)))));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        // <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
        // (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the default
         * look and feel.
         * For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GuiCuiner.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GuiCuiner.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GuiCuiner.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GuiCuiner.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        // </editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new GuiCuiner().setVisible(true);
                } catch (Exception ex) {
                    Logger.getLogger(GuiCuiner.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

    }

    private void printTableByType(JSONObject menu) {
        // Se obtienen todos los items
        JSONArray items = menu.getJSONArray("items");

        // Se miran los items
        for (int i = 0; i < items.length(); i++) {
            JSONObject item = items.getJSONObject(i);
            String tipo = item.getString("Tipo");
            String plat = item.getString("Plat");

            // DEBUG ONLY, ELIMINAR
            System.out.println(tipo + ": " + plat);

            // Se instancian las tablas
            TableModel tablePrimeros = this.tablePrimeros.getModel();
            TableModel tableSegundos = this.tableSegundos.getModel();
            TableModel tablePostres = this.tablePostres.getModel();

            // Se verifica el tipo de plato
            switch (tipo) {
                case "primero":
                    addPrimero(tablePrimeros, 1, plat);
                    break;
                case "segundo":
                    addSegundo(tableSegundos, 1, plat);
                    break;
                case "postre":
                    addPostre(tablePostres, 1, plat);
                    break;
                default:
                    throw new AssertionError();
            }

        }
    }

    private void addPrimero(TableModel tablePrimeros, Integer tableId, String plato) {
        // Se hace un cast
        if (tablePrimeros instanceof DefaultTableModel) {
            DefaultTableModel defaultModel = (DefaultTableModel) tablePrimeros;
            // Se crea el objeto
            Object[] builder = {
                tableId,
                plato,
                "00:00",
                "En espera"
            };
            // Se añade a la tabla
            defaultModel.addRow(builder);
        }
    }

    private void addSegundo(TableModel tableSegundos, Integer tableId, String plato) {
        // Se hace un cast
        if (tableSegundos instanceof DefaultTableModel) {
            DefaultTableModel defaultModel = (DefaultTableModel) tableSegundos;
            // Se crea el objeto
            Object[] builder = {
                tableId,
                plato,
                "00:00",
                "En espera"
            };
            // Se añade a la tabla
            defaultModel.addRow(builder);
        }
    }

    private void addPostre(TableModel tablePostres, Integer tableId, String plato) {
        // Se hace un cast
        if (tablePostres instanceof DefaultTableModel) {
            DefaultTableModel defaultModel = (DefaultTableModel) tablePostres;
            // Se crea el objeto
            Object[] builder = {
                tableId,
                plato,
                "00:00",
                "En espera"
            };
            // Se añade a la tabla
            defaultModel.addRow(builder);
        }
    }

    // Función para iniciar el timer
    private void startTimer() {
        timer = new Timer(1000, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTableTimers();
            }
        });
        timer.start();
    }

    private void updateTableTimers() {
        // Updatear timers
        updateTimerForTable((DefaultTableModel) this.tablePrimeros.getModel());
        updateTimerForTable((DefaultTableModel) this.tableSegundos.getModel());
        updateTimerForTable((DefaultTableModel) this.tablePostres.getModel());
    }

    private void updateTimerForTable(DefaultTableModel model) {
        for (int i = 0; i < model.getRowCount(); i++) {
            String estado = (String) model.getValueAt(i, 3);
            if ("En preparación".equals(estado)) {
                String currentTime = (String) model.getValueAt(i, 2);
                String[] parts = currentTime.split(":");
                int minutes = Integer.parseInt(parts[0]);
                int seconds = Integer.parseInt(parts[1]);

                seconds++;
                if (seconds == 60) {
                    seconds = 0;
                    minutes++;
                }

                String timeFormatted = String.format("%02d:%02d", minutes, seconds);
                model.setValueAt(timeFormatted, i, 2);
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonEspera;
    private javax.swing.JButton buttonListo;
    private javax.swing.JButton buttonPreparing;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel labelPostres;
    private javax.swing.JLabel labelPrimers;
    private javax.swing.JLabel labelSegons;
    private javax.swing.JScrollPane scrollPostres;
    private javax.swing.JScrollPane scrollPrimeros;
    private javax.swing.JScrollPane scrollSegundos;
    private javax.swing.JTable tablePostres;
    private javax.swing.JTable tablePrimeros;
    private javax.swing.JTable tableSegundos;
    private javax.swing.JLabel title;
    // End of variables declaration//GEN-END:variables
}
