
package com.groupf.java.swing.m7.lang;

import com.groupf.java.swing.m7.interfaces.InitFrame;
import static com.groupf.java.swing.m7.interfaces.InitFrame.langObject;
import static com.groupf.java.swing.m7.interfaces.InitFrame.translationsObject;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.JSONObject;

/**
 *
 * @author Baker
 */
public class LangController {

    public Boolean loadLanguage(String language) {
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
                    return false;
            }

            String content = new String(Files.readAllBytes(Paths.get(langPath)));

            JSONObject jsonObjectLang = new JSONObject(content);
            InitFrame.langObject = jsonObjectLang;
            InitFrame.translationsObject = InitFrame.langObject.getJSONObject("translations");

            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
