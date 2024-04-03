
package com.groupf.java.swing.m7.interfaces;

import com.groupf.java.swing.m7.messages.MessageBox;
import com.groupf.java.swing.m7.database.DatabaseController;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ButtonGroup;

/**
 *
 * @author Baker
 */
public class RegisterFrame extends javax.swing.JFrame {

    /**
     * Creates new form RegisterFrame
     */
    private List<String> listaUser;
    
    
    public RegisterFrame(List<String> listaUser) {
        this.listaUser = listaUser;
        
        setSize(224, 190);
        initComponents();
        setVisible(true);
        setLocationRelativeTo(null);
        
        ButtonGroup group = new ButtonGroup();
        group.add(radioCambrer);
        group.add(radioCuiner);
        
        
    }
    
    private void registerSubmit(){
        DatabaseController db = new DatabaseController();
        MessageBox msg = new MessageBox();
        Boolean isDone = false;
        if(radioCambrer.isSelected()){
            listaUser.add("cambrer");
            isDone = true;
        } else if(radioCuiner.isSelected()){
            listaUser.add("cuiner");
            isDone = true;
        } else{
            msg.errorMessageBox("Error", "Selecciona el teu lloc de treball");
        }
        
        if (isDone){
            db.registerUser(listaUser.get(0), listaUser.get(1), listaUser.get(2));
            msg.successMessageBox("Registrado!", "Usuario registrado correctamente");
            db.cerrarConexion();
            this.dispose();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        frameTitle = new javax.swing.JLabel();
        radioCambrer = new javax.swing.JRadioButton();
        radioCuiner = new javax.swing.JRadioButton();
        submitButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(237, 238, 238));

        frameTitle.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        frameTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        frameTitle.setText("Lloc de treball");

        radioCambrer.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        radioCambrer.setText("Cambrer");

        radioCuiner.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        radioCuiner.setText("Cuiner");

        submitButton.setBackground(new java.awt.Color(52, 167, 251));
        submitButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        submitButton.setForeground(new java.awt.Color(255, 255, 255));
        submitButton.setText("Enviar");
        submitButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                submitButtonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(frameTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(radioCambrer)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(radioCuiner)
                .addGap(29, 29, 29))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addComponent(submitButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(frameTitle)
                .addGap(38, 38, 38)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radioCuiner)
                    .addComponent(radioCambrer))
                .addGap(27, 27, 27)
                .addComponent(submitButton)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void submitButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_submitButtonMouseClicked
        registerSubmit();
    }//GEN-LAST:event_submitButtonMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
    /* Set the Nimbus look and feel */
    // ... el código para configurar el Look and Feel ...

    /* Create and display the form */
    java.awt.EventQueue.invokeLater(new Runnable() {
        public void run() {
            // Crear una lista vacía de Strings para pasar al constructor
            List<String> listaVacia = new ArrayList<>();
            new RegisterFrame(listaVacia).setVisible(true); // Pasar la lista al constructor
        }
    });
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel frameTitle;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton radioCambrer;
    private javax.swing.JRadioButton radioCuiner;
    private javax.swing.JButton submitButton;
    // End of variables declaration//GEN-END:variables
}
