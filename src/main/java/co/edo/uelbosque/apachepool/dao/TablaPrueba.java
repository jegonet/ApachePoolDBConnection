/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edo.uelbosque.apachepool.dao;

import java.sql.SQLException;

/**
 
 * @author Jorge Eliecer Gantiva Ochoa
 */
public class TablaPrueba {
    public static void crearTabla(ManejadorBaseDatos oBaseDatos) throws SQLException{
        oBaseDatos.ejecutarSentencia("CREATE TABLE table1(id BIGINT NOT NULL, name VARCHAR(30) NOT NULL, CONSTRAINT pk_table1 PRIMARY KEY (id));");
    }
    
    public static void eliminarTabla(ManejadorBaseDatos oBaseDatos) throws SQLException{
        oBaseDatos.ejecutarSentencia("DROP TABLE table1;");
    }
    
    public static void insertarDato(ManejadorBaseDatos oBaseDatos, long id, String name) throws SQLException{
        oBaseDatos.ejecutarSentencia("INSERT INTO table1(id, name) VALUES (" + 
                String.valueOf(id) + ", '" + name +"');");
    }
}