/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edo.uelbosque.apachepool.dao;

import co.edo.uelbosque.apachepool.dao.pool.BDPool;

/**
 
 * @author Jorge Eliecer Gantiva Ochoa
 */
public class TablaPrueba {
    public static void crearTabla(BDPool poolBasesDatos) throws Exception{
        
        ManejadorBaseDatos oBaseDatos = null;
        try { 
            oBaseDatos = poolBasesDatos.borrowObject();
            oBaseDatos.ejecutarSentencia("CREATE TABLE IF NOT EXISTS table1(id SERIAL, name VARCHAR(30) NOT NULL);");
        }
        catch(Exception ex) {
            throw ex;
        }
        finally {
            if(oBaseDatos!=null)
                poolBasesDatos.returnObject(oBaseDatos);
        }
    }
    
    public static void eliminarTabla(BDPool poolBasesDatos) throws Exception{
        
        ManejadorBaseDatos oBaseDatos = null;
        try { 
            oBaseDatos = poolBasesDatos.borrowObject();
            oBaseDatos.ejecutarSentencia("DROP TABLE table1;");
        }
        catch(Exception ex) {
            throw ex;
        }
        finally {
            if(oBaseDatos!=null)
                poolBasesDatos.returnObject(oBaseDatos);
        }
    }
    
    public static void insertarDato(BDPool poolBasesDatos, String name) throws Exception{
        
        ManejadorBaseDatos oBaseDatos = null;
        try { 
            oBaseDatos = poolBasesDatos.borrowObject();
            oBaseDatos.ejecutarSentencia("INSERT INTO table1(name) VALUES ('" + name +"');");
        }
        catch(Exception ex) {
            throw ex;
        }
        finally {
            if(oBaseDatos!=null)
                poolBasesDatos.returnObject(oBaseDatos);
        }
    }
}