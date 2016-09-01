/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edo.uelbosque.apachepool.ui;

import co.edo.uelbosque.apachepool.dao.TablaPrueba;
import co.edo.uelbosque.apachepool.dao.pool.BDPool;
import co.edo.uelbosque.apachepool.dao.pool.BDPoolFactory;
import co.edo.uelbosque.apachepool.util.ManejadorMensajes;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

/**
 *
 * @author Jorge Eliecer Gantiva Ochoa
 */
public class Programa {
    
    private static BDPool poolBasesDatos;
    
    public static void crearPoolConexiones(){
        
        GenericObjectPoolConfig configPool = new GenericObjectPoolConfig();
        configPool.setMaxIdle(1);
        configPool.setMaxTotal(5);
        
        configPool.setTestOnBorrow(true);
        configPool.setTestOnReturn(true);
        
        poolBasesDatos = new BDPool(new BDPoolFactory(), configPool);
    }
    
    public static boolean crearTabla(){
        
        try { 
            TablaPrueba.crearTabla(poolBasesDatos);
        }
        catch(Exception ex) {
            ManejadorMensajes.mostrarError(ex);
            return false;
        }
        return true;
    }
    
    public static boolean insertarResgistro(String name) {
        
        try { 
            TablaPrueba.insertarDato(poolBasesDatos, name);
        }
        catch(Exception ex) {
            ManejadorMensajes.mostrarError(ex);
            return false;
        }
        return true;
    }
    
    public static boolean borrarTabla(){
        
        try { 
            TablaPrueba.eliminarTabla(poolBasesDatos);
        }
        catch(Exception ex) {
            ManejadorMensajes.mostrarError(ex);
            return false;
        }
        return true;
    }
    
    public static void cerrarPoolConexiones(){
        poolBasesDatos.clear();
        poolBasesDatos.close();
    }
    
    public static BDPool getPoolBasesDatos(){
        return poolBasesDatos;
    }
}