package com.groupf.java.swing.m7.interfaces;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.groupf.java.swing.m7.database.DatabaseController;
import com.groupf.java.swing.m7.database.DatabaseConvertor;
import com.groupf.java.swing.m7.lang.LangController;
import com.groupf.java.swing.m7.messages.MessageBox;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.ButtonGroup;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingUtilities;
import org.json.JSONObject;

/**
 *
 * @author Baker
 */
public class InitFrame extends javax.swing.JFrame {

    /**
     * Creates new form InitFrame
     */
    public InitFrame() {
        DatabaseController db = new DatabaseController();
        DatabaseConvertor dbc = new DatabaseConvertor();
        initComponents();
        setTitle("Restaurant Can Pedro");
        setSize(760, 560);
        setLocationRelativeTo(null);

        ResultSet rs = db.selectAllUsers();
        dbc.showUsers(rs);

        setResizable(false);
        setVisible(true);
        MessageBox msg = new MessageBox();

        generateTable();
        /*LLICENCIA
        DISABLE PANELS*/
        allPanels.setEnabledAt(1, false);
        allPanels.setEnabledAt(2, false);
        allPanels.setEnabledAt(3, false);

        /* USUARI */
 /* SETTINGS 
        RADIO BUTTONS */
        radioButtonClar.setSelected(true);
        ButtonGroup group = new ButtonGroup();
        group.add(radioButtonClar);
        group.add(radioButtonFosc);

        /* COMBO BOX */
        comboBoxLlengua.removeAllItems();
        comboBoxLlengua.addItem("Català");
        comboBoxLlengua.addItem("Castellano");
        comboBoxLlengua.addItem("English");

    }

    private void registerClicked() {
        MessageBox msg = new MessageBox();
        String username = textFieldUsuari.getText();
        String password = textFieldPassword.getText();

        if (username.length() <= 3) {
            msg.errorMessageBox("Error", "Error, el nombre de usuario debe tener una longitud mínima de 3 caracteres");
            return;
        }

        if (password.length() <= 6) {
            msg.errorMessageBox("Error", "Error, la contraseña debe tener una longitud mínima de 6 caracteres");
            return;
        }
        List<String> listaUser = new ArrayList<>(3);
        listaUser.add(username);
        listaUser.add(password);

        RegisterFrame register = new RegisterFrame(listaUser);
        register.setDefaultCloseOperation(RegisterFrame.DISPOSE_ON_CLOSE);
    }

    private void selectMenuClicked() {
        GuiCambrerFrame cambrer = new GuiCambrerFrame();
        cambrer.setDefaultCloseOperation(RegisterFrame.DISPOSE_ON_CLOSE);
    }

