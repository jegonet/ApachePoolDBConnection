/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edo.uelbosque.apachepool.ui;

import co.edo.uelbosque.apachepool.dao.ManejadorBaseDatos;
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
    
    private static void crearPoolConexiones(){
        
        GenericObjectPoolConfig configPool = new GenericObjectPoolConfig();
        configPool.setMaxIdle(1);
        configPool.setMaxTotal(5);
        
        configPool.setTestOnBorrow(true);
        configPool.setTestOnReturn(true);
        
        poolBasesDatos = new BDPool(new BDPoolFactory(), configPool);
    }
    
    public static boolean inicializar(){

        crearPoolConexiones();
        
        try { 
            ManejadorBaseDatos oBaseDatos = poolBasesDatos.borrowObject();
            TablaPrueba.crearTabla(oBaseDatos);
        }
        catch(Exception ex) {
            ManejadorMensajes.mostrarError(ex);
            return false;
        }
        return true;
    }
    
    public static boolean insertarResgistro(long id, String name){
        
        try { 
            ManejadorBaseDatos oBaseDatos = poolBasesDatos.borrowObject();
            TablaPrueba.insertarDato(oBaseDatos, id, name);
        }
        catch(Exception ex) {
            ManejadorMensajes.mostrarError(ex);
            return false;
        }
        return true;
    }
    
    public static boolean borrarTabla(){
        
        try { 
            ManejadorBaseDatos oBaseDatos = poolBasesDatos.borrowObject();
            TablaPrueba.eliminarTabla(oBaseDatos);
        }
        catch(Exception ex) {
            ManejadorMensajes.mostrarError(ex);
            return false;
        }
        return true;
    }
}