/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edo.uelbosque.apachepool.util;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import org.json.JSONException;
import org.json.JSONObject;
/**
 *
 * @author JORGE E GANTIVA O
 */
public class ManejadorArchivos {
    
    public static JSONObject getJsonFromResource(Object context, String resourceName) throws Exception {
        
        JSONObject jsonObject;
        
        try {
            URL resource = context.getClass().getResource(resourceName);
            File file = new File(resource.toURI());
            file.setReadOnly();
            FileInputStream fileInput = new FileInputStream(file);

            byte[] data = new byte[(int) file.length()];
            fileInput.read(data);
            fileInput.close();

            String strFile = new String(data, "UTF-8");
            jsonObject = new JSONObject(strFile);
        }
        catch (IOException e) {
            throw new Exception("Error de lectura de archivo JSON: " + e.getMessage());
        }
        catch (JSONException e) {
            throw new Exception("Error convirtiendo archivo JSON: " + e.getMessage());
        }
        return jsonObject;  
    }
}