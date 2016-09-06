package unitTest;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import co.edo.uelbosque.apachepool.dao.ManejadorBaseDatos;
import co.edo.uelbosque.apachepool.dao.pool.BDPool;
import co.edo.uelbosque.apachepool.ui.Programa;
import java.util.Random;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Jorge Eliecer Gantiva Ochoa
 */
public class ObjectPoolTest {
    
    public ObjectPoolTest() {
    }

    @Test(invocationCount = 5, threadPoolSize = 5)
    public void conexionesLimite5() {
        int idRandom = new Random().nextInt(10000 - 2000 + 1) + 2000;
        Assert.assertTrue(Programa.insertarResgistro("Prueba" + String.valueOf(idRandom)));
    }
    
    @Test(invocationCount = 10, threadPoolSize = 10)
    public void conexionesLimite10(){
        int idRandom = new Random().nextInt(10000 - 2000 + 1) + 2000;
        Assert.assertTrue(Programa.insertarResgistro("Prueba" + String.valueOf(idRandom)));
    }
    
    @Test
    public void crear1000RegistrosValido(){
        
        boolean resultadoPrueba = true;
        
        for(int i=0; i<1000; i++) {
            boolean resultadoOperacion = Programa.insertarResgistro("nombre" + String.valueOf(i));
            resultadoPrueba = resultadoPrueba || resultadoOperacion;
        }
        
        Assert.assertTrue(resultadoPrueba);
        Assert.assertEquals(Programa.getPoolBasesDatos().getCreatedCount(), 1); //solo debe crear una conexion
    }
    
    @Test
    public void validarLimpiezaPool() throws Exception{
        
        BDPool pool = Programa.getPoolBasesDatos();
        ManejadorBaseDatos oBaseDatos = null;
        try { 
            oBaseDatos = pool.borrowObject();
            oBaseDatos.consultar("SELECT 1;");
            
            oBaseDatos.destruirConexion();
        }
        catch(Exception ex) {
            throw ex;
        }
        finally {
            if(oBaseDatos!=null)
                pool.returnObject(oBaseDatos);
        }
        
        try { 
            oBaseDatos = pool.borrowObject();
            oBaseDatos.consultar("SELECT 1;");
        }
        catch(Exception ex) {
            throw ex;
        }
        finally {
            if(oBaseDatos!=null)
                pool.returnObject(oBaseDatos);
        }
        
        Assert.assertEquals(pool.getDestroyedCount(), 1); //debe destruir una conexion dañada
        Assert.assertEquals(pool.getCreatedCount(), 2); //debió crear dos conexiones, debido a que la primera fue dañana
    }

    @BeforeMethod(firstTimeOnly = true)
    public void setUpMethod() {
        Programa.crearPoolConexiones();
        Programa.crearTabla();
    }

    @AfterMethod(lastTimeOnly = true)
    public void tearDownMethod() throws Exception {
        System.out.println("Médoto terminado para debug de pool:");
        System.out.println(Programa.getPoolBasesDatos().getNumIdle());
        System.out.println(Programa.getPoolBasesDatos().getBorrowedCount());
        System.out.println(Programa.getPoolBasesDatos().getCreatedCount());
        
        Programa.borrarTabla();
        Programa.cerrarPoolConexiones();  
    }
    
    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }
}