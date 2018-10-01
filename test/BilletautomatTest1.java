import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BilletautomatTest {
    //Nem adgang til password hvis det ændres i kildekoden til billetautomat skal vi kun ændre variablen et sted i vores tests.
    public String password = "1234";

    //Instantier et Billetautomat object
    Billetautomat automat = new Billetautomat();

    //Lille metode som overskueliggoer nogle af vores tests.
    public void koebBilletter(int antal){
        for (int i = 0; i < antal ; i++) {
            automat.udskrivBillet();
        }
    }

    /** 
    * Test Case: 1 : 
    * Beskrivelse: Få billet prisen
    * Result: Billet prisen er større end 0
    */
    @Test
    void testGetBilletpris() {
        assertTrue(automat.getBilletpris()>0);
    }

    //Forsoeg at indsaette 0 kroner. Automaten's getBalance skal returnere 0 kroner.
    @Test
    void indsaetNulKroner() {
        automat.indsætPenge(0);
        assertEquals(0,automat.getBalance());
    }

    //Indsaet Integer.MAX_VALUE kroner.
    @Test
    void insaetMaxIntegerKroner(){
        automat.indsætPenge(Integer.MAX_VALUE);
        assertEquals(Integer.MAX_VALUE,automat.getBalance());
    }

    //
    @Test
    void testGetNegativBalance() {
        automat.indsætPenge(-10);
        assertEquals(0,automat.getBalance());
    }

    //
    @Test
    void testGetMaxBalance() {
        automat.indsætPenge(Integer.MAX_VALUE);
        assertTrue(automat.getBalance()==Integer.MAX_VALUE);
    }

    //
    @Test
    void insaetMinIntegerKroner(){
        automat.indsætPenge(Integer.MIN_VALUE);
        assertEquals(0,automat.getBalance());
    }

    //
    @Test
    void testReturpenge() {
        automat.indsætPenge(automat.getBilletpris()*2);
        automat.udskrivBillet();
        assertEquals(automat.getBilletpris(), automat.returpenge());
    }

    //
    @Test
    void testMontørLoginKorrektPassword() {
        automat.montørLogin(password);
        assertTrue(automat.erMontør());
    }

    //
    @Test
    void testMontørLoginForkertPassword() {
        automat.montørLogin("abcd");
        assertFalse(automat.erMontør());
    }

    //Test om vi er i montør tilstand fra starten af programmet. (Skal være false - det er ikke ønskeligt!).
    @Test
    void testErMontørIkkeForetagetLogind() {
        assertFalse(automat.erMontør());
    }

    //
    @Test
    void testGetTotalErMontoer() {
        automat.montørLogin(password);
        automat.indsætPenge(automat.getBilletpris()*3);
        koebBilletter(2);
        assertEquals(automat.getBilletpris()*automat.getAntalBilletterSolgt(),automat.getTotal());
    }

    //
    @Test
    void testGetTotalIkkeMontoer() {
        automat.indsætPenge(automat.getBilletpris()*3);
        koebBilletter(2);
        assertEquals(0,automat.getTotal());
    }

    //
    @Test
    void testGetAntalBilletterSolgtErMontoer() {
        automat.montørLogin(password);
        automat.indsætPenge(automat.getBilletpris()*3);
        koebBilletter(2);
        assertEquals(2,automat.getAntalBilletterSolgt());
    }

    //
    @Test
    void testGetAntalBilletterSolgtIkkeMontoer() {
        automat.indsætPenge(automat.getBilletpris()*3);
        koebBilletter(2);
        assertEquals("Afvist - log ind først",automat.getAntalBilletterSolgt());
    }

    //
    @Test
    void testSaetBilletprisErMontoer() {
    }

    //
    @Test
    void testSaetBilletprisIkkeMontoer() {
    }

    //
    @Test
    void testNulstilErMontoer() {

        automat.indsætPenge(automat.getBilletpris()*3);
        koebBilletter(2);
        automat.montørLogin(password);
        automat.nulstil();
        assertTrue(automat.getAntalBilletterSolgt()==0);
    }

    //
    @Test
    void testNulstilIkkeMontoer() {
        automat.indsætPenge(automat.getBilletpris()*3);
        koebBilletter(2);
        automat.nulstil();
        assertTrue(automat.getAntalBilletterSolgt()==2);
    }

    //
    @Test
    void testSaetAntalBilletterSolgtErMontoer() {
    }

    //
    @Test
    void testSaetAntalBilletterSolgtIkkeMontoer() {
    }

}
