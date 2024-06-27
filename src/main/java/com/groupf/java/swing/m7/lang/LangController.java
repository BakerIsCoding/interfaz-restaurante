package com.groupf.java.swing.m7.lang;

import com.groupf.java.swing.m7.interfaces.InitFrame;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import org.json.JSONObject;

/**
 *
 * @author Baker
 */
public class LangController {

    public Boolean loadLanguage(String language) {
        try {
            String langPath = "/com/lang/";
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
                    return false;
            }

            InputStream inputStream = LangController.class.getResourceAsStream(langPath);
            if (inputStream == null) {
                return false;
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder contentBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                contentBuilder.append(line);
            }

            String content = contentBuilder.toString();

            JSONObject jsonObjectLang = new JSONObject(content);
            InitFrame.langObject = jsonObjectLang;
            InitFrame.translationsObject = InitFrame.langObject.getJSONObject("translations");

            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
