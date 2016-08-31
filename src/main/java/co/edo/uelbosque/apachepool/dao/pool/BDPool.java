/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edo.uelbosque.apachepool.dao.pool;

import co.edo.uelbosque.apachepool.dao.ManejadorBaseDatos;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

/**
 *
 * @author JORGE E GANTIVA O
 */
public class BDPool extends GenericObjectPool<ManejadorBaseDatos> {
    
    public BDPool(PooledObjectFactory<ManejadorBaseDatos> factory, GenericObjectPoolConfig config) {
        super(factory, config);
    }
}