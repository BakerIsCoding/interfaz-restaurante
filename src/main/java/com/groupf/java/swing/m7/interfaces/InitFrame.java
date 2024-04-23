package com.groupf.java.swing.m7.interfaces;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import com.groupf.java.swing.m7.database.DatabaseController;
import com.groupf.java.swing.m7.lang.LangController;
import com.groupf.java.swing.m7.messages.MessageBox;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import javax.swing.table.TableColumnModel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Baker
 */
public class InitFrame extends javax.swing.JFrame {

    /**
     * Creates new form InitFrame
     */
    public static JSONObject langObject = new JSONObject();
    public static JSONObject translationsObject = new JSONObject();
    public static String uid = "0";
    public static Integer menuValue = 0;

    public InitFrame() {
        DatabaseController db = DatabaseController.getInstance();
        LangController langController = new LangController();
        MessageBox msg = new MessageBox();
        initComponents();
        setTitle("Restaurant Can Pedro");
        setSize(760, 560);
        setLocationRelativeTo(null);

        setResizable(false);
        setVisible(true);

        /*
         * LLICENCIA
         * DISABLE PANELS
         */
        allPanels.setEnabledAt(1, false);
        allPanels.setEnabledAt(2, false);
        comboBoxLlengua.setSelectedIndex(1);
        saveConfigButton.setEnabled(false);
        jComboBoxMenu.setSelectedIndex(1);

        /* MENU */
 /* COMBO BOX MENU*/
        jComboBoxMenu.removeAllItems();
        jComboBoxMenu.addItem("Menú 1");
        jComboBoxMenu.addItem("Menú 2");
        /*
         * SETTINGS
         * RADIO BUTTONS
         */
        radioButtonClar.setSelected(true);
        ButtonGroup group = new ButtonGroup();
        group.add(radioButtonClar);
        group.add(radioButtonFosc);

        /* COMBO BOX Llengüa*/
        comboBoxLlengua.removeAllItems();
        comboBoxLlengua.addItem("Català");
        comboBoxLlengua.addItem("Castellano");
        comboBoxLlengua.addItem("English");

        /* Load lengua */
        try {
            Boolean isDoneLoading = langController.loadLanguage("es");
            if (isDoneLoading) {
                // translationsObject = langObject.getJSONObject("translations");
                doTranslations();
            } else {
                msg.errorMessageBox("Error al cargar traduccioens", "Error al cargar traduccioens");
            }
        } catch (Exception e) {
            msg.errorMessageBox("Error al cargar traduccioens", "Error al cargar traduccioens");
        }

    }

    private void doTranslations() throws Exception {
        try {

            // Panels
            allPanels.setTitleAt(0, translationsObject.getString("license"));
            allPanels.setTitleAt(1, translationsObject.getString("user"));
            allPanels.setTitleAt(2, translationsObject.getString("menu"));
            allPanels.setTitleAt(3, translationsObject.getString("config"));

            // Llicencia
            textButtonSelectFile.setText(translationsObject.getString("license"));
            buttonSelectFile.setText(translationsObject.getString("license_button"));

            // Usuari
            titleUsuari.setText(translationsObject.getString("user_credentials"));
            labelUsuari.setText(translationsObject.getString("user_username"));
            labelClau.setText(translationsObject.getString("user_password"));
            buttonRegister.setText(translationsObject.getString("user_register_button"));
            buttonLogIn.setText(translationsObject.getString("user_login_button"));

            // Menú diari
            labelTitleMenu.setText(translationsObject.getString("menu"));
            jLabelMenu.setText(translationsObject.getString("tria_menu"));
            buttonMenu.setText(translationsObject.getString("menu_acceptar"));
            TableColumnModel columnModel = menuTable.getColumnModel();
            columnModel.getColumn(0).setHeaderValue(translationsObject.getString("menu_table_name"));
            columnModel.getColumn(1).setHeaderValue(translationsObject.getString("menu_table_price"));
            columnModel.getColumn(2).setHeaderValue(translationsObject.getString("menu_table_order"));
            columnModel.getColumn(3).setHeaderValue(translationsObject.getString("menu_table_allergens"));
            menuTable.repaint();

            // Config
            titleConfig.setText(translationsObject.getString("config"));
            labelTema.setText(translationsObject.getString("config_theme"));
            radioButtonClar.setText(translationsObject.getString("config_theme_light"));
            radioButtonFosc.setText(translationsObject.getString("config_theme_dark"));
            labelLlengua.setText(translationsObject.getString("config_language"));
            saveConfigButton.setText(translationsObject.getString("config_button_save"));
        } catch (Exception e) {
            throw e;
        }
    }

