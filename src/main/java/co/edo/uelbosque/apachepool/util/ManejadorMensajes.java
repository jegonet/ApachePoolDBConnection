/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edo.uelbosque.apachepool.util;

/**
 *
 * @author Jorge Eliecer Gantiva Ochoa
 */
public class ManejadorMensajes {
    
    public static void mostrarError(Exception exception){
        System.out.println("EXCEPTION: " + exception.getMessage());
    }
}