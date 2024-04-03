package com.groupf.java.swing.m7;



import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import com.groupf.java.swing.m7.interfaces.InitFrame;

import javax.swing.*;
import org.json.JSONObject;

/**
 *
 * @author Baker, Cramcat & Don Eduardo
 */
public class Main {

    public static void main(String[] args) {
        FlatLaf.setup(new FlatMacLightLaf());
        SwingUtilities.invokeLater(() -> {
            InitFrame initFrame = new InitFrame();
        });
    }
}