    public void generateTable() {
        TableModel model = menuTable.getModel(); // Utiliza la instancia correcta de JTable aquí.
        if (model instanceof DefaultTableModel) {
            DefaultTableModel defaultModel = (DefaultTableModel) model;

            // Ahora puedes añadir una fila al modelo
            defaultModel.addRow(new Object[]{"Dato 1", 3, "Dato 3"});
            defaultModel.addRow(new Object[]{"Dato 1", 3, "Dato 3"});
            defaultModel.addRow(new Object[]{"Dato 1", 3, "Dato 3"});
            defaultModel.addRow(new Object[]{"Dato 1", 3, "Dato 3"});
            defaultModel.addRow(new Object[]{"Dato 1", 3, "Dato 3"});
            defaultModel.addRow(new Object[]{"Dato 1", 3, "Dato 3"});
            defaultModel.addRow(new Object[]{"Dato 1", 3, "Dato 3"});
            defaultModel.addRow(new Object[]{"Dato 1", 3, "Dato 3"});
            defaultModel.addRow(new Object[]{"Dato 1", 3, "Dato 3"});
            defaultModel.addRow(new Object[]{"Dato 1", 3, "Dato 3"});
            defaultModel.addRow(new Object[]{"Dato 1", 3, "Dato 3"});
            defaultModel.addRow(new Object[]{"Dato 1", 3, "Dato 3"});
            defaultModel.addRow(new Object[]{"Dato 1", 3, "Dato 3"});
            defaultModel.addRow(new Object[]{"Dato 1", 3, "Dato 3"});
            defaultModel.addRow(new Object[]{"Dato 1", 3, "Dato 3"});
        } else {
            // Manejar el caso en que el modelo no sea una instancia de DefaultTableModel
            System.out.println("El modelo de la tabla no es una instancia de DefaultTableModel.");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        allPanels = new javax.swing.JTabbedPane();
        Llicencia = new javax.swing.JPanel();
        panelLlicencia = new javax.swing.JPanel();
        textButtonSelectFile = new javax.swing.JLabel();
        buttonSelectFile = new javax.swing.JButton();
        Usuari = new javax.swing.JPanel();
        panelCredencials = new javax.swing.JPanel();
        titleUsuari = new javax.swing.JLabel();
        labelUsuari = new javax.swing.JLabel();
        textFieldUsuari = new javax.swing.JTextField();
        labelClau = new javax.swing.JLabel();
        textFieldPassword = new javax.swing.JTextField();
        buttonLogIn = new javax.swing.JButton();
        buttonRegister = new javax.swing.JButton();
        MenuDelDia = new javax.swing.JPanel();
        panelMenu = new javax.swing.JPanel();
        labelTitleMenu = new javax.swing.JLabel();
        scrollTable = new javax.swing.JScrollPane();
        menuTable = new javax.swing.JTable();
        panelMenuButton = new javax.swing.JPanel();
        buttonMenu = new javax.swing.JButton();
        Configuracio = new javax.swing.JPanel();
        panelConfiguracio = new javax.swing.JPanel();
        titleConfig = new javax.swing.JLabel();
        labelTema = new javax.swing.JLabel();
        radioButtonClar = new javax.swing.JRadioButton();
        radioButtonFosc = new javax.swing.JRadioButton();
        labelLlengua = new javax.swing.JLabel();
        comboBoxLlengua = new javax.swing.JComboBox<>();
        saveConfigButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        allPanels.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        panelLlicencia.setBackground(new java.awt.Color(237, 238, 238));

        textButtonSelectFile.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        textButtonSelectFile.setText("Llicencia");

        buttonSelectFile.setBackground(new java.awt.Color(52, 167, 251));
        buttonSelectFile.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        buttonSelectFile.setForeground(new java.awt.Color(255, 255, 255));
        buttonSelectFile.setText("Selecciona fitxer");
        buttonSelectFile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonSelectFileMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelLlicenciaLayout = new javax.swing.GroupLayout(panelLlicencia);
        panelLlicencia.setLayout(panelLlicenciaLayout);
        panelLlicenciaLayout.setHorizontalGroup(
            panelLlicenciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLlicenciaLayout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(textButtonSelectFile)
                .addContainerGap(58, Short.MAX_VALUE))
            .addGroup(panelLlicenciaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buttonSelectFile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelLlicenciaLayout.setVerticalGroup(
            panelLlicenciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLlicenciaLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(textButtonSelectFile)
                .addGap(18, 18, 18)
                .addComponent(buttonSelectFile)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout LlicenciaLayout = new javax.swing.GroupLayout(Llicencia);
        Llicencia.setLayout(LlicenciaLayout);
        LlicenciaLayout.setHorizontalGroup(
            LlicenciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LlicenciaLayout.createSequentialGroup()
                .addGap(256, 256, 256)
                .addComponent(panelLlicencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(256, 256, 256))
        );
        LlicenciaLayout.setVerticalGroup(
            LlicenciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LlicenciaLayout.createSequentialGroup()
                .addGap(169, 169, 169)
                .addComponent(panelLlicencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(170, 170, 170))
        );

        allPanels.addTab("Llicencia", Llicencia);

        panelCredencials.setBackground(new java.awt.Color(237, 238, 238));

        titleUsuari.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        titleUsuari.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleUsuari.setText("Credencials");

        labelUsuari.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        labelUsuari.setText("Usuari:");

        textFieldUsuari.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        labelClau.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        labelClau.setText("Clau:");

        textFieldPassword.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        textFieldPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldPasswordActionPerformed(evt);
            }
        });

        buttonLogIn.setBackground(new java.awt.Color(52, 167, 251));
        buttonLogIn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        buttonLogIn.setForeground(new java.awt.Color(255, 255, 255));
        buttonLogIn.setText("Iniciar Sessió");
        buttonLogIn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonLogInMouseClicked(evt);
            }
        });

        buttonRegister.setBackground(new java.awt.Color(52, 167, 251));
        buttonRegister.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        buttonRegister.setForeground(new java.awt.Color(255, 255, 255));
        buttonRegister.setText("Registrarse");
        buttonRegister.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonRegisterMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelCredencialsLayout = new javax.swing.GroupLayout(panelCredencials);
        panelCredencials.setLayout(panelCredencialsLayout);
        panelCredencialsLayout.setHorizontalGroup(
            panelCredencialsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(titleUsuari, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelCredencialsLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(panelCredencialsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelUsuari, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelClau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelCredencialsLayout.createSequentialGroup()
                        .addComponent(textFieldUsuari, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCredencialsLayout.createSequentialGroup()
                        .addGroup(panelCredencialsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(textFieldPassword, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelCredencialsLayout.createSequentialGroup()
                                .addComponent(buttonLogIn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(buttonRegister)))
                        .addGap(30, 30, 30))))
        );
        panelCredencialsLayout.setVerticalGroup(
            panelCredencialsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCredencialsLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(titleUsuari)
                .addGap(18, 18, 18)
                .addComponent(labelUsuari)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textFieldUsuari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(labelClau)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textFieldPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addGroup(panelCredencialsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonLogIn)
                    .addComponent(buttonRegister))
                .addGap(40, 40, 40))
        );

        javax.swing.GroupLayout UsuariLayout = new javax.swing.GroupLayout(Usuari);
        Usuari.setLayout(UsuariLayout);
        UsuariLayout.setHorizontalGroup(
            UsuariLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(UsuariLayout.createSequentialGroup()
                .addGap(202, 202, 202)
                .addComponent(panelCredencials, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(203, 203, 203))
        );
        UsuariLayout.setVerticalGroup(
            UsuariLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(UsuariLayout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addComponent(panelCredencials, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(75, 75, 75))
        );

        allPanels.addTab("Usuari", Usuari);

        panelMenu.setBackground(new java.awt.Color(237, 238, 238));

        labelTitleMenu.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        labelTitleMenu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTitleMenu.setText("Menú del dia");

        menuTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nom", "Ordre", "Alergens", "Preu"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        menuTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuTableMouseClicked(evt);
            }
        });
        scrollTable.setViewportView(menuTable);

        panelMenuButton.setBackground(new java.awt.Color(237, 238, 238));

        buttonMenu.setBackground(new java.awt.Color(52, 167, 251));
        buttonMenu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        buttonMenu.setForeground(new java.awt.Color(255, 255, 255));
        buttonMenu.setText("Seleccionar Menú");
        buttonMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonMenuMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelMenuButtonLayout = new javax.swing.GroupLayout(panelMenuButton);
        panelMenuButton.setLayout(panelMenuButtonLayout);
        panelMenuButtonLayout.setHorizontalGroup(
            panelMenuButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMenuButtonLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelMenuButtonLayout.setVerticalGroup(
            panelMenuButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMenuButtonLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buttonMenu)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelMenuLayout = new javax.swing.GroupLayout(panelMenu);
        panelMenu.setLayout(panelMenuLayout);
        panelMenuLayout.setHorizontalGroup(
            panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelTitleMenu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelMenuLayout.createSequentialGroup()
                .addGroup(panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelMenuButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelMenuLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(scrollTable, javax.swing.GroupLayout.DEFAULT_SIZE, 628, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelMenuLayout.setVerticalGroup(
            panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMenuLayout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(labelTitleMenu)
                .addGap(18, 18, 18)
                .addComponent(scrollTable, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelMenuButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );

        javax.swing.GroupLayout MenuDelDiaLayout = new javax.swing.GroupLayout(MenuDelDia);
        MenuDelDia.setLayout(MenuDelDiaLayout);
        MenuDelDiaLayout.setHorizontalGroup(
            MenuDelDiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuDelDiaLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(panelMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
        );
        MenuDelDiaLayout.setVerticalGroup(
            MenuDelDiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuDelDiaLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(panelMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
        );

        allPanels.addTab("Menú del dia", MenuDelDia);

        panelConfiguracio.setBackground(new java.awt.Color(237, 238, 238));

        titleConfig.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        titleConfig.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleConfig.setText("Configuració");

        labelTema.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        labelTema.setText("Tema:");

        radioButtonClar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        radioButtonClar.setText("Clar");
        radioButtonClar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioButtonClarActionPerformed(evt);
            }
        });

        radioButtonFosc.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        radioButtonFosc.setText("Fosc");
        radioButtonFosc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioButtonFoscActionPerformed(evt);
            }
        });

        labelLlengua.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        labelLlengua.setText("Llengüa");

        comboBoxLlengua.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        comboBoxLlengua.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboBoxLlengua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxLlenguaActionPerformed(evt);
            }
        });

        saveConfigButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        saveConfigButton.setText("Desa");

        javax.swing.GroupLayout panelConfiguracioLayout = new javax.swing.GroupLayout(panelConfiguracio);
        panelConfiguracio.setLayout(panelConfiguracioLayout);
        panelConfiguracioLayout.setHorizontalGroup(
            panelConfiguracioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(titleConfig, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelConfiguracioLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(panelConfiguracioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelConfiguracioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(panelConfiguracioLayout.createSequentialGroup()
                            .addComponent(radioButtonClar, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(radioButtonFosc))
                        .addComponent(labelLlengua, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                        .addComponent(labelTema, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(comboBoxLlengua, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelConfiguracioLayout.createSequentialGroup()
                .addContainerGap(165, Short.MAX_VALUE)
                .addComponent(saveConfigButton, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(161, 161, 161))
        );
        panelConfiguracioLayout.setVerticalGroup(
            panelConfiguracioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelConfiguracioLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(titleConfig)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelTema)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelConfiguracioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radioButtonFosc)
                    .addComponent(radioButtonClar))
                .addGap(18, 18, 18)
                .addComponent(labelLlengua)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboBoxLlengua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addComponent(saveConfigButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );

        javax.swing.GroupLayout ConfiguracioLayout = new javax.swing.GroupLayout(Configuracio);
        Configuracio.setLayout(ConfiguracioLayout);
        ConfiguracioLayout.setHorizontalGroup(
            ConfiguracioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ConfiguracioLayout.createSequentialGroup()
                .addGap(137, 137, 137)
                .addComponent(panelConfiguracio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(138, 138, 138))
        );
        ConfiguracioLayout.setVerticalGroup(
            ConfiguracioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ConfiguracioLayout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addComponent(panelConfiguracio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(83, 83, 83))
        );

        allPanels.addTab("Configuració", Configuracio);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(allPanels)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(allPanels)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonSelectFileMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonSelectFileMouseClicked
        MessageBox msg = new MessageBox();
        JFileChooser fileChooser = new JFileChooser();

        // Filtrar solo archivos .csv
        FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV Files", "csv");
        fileChooser.setFileFilter(filter);
        fileChooser.setDialogTitle("Selecciona el archivo de licencia");

        // Mostrar el diálogo para abrir archivo
        int seleccion = fileChooser.showOpenDialog(null);

        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            System.out.println("Archivo seleccionado: " + selectedFile.getAbsolutePath());

            // Procesar el archivo seleccionado
            Boolean isValid = procesarArchivoLicencia(selectedFile);

            if (isValid) {
                allPanels.setSelectedIndex(1);
                allPanels.setEnabledAt(0, false);
                allPanels.setEnabledAt(1, true);
                allPanels.setEnabledAt(2, true);
                allPanels.setEnabledAt(3, true);
                msg.successMessageBox("Licencia válida", "Licencia válida");
            }
        }


    }//GEN-LAST:event_buttonSelectFileMouseClicked
    private Boolean procesarArchivoLicencia(File archivo) {
        DatabaseController dbController = new DatabaseController();
        MessageBox msg = new MessageBox();
        try ( BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
                // Asumiendo que la fecha está en la segunda parte y el formato es correcto
                if (partes.length > 1) {
                    String licencia = partes[0];
                    String fechaStr = partes[1];
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss yyyy-MM-dd");
                    LocalDateTime fechaLicencia = LocalDateTime.parse(fechaStr, formatter);
                    LocalDateTime ahora = LocalDateTime.now();
                    if (fechaLicencia.isAfter(ahora)) {
                        Boolean isLicenciaExisting = dbController.existeLicencia(licencia);
                        if (isLicenciaExisting) {
                            return true;
                        } else {
                            msg.errorMessageBox("Error al verificar la licencia", "La licencia no es válida");
                        }

                    } else {
                        System.out.println();
                        msg.errorMessageBox("La licencia ha expirado", "La licencia ha expirado. Fecha de expiración: " + fechaLicencia);
                        return false;
                    }
                }
            }
        } catch (IOException e) {
            msg.errorMessageBox("Error en el archivo", "Ha ocurrido un error al leer el archivo, comprueba que esté en el formato correcto");
            System.err.println("Error al leer el archivo: " + e.getMessage());
            return false;
        }
        return false;
    }
    private void textFieldPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldPasswordActionPerformed

    private void radioButtonClarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioButtonClarActionPerformed
        FlatLaf.setup(new FlatLightLaf());
        SwingUtilities.updateComponentTreeUI(this);
    }//GEN-LAST:event_radioButtonClarActionPerformed

    private void comboBoxLlenguaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxLlenguaActionPerformed
        LangController lang = new LangController();
        // Obtenim l'index
        int selectedIndex = comboBoxLlengua.getSelectedIndex();
        switch (selectedIndex) {
            case 0: // Català
                JSONObject langObj = lang.loadLanguage("cat");
                break;
            case 1: // Castellano
                lang.loadLanguage("es");
                break;
            case 2: // English
                lang.loadLanguage("en");
                break;
        }
    }//GEN-LAST:event_comboBoxLlenguaActionPerformed

    private void menuTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuTableMouseClicked

    }//GEN-LAST:event_menuTableMouseClicked

    private void buttonLogInMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonLogInMouseClicked

    }//GEN-LAST:event_buttonLogInMouseClicked

    private void loginUser() {

    }

    private void buttonRegisterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonRegisterMouseClicked
        registerClicked();
    }//GEN-LAST:event_buttonRegisterMouseClicked

    private void buttonMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonMenuMouseClicked
        selectMenuClicked();
    }//GEN-LAST:event_buttonMenuMouseClicked

    private void radioButtonFoscActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioButtonFoscActionPerformed
        FlatLaf.setup(new FlatDarkLaf());
        SwingUtilities.updateComponentTreeUI(this);
    }//GEN-LAST:event_radioButtonFoscActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(InitFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InitFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InitFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InitFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InitFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Configuracio;
    private javax.swing.JPanel Llicencia;
    private javax.swing.JPanel MenuDelDia;
    private javax.swing.JPanel Usuari;
    private javax.swing.JTabbedPane allPanels;
    private javax.swing.JButton buttonLogIn;
    private javax.swing.JButton buttonMenu;
    private javax.swing.JButton buttonRegister;
    private javax.swing.JButton buttonSelectFile;
    private javax.swing.JComboBox<String> comboBoxLlengua;
    private javax.swing.JLabel labelClau;
    private javax.swing.JLabel labelLlengua;
    private javax.swing.JLabel labelTema;
    private javax.swing.JLabel labelTitleMenu;
    private javax.swing.JLabel labelUsuari;
    private javax.swing.JTable menuTable;
    private javax.swing.JPanel panelConfiguracio;
    private javax.swing.JPanel panelCredencials;
    private javax.swing.JPanel panelLlicencia;
    private javax.swing.JPanel panelMenu;
    private javax.swing.JPanel panelMenuButton;
    private javax.swing.JRadioButton radioButtonClar;
    private javax.swing.JRadioButton radioButtonFosc;
    private javax.swing.JButton saveConfigButton;
    private javax.swing.JScrollPane scrollTable;
    private javax.swing.JLabel textButtonSelectFile;
    private javax.swing.JTextField textFieldPassword;
    private javax.swing.JTextField textFieldUsuari;
    private javax.swing.JLabel titleConfig;
    private javax.swing.JLabel titleUsuari;
    // End of variables declaration//GEN-END:variables
}
