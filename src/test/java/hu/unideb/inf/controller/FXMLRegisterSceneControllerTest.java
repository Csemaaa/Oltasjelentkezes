/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Tamás Ádám
 */
public class FXMLRegisterSceneControllerTest {

    @Test
    public void testErvenyesEmail() {
        System.out.println("ErvenyesEmail");
        String email = "ervenyes@gmail.com";
        FXMLRegisterSceneController instance = new FXMLRegisterSceneController();
        boolean expResult = true;
        boolean result = instance.ErvenyesEmail(email);
        assertEquals(expResult, result);
        
    }

    @Test
    public void testErvenyesJelszo() {
        System.out.println("ErvenyesJelszo");
        String jelszo1 = "EROS10vagyok";
        String jelszo2 = "EROS10vagyok";
        FXMLRegisterSceneController instance = new FXMLRegisterSceneController();
        boolean expResult = true;
        boolean result = instance.ErvenyesJelszo(jelszo1, jelszo2);
        assertEquals(expResult, result);

    }
    
    @Test
    public void testErvenyesTaj()
    {
        System.out.println("ErvenyesTaj");
        int taj = 123456789;
        String tajszam = String.valueOf(taj);
        FXMLRegisterSceneController instance = new FXMLRegisterSceneController();
        boolean expResult = true;
        boolean result = instance.ErvenyesTaj(taj);
        assertEquals(expResult, result);


    }
    

    @Test
    public void testErvenyesNev() {
        System.out.println("ErvenyesNev");
        String nev = "Tamas Adam Yesboii";
        FXMLRegisterSceneController instance = new FXMLRegisterSceneController();
        boolean expResult = true;
        boolean result = instance.ErvenyesNev(nev);
        assertEquals(expResult, result);

    }



}
