
package com.groupf.java.swing.m7.messages;

import javax.swing.JOptionPane;

/**
 *
 * @author Baker
 */
public class MessageBox {

    public void successMessageBox(String title, String message) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    public void errorMessageBox(String title, String message) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
    }

}
