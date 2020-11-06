package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class KassapaateTest {

    Kassapaate kassa;
    Maksukortti kortti;
    Maksukortti rahatLopussa;

    @Before
    public void setUp() {
        kassa = new Kassapaate();
        kortti = new Maksukortti(1000);
        rahatLopussa = new Maksukortti(100);
    }

    @Test
    public void uudenKassanRahamaaraOikein() {
        assertEquals(100000, kassa.kassassaRahaa());
    }

    @Test
    public void uudessaKassassaEiMyytyjaLounaita() {
        assertTrue(kassa.edullisiaLounaitaMyyty() == 0 && kassa.maukkaitaLounaitaMyyty() == 0);
    }

    @Test
    public void edullisillaSaldoKasvaaJaVaihtorahaOikeinKateisella() {
        assertEquals(260, kassa.syoEdullisesti(500));
        assertEquals(100240, kassa.kassassaRahaa());
    }

    @Test
    public void maukkaillaSaldoKasvaaJaVaihtorahaOikeinKateisella() {
        assertEquals(100, kassa.syoMaukkaasti(500));
        assertEquals(100400, kassa.kassassaRahaa());
    }

    @Test
    public void myytyjenEdullistenMaaraKasvaaJosRiittavastiKateista() {
        kassa.syoEdullisesti(500);
        kassa.syoEdullisesti(500);
        kassa.syoEdullisesti(500);
        assertTrue(kassa.edullisiaLounaitaMyyty() == 3);
    }

    @Test
    public void myytyjenMaukkaidenMaaraKasvaaJosRiittavastiKateista() {
        kassa.syoMaukkaasti(500);
        kassa.syoMaukkaasti(500);
        assertTrue(kassa.maukkaitaLounaitaMyyty() == 2);
    }

    @Test
    public void edullisillaSaldoEiMuutuJaRahatPalautetaanJosKateistaEiRiittavasti() {
        assertEquals(200, kassa.syoEdullisesti(200));
        assertEquals(100000, kassa.kassassaRahaa());
    }

    @Test
    public void maukkaillaSaldoEiMuutuJaRahatPalautetaanJosKateistaEiRiittavasti() {
        assertEquals(300, kassa.syoMaukkaasti(300));
        assertEquals(100000, kassa.kassassaRahaa());
    }

    @Test
    public void myytyjenEdullistenMaaraEiKasvaJosEiRiittavastiKateista() {
        kassa.syoEdullisesti(200);
        kassa.syoEdullisesti(230);
        kassa.syoEdullisesti(100);
        assertTrue(kassa.edullisiaLounaitaMyyty() == 0);
    }

    @Test
    public void myytyjenMaukkaidenMaaraEiKasvaJosEiRiittavastiKateista() {
        kassa.syoMaukkaasti(300);
        kassa.syoMaukkaasti(250);
        assertTrue(kassa.maukkaitaLounaitaMyyty() == 0);
    }

    @Test
    public void edullisillaKorttiOk() {
        assertTrue(kassa.syoEdullisesti(kortti));
        assertEquals(760, kortti.saldo());
    }

    @Test
    public void maukkaillaKorttiOk() {
        assertTrue(kassa.syoMaukkaasti(kortti));
        assertEquals(600, kortti.saldo());
    }

    @Test
    public void myytyjenEdullistenMaaraKasvaaJosKortillaRahaa() {
        kassa.syoEdullisesti(kortti);
        kassa.syoEdullisesti(kortti);
        kassa.syoEdullisesti(kortti);
        assertTrue(kassa.edullisiaLounaitaMyyty() == 3);
    }

    @Test
    public void myytyjenMaukkaidenMaaraKasvaaJosKortillaRahaa() {
        kassa.syoMaukkaasti(kortti);
        kassa.syoMaukkaasti(kortti);
        assertTrue(kassa.maukkaitaLounaitaMyyty() == 2);
    }

    @Test
    public void edullisillaKorttiaEiVeloitetaJosEiRiittavastiRahaa() {
        assertFalse(kassa.syoEdullisesti(rahatLopussa));
        assertEquals(100, rahatLopussa.saldo());
    }

    @Test
    public void maukkaillaKorttiaEiVeloitetaJosEiRiittavastiRahaa() {
        assertFalse(kassa.syoMaukkaasti(rahatLopussa));
        assertEquals(100, rahatLopussa.saldo());
    }

    @Test
    public void myytyjenEdullistenMaaraEiKasvaJosKortillaEiRahaa() {
        kassa.syoEdullisesti(rahatLopussa);
        assertTrue(kassa.edullisiaLounaitaMyyty() == 0);
    }

    @Test
    public void myytyjenMaukkaidenMaaraEiKasvaJosKortillaEiRahaa() {
        kassa.syoMaukkaasti(rahatLopussa);
        assertTrue(kassa.maukkaitaLounaitaMyyty() == 0);
    }

    @Test
    public void edullinenKorttiostosEiMuutaKassaa() {
        kassa.syoEdullisesti(kortti);
        assertEquals(100000, kassa.kassassaRahaa());
    }

    @Test
    public void maukasKorttiostosEiMuutaKassaa() {
        kassa.syoMaukkaasti(kortti);
        assertEquals(100000, kassa.kassassaRahaa());
    }

    @Test
    public void korttiOkRahaaLadatessa() {
        kassa.lataaRahaaKortille(rahatLopussa, 1237);
        assertEquals(1337, rahatLopussa.saldo());
    }

    @Test
    public void korttilleEiVoiLadataNegatiivistaSummaa() {
        kassa.lataaRahaaKortille(rahatLopussa, -1337);
        assertEquals(100, rahatLopussa.saldo());
    }

    @Test
    public void kassaKasvaaKortilleLadatunSumman() {
        kassa.lataaRahaaKortille(rahatLopussa, 1337);
        assertEquals(101337, kassa.kassassaRahaa());
    }
    
}
