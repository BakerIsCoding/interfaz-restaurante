
package com.groupf.java.swing.m7.interfaces;

/**
 *
 * @author Marc Baker Eduardo
 */
public class GuiTaula extends javax.swing.JFrame {
    private String numTaula;

    public GuiTaula() {
        initComponents();
    }
    
    public void setNumTaula(String numeroMesa) {
        this.numTaula = numeroMesa; //Asigna el valor de numeroMesa a la variable numMesa de la clase.
        jLabel1.setText("Taula " + numTaula);
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
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
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
                {null, null, null}
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelPlatsLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanelPlatsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelPlatsLayout.createSequentialGroup()
                        .addComponent(jButtonPlat4, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonPlat5, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(jButtonPlat6, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelPlatsLayout.createSequentialGroup()
                        .addGroup(jPanelPlatsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelPlatsLayout.createSequentialGroup()
                                .addComponent(jButtonPrimers, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28))
                            .addGroup(jPanelPlatsLayout.createSequentialGroup()
                                .addComponent(jButtonPlat1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanelPlatsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanelPlatsLayout.createSequentialGroup()
                                .addComponent(jButtonSegons, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addComponent(jButtonPostres, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelPlatsLayout.createSequentialGroup()
                                .addComponent(jButtonPlat2, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addComponent(jButtonPlat3, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(14, 14, 14))
        );
        jPanelPlatsLayout.setVerticalGroup(
            jPanelPlatsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelPlatsLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(jPanelPlatsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonPlat2, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonPlat3, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonPlat1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addGroup(jPanelPlatsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonPlat5, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonPlat6, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonPlat4, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelPlatsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonPostres, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonPrimers, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonSegons, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
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
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonTornarActionPerformed

    private void jButtonPagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPagarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonPagarActionPerformed

    private void jButtonEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEnviarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonEnviarActionPerformed

    private void jButtonSegonsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSegonsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonSegonsActionPerformed

    private void jButtonPlat2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPlat2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonPlat2ActionPerformed

    public static void main(String args[]) {

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GuiTaula().setVisible(true);
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