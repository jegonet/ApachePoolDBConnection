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
}