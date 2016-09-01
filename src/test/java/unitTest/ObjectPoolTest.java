package unitTest;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
    public void conexionesLimite5Valido() {
        int idRandom = new Random().nextInt(10000 - 2000 + 1) + 2000;
        Assert.assertTrue(Programa.insertarResgistro("Prueba" + String.valueOf(idRandom)));
    }
    
    @Test(invocationCount = 6, threadPoolSize = 6)
    public void conexionesLimite6Invalido(){
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
    }
    
    @BeforeClass
    public static void setUpClass() {
        Programa.crearPoolConexiones();
        Programa.crearTabla();
    }

    @AfterClass
    public static void tearDownClass() {
        Programa.borrarTabla();
        Programa.cerrarPoolConexiones();       
    }

    @BeforeMethod(firstTimeOnly = true)
    public void setUpMethod() {
        
    }

    @AfterMethod(lastTimeOnly = true)
    public void tearDownMethod() throws Exception {
        System.out.println("Médoto terminado para debug de pool:");
        System.out.println(Programa.getPoolBasesDatos().getNumIdle());
        System.out.println(Programa.getPoolBasesDatos().getBorrowedCount());
        System.out.println(Programa.getPoolBasesDatos().getCreatedCount());
    }
}