    private void registerClicked() throws Exception {
        MessageBox msg = new MessageBox();
        DatabaseController db = DatabaseController.getInstance();
        String username = textFieldUsuari.getText();
        String password = jPasswordFieldPass.getText();

        if (username.length() <= 2) {
            msg.errorMessageBox(translationsObject.getString("user_register_error_title"),
                    translationsObject.getString("user_register_error_length_text"));
            return;
        }

        if (password.length() <= 5) {
            msg.errorMessageBox(translationsObject.getString("user_register_error_title"),
                    translationsObject.getString("user_register_error_password_text"));
            return;
        }
        Boolean isUsernameTaken = db.isUsernameTaken(username);
        if (isUsernameTaken) {
            msg.errorMessageBox(translationsObject.getString("user_register_error_title"), translationsObject.getString("user_register_error_taken_text"));
            return;
        }

        List<String> listaUser = new ArrayList<>(3);
        listaUser.add(username);
        listaUser.add(password);

        RegisterFrame register = new RegisterFrame(listaUser);
        register.setDefaultCloseOperation(RegisterFrame.DISPOSE_ON_CLOSE);
    }

    private void selectMenuClicked(int selectedIndex) {
        
        GuiCambrerFrame cambrerInstance = GuiCambrerFrame.getInstance();
        cambrerInstance.setDefaultCloseOperation(RegisterFrame.DISPOSE_ON_CLOSE);

    }

    private void textFieldUsuariActionPerformed(java.awt.event.ActionEvent evt) {

    }

