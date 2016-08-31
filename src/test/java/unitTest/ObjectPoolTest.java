package unitTest;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import co.edo.uelbosque.apachepool.ui.Programa;
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

    @Test
    public void conexionesLimite5Valido() {
         Assert.assertTrue(Programa.insertarResgistro(1, "Prueba"));
    }
    
    @Test
    public void conexionesLimite6Invalido(){
        Assert.assertTrue(Programa.insertarResgistro(1, "Prueba"));
    }
    
    @Test
    public void crear10000RegistrosValido(){
        
        boolean resultadoPrueba = true;
        
        for(int i=0; i<10000; i++) {
            resultadoPrueba = resultadoPrueba || Programa.insertarResgistro(i, 
                    "nombre" + String.valueOf(i));
        }
        
        Assert.assertTrue(resultadoPrueba);
    }
    
    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @BeforeMethod
    public void setUpMethod() {
        Programa.inicializar();
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
        Programa.borrarTabla();
    }
}