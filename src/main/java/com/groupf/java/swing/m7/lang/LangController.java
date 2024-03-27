/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.groupf.java.swing.m7.lang;

import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.JSONObject;



/**
 *
 * @author Baker
 */
public class LangController {

    public JSONObject loadLanguage(String language) {
        try {
            String langPath = "./lang/";
            switch (language) {
                case "cat": // Català
                    langPath += "cat.json";
                    break;
                case "es": // Castellano
                    langPath += "es.json";
                    break;
                case "en": // English
                    langPath += "en.json";
                    break;
                    
                default:
                    return null;
            }
            
            String content = new String(Files.readAllBytes(Paths.get(langPath)));
            
            JSONObject jsonObjectLang = new JSONObject(content);
            
            return jsonObjectLang;
        } catch (Exception e) {
            return null;
        }
    }
}
