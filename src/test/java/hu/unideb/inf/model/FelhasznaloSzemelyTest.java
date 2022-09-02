/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.model;

import java.time.LocalDate;
import java.time.Month;
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
public class FelhasznaloSzemelyTest {

    @Test
    public void testGetSzuletesiDatum() {
        FelhasznaloSzemely  szemely = new FelhasznaloSzemely();
        szemely.setSzuletesiDatum(LocalDate.of(2000, 05, 11));
        assertEquals(szemely.getSzuletesiDatum(),LocalDate.of(2000, 05, 11));
    }

    @Test
    public void testGetID() {
        FelhasznaloSzemely  szemely = new FelhasznaloSzemely();
        szemely.setID(10);
        assertEquals(szemely.getID(), 10);
    }


    @Test
    public void testGetEmail() {
        FelhasznaloSzemely  szemely = new FelhasznaloSzemely();
        szemely.setEmail("email@gmail.com");
        assertEquals(szemely.getEmail(), "email@gmail.com");

    }


    @Test
    public void testGetJelszo() {
        FelhasznaloSzemely  szemely = new FelhasznaloSzemely();

        szemely.setJelszo("erosBattya10");
        assertEquals(szemely.getJelszo(),"erosBattya10");
    }


    @Test
    public void testGetNev() {
        FelhasznaloSzemely  szemely = new FelhasznaloSzemely();
        szemely.setNev("Jancsika");
        assertEquals(szemely.getNev(), "Jancsika");

    }


    @Test
    public void testGetTAJ() {
        FelhasznaloSzemely  szemely = new FelhasznaloSzemely();

        szemely.setTAJ(123456789);
        assertEquals(szemely.getTAJ(), 123456789);

    }
}