    private void buttonRegisterActionPerformed(java.awt.event.ActionEvent evt) {

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
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
        buttonLogIn = new javax.swing.JButton();
        buttonRegister = new javax.swing.JButton();
        jPasswordFieldPass = new javax.swing.JPasswordField();
        MenuDelDia = new javax.swing.JPanel();
        panelMenu = new javax.swing.JPanel();
        labelTitleMenu = new javax.swing.JLabel();
        scrollTable = new javax.swing.JScrollPane();
        menuTable = new javax.swing.JTable();
        panelMenuButton = new javax.swing.JPanel();
        buttonMenu = new javax.swing.JButton();
        jComboBoxMenu = new javax.swing.JComboBox<>();
        jLabelMenu = new javax.swing.JLabel();
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
        textFieldUsuari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldUsuariActionPerformed(evt);
            }
        });

        labelClau.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        labelClau.setText("Clau:");

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
        buttonRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRegisterActionPerformed(evt);
            }
        });

        jPasswordFieldPass.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

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
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCredencialsLayout.createSequentialGroup()
                        .addGroup(panelCredencialsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPasswordFieldPass, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelCredencialsLayout.createSequentialGroup()
                                .addComponent(buttonLogIn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                                .addComponent(buttonRegister)))
                        .addGap(30, 30, 30))
                    .addGroup(panelCredencialsLayout.createSequentialGroup()
                        .addComponent(textFieldUsuari, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
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
                .addGap(18, 18, 18)
                .addComponent(jPasswordFieldPass, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
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
                "null", "null", "null", "null"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        menuTable.getTableHeader().setReorderingAllowed(false);
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
        buttonMenu.setText("Aceptar");
        buttonMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonMenuMouseClicked(evt);
            }
        });
        buttonMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonMenuActionPerformed(evt);
            }
        });

        jComboBoxMenu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jComboBoxMenu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxMenuActionPerformed(evt);
            }
        });

        jLabelMenu.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelMenu.setText("Seleccionar Menú:");

        javax.swing.GroupLayout panelMenuButtonLayout = new javax.swing.GroupLayout(panelMenuButton);
        panelMenuButton.setLayout(panelMenuButtonLayout);
        panelMenuButtonLayout.setHorizontalGroup(
            panelMenuButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMenuButtonLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jComboBoxMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(buttonMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79))
        );
        panelMenuButtonLayout.setVerticalGroup(
            panelMenuButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMenuButtonLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelMenuButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonMenu)
                    .addComponent(jComboBoxMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
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
                .addContainerGap())
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
        saveConfigButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                saveConfigButtonMouseClicked(evt);
            }
        });
        saveConfigButton.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                saveConfigButtonKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout panelConfiguracioLayout = new javax.swing.GroupLayout(panelConfiguracio);
        panelConfiguracio.setLayout(panelConfiguracioLayout);
        panelConfiguracioLayout.setHorizontalGroup(
            panelConfiguracioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(titleConfig, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelConfiguracioLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(panelConfiguracioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelConfiguracioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelConfiguracioLayout.createSequentialGroup()
                            .addComponent(radioButtonClar, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(radioButtonFosc, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE))
                        .addComponent(labelLlengua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private void jComboBoxMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxMenuActionPerformed
        int selectedIndex = jComboBoxMenu.getSelectedIndex();

        switch (selectedIndex) {
            case 0: // menu 1
                loadMenu(1);
                break;
            case 1: // menu 2
                loadMenu(2);
                break;
        }
    }//GEN-LAST:event_jComboBoxMenuActionPerformed
    private void loadMenu(int menuNumber) {
        DatabaseController db = DatabaseController.getInstance();
        TableModel tableModel = menuTable.getModel();
        // Realiza la consulta para obtener el menú desde la base de datos
        String consulta = "SELECT json FROM menu WHERE nombre = '" + menuNumber + "'";
        ResultSet rs = db.ejecutarConsulta(consulta);

        try {
            if (tableModel instanceof DefaultTableModel) {
                DefaultTableModel defaultModel = (DefaultTableModel) tableModel;

                // Borra todas las filas existentes en la tabla antes de cargar los datos
                defaultModel.setRowCount(0);

                // Itera sobre el ResultSet y agrega los datos al modelo de la tabla
                while (rs.next()) {
                    String jsonString = rs.getString("json");
                    JSONArray jsonArray = new JSONArray(jsonString);

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        Object[] fila = {
                            jsonObject.getString("Plato"),
                            jsonObject.getDouble("Precio"),
                            jsonObject.getString("Tipo"),
                            jsonObject.getString("Alérgenos")
                        };
                        defaultModel.addRow(fila);
                    }
                }
            }
            // Cierra el ResultSet después de usarlo
            rs.close();

        } catch (SQLException | JSONException e) {
            // Manejar excepciones
            e.printStackTrace();
        }
    }

    private void buttonSelectFileMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_buttonSelectFileMouseClicked
        MessageBox msg = new MessageBox();
        JFileChooser fileChooser = new JFileChooser();

        // Filtrar solo archivos .csv
        FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV Files", "csv");
        fileChooser.setFileFilter(filter);
        fileChooser.setDialogTitle(translationsObject.getString("license_button_dialog_text"));

        // Mostrar el diálogo para abrir archivo
        int seleccion = fileChooser.showOpenDialog(null);

        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            //System.out.println("Archivo seleccionado: " + selectedFile.getAbsolutePath());

            // Procesar el archivo seleccionado
            Boolean isValid = procesarArchivoLicencia(selectedFile);

            if (isValid) {
                allPanels.setSelectedIndex(1);
                allPanels.setEnabledAt(0, false);
                allPanels.setEnabledAt(1, true);

                msg.successMessageBox(translationsObject.getString("license_success_title"), translationsObject.getString("license_success_text"));
            }
        }

    }// GEN-LAST:event_buttonSelectFileMouseClicked

    private Boolean procesarArchivoLicencia(File archivo) {
        DatabaseController dbController = DatabaseController.getInstance();
        MessageBox msg = new MessageBox();
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                try {
                    String[] partes = linea.split(",");

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
                                msg.errorMessageBox(translationsObject.getString("license_error_verification_text"),
                                        translationsObject.getString("license_error_verification_text"));
                            }

                        } else {
                            System.out.println();
                            msg.errorMessageBox(translationsObject.getString("license_error_title"),
                                    translationsObject.getString("license_error_expired_text") + fechaLicencia);
                            return false;
                        }
                    }
                } catch (Exception e) {
                    msg.errorMessageBox(translationsObject.getString("license_error_verification_text"),
                            translationsObject.getString("license_error_verification_text"));
                }

            }
        } catch (IOException e) {
            msg.errorMessageBox(translationsObject.getString("license_error_reading_file_title"),
                    translationsObject.getString("license_error_reading_file_text"));
            System.err.println("Error al leer el archivo: " + e.getMessage());
            return false;
        }
        return false;
    }

    private void setWhiteTheme() {
        FlatLaf.setup(new FlatMacLightLaf());
        SwingUtilities.updateComponentTreeUI(this);
    }

    private void radioButtonClarActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_radioButtonClarActionPerformed
        setWhiteTheme();
    }// GEN-LAST:event_radioButtonClarActionPerformed

    private void comboBoxLlenguaActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_comboBoxLlenguaActionPerformed
        LangController lang = new LangController();
        // Obtenim l'index
        int selectedIndex = comboBoxLlengua.getSelectedIndex();
        switch (selectedIndex) {
            case 0: // Català
                loadCat();
                break;
            case 1: // Castellano
                loadEs();
                break;
            case 2: // English
                loadEn();
                break;
        }
    }

    private void loadCat() {
        LangController lang = new LangController();
        MessageBox msg = new MessageBox();
        Boolean isLangLoaded = false;
        isLangLoaded = lang.loadLanguage("cat");
        if (isLangLoaded) {
            try {
                doTranslations();
            } catch (Exception e) {
                msg.errorMessageBox("Error al cargar traduccioens", "Error al cargar traduccioens");
            }
        }
    }

    private void loadEs() {
        LangController lang = new LangController();
        MessageBox msg = new MessageBox();
        Boolean isLangLoaded = false;
        isLangLoaded = lang.loadLanguage("es");
        if (isLangLoaded) {
            try {
                doTranslations();
            } catch (Exception e) {
                msg.errorMessageBox("Error al cargar traduccioens", "Error al cargar traduccioens");
            }
        }
    }

    private void loadEn() {
        LangController lang = new LangController();
        MessageBox msg = new MessageBox();
        Boolean isLangLoaded = false;
        isLangLoaded = lang.loadLanguage("en");
        if (isLangLoaded) {
            try {
                doTranslations();
            } catch (Exception e) {
                msg.errorMessageBox("Error al cargar traduccioens", "Error al cargar traduccioens");
            }
        }
    }

    private void menuTableMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_menuTableMouseClicked

    }// GEN-LAST:event_menuTableMouseClicked

    private void buttonLogInMouseClicked(java.awt.event.MouseEvent evt) {
        try {
            // GEN-FIRST:event_buttonLogInMouseClicked
            loginUser();
        } catch (Exception ex) {
            Logger.getLogger(InitFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

    }// GEN-LAST:event_buttonLogInMouseClicked

    private void loginUser() throws Exception {
        DatabaseController db = DatabaseController.getInstance();
        MessageBox msg = new MessageBox();

        String username = textFieldUsuari.getText();
        String password = jPasswordFieldPass.getText();

        if (db.existeUsuario(username, password)) {

            uid = String.valueOf(db.getUserId(username, password));

            String userType = db.userType(uid);

            //Cambrer
            if (userType.equals("cambrer")) {
                allPanels.setEnabledAt(0, false);
                allPanels.setEnabledAt(1, false);
                allPanels.setEnabledAt(2, true);
                allPanels.setEnabledAt(3, true);

                allPanels.setSelectedIndex(2);

                //Cuiner
            } else {
                allPanels.setEnabledAt(0, false);
                allPanels.setEnabledAt(1, false);
                allPanels.setEnabledAt(2, false);
                allPanels.setEnabledAt(3, true);
                GuiCuiner cuiner = new GuiCuiner();
                cuiner.setVisible(true);

                allPanels.setSelectedIndex(3);

            }

            saveConfigButton.setEnabled(true);
            applyConfig();
            msg.successMessageBox(translationsObject.getString("user_login_successful_title"), translationsObject.getString("user_login_successful_text"));

        } else {
            msg.errorMessageBox(translationsObject.getString("user_login_error_title"), translationsObject.getString("user_login_error_text"));
        }
    }

    private void applyConfig() {
        DatabaseController db = DatabaseController.getInstance();
        Integer theme = db.getThemeById(uid);
        String lang = db.getLangById(uid);

        if (theme == 0) {
            setWhiteTheme();
            radioButtonClar.setSelected(true);
        } else {
            setBlackTheme();
            radioButtonFosc.setSelected(true);
        }

        if (lang.equals("cat")) {
            loadCat();
            comboBoxLlengua.setSelectedIndex(0);
        } else if (lang.equals("es")) {
            loadEs();
            comboBoxLlengua.setSelectedIndex(1);
        } else {
            loadEn();
            comboBoxLlengua.setSelectedIndex(2);
        }
    }

    private void buttonRegisterMouseClicked(java.awt.event.MouseEvent evt) {
        try {
            // GEN-FIRST:event_buttonRegisterMouseClicked
            registerClicked();
        } catch (Exception ex) {
            Logger.getLogger(InitFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }// GEN-LAST:event_buttonRegisterMouseClicked

    private void buttonMenuMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_buttonMenuMouseClicked

        int selectedIndex = jComboBoxMenu.getSelectedIndex();

        selectMenuClicked(selectedIndex);
        switch (selectedIndex) {
            case 0: // menu 1
                menuValue = 1;
                break;
            case 1: // menu 2
                menuValue = 2;
                break;
        }
    }// GEN-LAST:event_buttonMenuMouseClicked

    public void setBlackTheme() {
        FlatLaf.setup(new FlatMacDarkLaf());
        SwingUtilities.updateComponentTreeUI(this);
    }

    private void radioButtonFoscActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_radioButtonFoscActionPerformed
        setBlackTheme();

    }// GEN-LAST:event_radioButtonFoscActionPerformed

    private void buttonMenuActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_buttonMenuActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_buttonMenuActionPerformed

    private void saveConfigButtonKeyPressed(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_saveConfigButtonKeyPressed

    }// GEN-LAST:event_saveConfigButtonKeyPressed

    private void saveConfigButtonMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_saveConfigButtonMouseClicked
        DatabaseController db = DatabaseController.getInstance();
        MessageBox msg = new MessageBox();
        Boolean configExisting = db.existsConfig("1");
        Integer theme = 0;
        String lang = "cat";
        Boolean isQuerySuccessful = false;

        if (!saveConfigButton.isEnabled()) {
            msg.errorMessageBox("Error", "Necesitas iniciar sesión para guardar tu configuración");
            return;
        }

        if (radioButtonClar.isSelected()) {
            theme = 0;
        } else if (radioButtonFosc.isSelected()) {
            theme = 1;
        }
        int selectedIndex = comboBoxLlengua.getSelectedIndex();
        switch (selectedIndex) {
            case 0: // Català
                lang = "cat";
                break;
            case 1: // Castellano
                lang = "es";
                break;
            case 2: // English
                lang = "en";
                break;
        }

        if (configExisting) {
            isQuerySuccessful = db.updateConfig("1", theme, lang);
        } else {
            isQuerySuccessful = db.saveConfig("1", theme, lang);
        }

        if (isQuerySuccessful) {
            msg.successMessageBox(translationsObject.getString("config_button_save_success_title"),
                    translationsObject.getString("config_button_save_success_text"));
        } else {
            msg.errorMessageBox(translationsObject.getString("config_button_save_error_title"),
                    translationsObject.getString("config_button_save_error_text"));
        }
    }// GEN-LAST:event_saveConfigButtonMouseClicked

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
            java.util.logging.Logger.getLogger(InitFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InitFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InitFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InitFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        // </editor-fold>

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
    private javax.swing.JComboBox<String> jComboBoxMenu;
    private javax.swing.JLabel jLabelMenu;
    private javax.swing.JPasswordField jPasswordFieldPass;
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
    private javax.swing.JTextField textFieldUsuari;
    private javax.swing.JLabel titleConfig;
    private javax.swing.JLabel titleUsuari;
    // End of variables declaration//GEN-END:variables
}
