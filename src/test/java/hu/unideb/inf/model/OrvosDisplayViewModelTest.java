/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.model;

import org.controlsfx.control.Rating;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Tamás Ádám
 */
public class OrvosDisplayViewModelTest {
    
    @Test
    public void testGetIdopont() {
        OrvosDisplayViewModel instance = new OrvosDisplayViewModel();
        instance.setIdopont("2020");
        String result = instance.getIdopont();
        assertEquals(result, "2020");

    }

    
}
