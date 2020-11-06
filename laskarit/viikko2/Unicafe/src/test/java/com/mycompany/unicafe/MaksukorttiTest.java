package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(1337);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }

    @Test
    public void kortinSaldoAlussaOikein() {
        assertEquals(1337, kortti.saldo());
    }

    @Test
    public void rahanLataaminenKasvattaaSaldoaOikein() {
        kortti.lataaRahaa(667);
        assertEquals(2004, kortti.saldo());
    }

    @Test
    public void saldoVaheneeOikeinJosRahaaOnTarpeeksi() {
        kortti.otaRahaa(667);
        assertEquals(670, kortti.saldo());
    }

    @Test
    public void saldoEiMuutuJosRahaaEiOleTarpeeksi() {
        kortti.otaRahaa(31337);
        assertEquals(1337, kortti.saldo());
    }

    @Test
    public void metodiPalauttaaTrueJosRahatRiittivat() {
        assertTrue(kortti.otaRahaa(667));
    }

    @Test
    public void metodiPalauttaaFalseJosRahatEivatRiita() {
        assertFalse(kortti.otaRahaa(31337));
    }

    @Test
    public void toStringToimii() {
        assertEquals("saldo: 13.37", kortti.toString());
    }
}
