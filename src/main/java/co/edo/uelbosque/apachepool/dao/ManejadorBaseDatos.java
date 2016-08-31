/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edo.uelbosque.apachepool.dao;

import co.edo.uelbosque.apachepool.util.ManejadorArchivos;
import java.sql.*;
import java.util.Properties;
import org.json.JSONObject;

/**
 *
 * @author Jorge Eliecer Gantiva Ochoa
 */
public class ManejadorBaseDatos {
    private final String dbPathServer;
    private final Properties props;
    private Connection conn;

    public ManejadorBaseDatos() throws Exception {
       
        JSONObject json = ManejadorArchivos.getJsonFromResource(this, "/connectionString2.json");
    
        this.dbPathServer = "jdbc:postgresql:" +  json.getString("hostPath");

        props = new Properties();
        props.setProperty("user", json.getString("user"));
        props.setProperty("password", json.getString("password"));

        if(json.getString("ssl").equals("true"))
            props.setProperty("ssl", json.getString("ssl"));
       
        conn = getConexionActual();
    }
    
    private Connection getConexionActual() throws SQLException {
        if(conn == null || !conn.isValid(5000) || conn.isClosed())
            conn = DriverManager.getConnection(dbPathServer, props);
        
        return conn;
    }
    
    public ResultSet consultar(String consulta) throws SQLException {
        
        Connection conn = getConexionActual();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(consulta);

        return rs;
    }

    public void ejecutarSentencia(String sentencia) throws SQLException {
        
        conn = DriverManager.getConnection(dbPathServer, props);

        Statement st = conn.createStatement();
        st.executeUpdate(sentencia);
    }
    
    public void cerrarConexionActual() throws SQLException {
        if(conn != null)
            conn.close();
    }

    public static String limpiarParametro(String valor) {
        return valor.replace('\'', '"').replace(';', ',');
    }
}