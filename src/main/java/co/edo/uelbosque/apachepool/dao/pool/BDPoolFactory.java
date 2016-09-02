/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edo.uelbosque.apachepool.dao.pool;

import co.edo.uelbosque.apachepool.dao.ManejadorBaseDatos;
import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

/**
 *
 * @author Jorge Eliecer Gantiva Ochoa
 */
public class BDPoolFactory extends BasePooledObjectFactory<ManejadorBaseDatos> {
    
    @Override
    public ManejadorBaseDatos create() throws Exception {
        return new ManejadorBaseDatos();
    }

    @Override
    public PooledObject<ManejadorBaseDatos> wrap(ManejadorBaseDatos t) {
        return new DefaultPooledObject<ManejadorBaseDatos>(t);
    }   
    
    @Override
    public boolean validateObject(PooledObject<ManejadorBaseDatos> oBaseDatosPooled){
        try{
            oBaseDatosPooled.getObject().consultar("SELECT 1;");
            //La conexion funciona!
            return true;
        }
        catch(Exception ex){
            //La conexión está caida, sacar la conexión del pool
            return false;
        }
    }
    
    @Override
    public void destroyObject(PooledObject<ManejadorBaseDatos> oBaseDatosPooled){
        oBaseDatosPooled.getObject().destruirConexion();
        oBaseDatosPooled.deallocate();
        
        try{
            super.destroyObject(oBaseDatosPooled);
        }
        catch(Exception ex){
            //Se realiza la eliminación normal
        }
        
        oBaseDatosPooled = null;
    }
}