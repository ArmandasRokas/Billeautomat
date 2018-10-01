import static org.junit.jupiter.api.Assertions.*;

class BilletautomatTestAflevering {

      Billetautomat billetautomat = new Billetautomat();

    /**
     * Beskrivelse: Der testes, om der kan fås retur penge fra automaten.
     *              Det kræver, at balancen ikke er lige med nul
     *              for at få retur penge.
     * Forventet: Retur penge er lige med nuværende balance.
     *            Efter operationen er balancen er lige med nul
     */

    @Test
    void testReturPenge(){

        //Arrange
        if(billetautomat.getBalance()<= 0){
            billetautomat.indsætPenge(20);
        }
        int balanceFoerReturPenge = billetautomat.getBalance();

        //Act
        int returPenge = billetautomat.returpenge();

        //Assert
        assertEquals(balanceFoerReturPenge, returPenge); // Retur penge skal være lige med balancen, som var før operationen.
        assertEquals(0, billetautomat.getBalance()); // balance skal være lige med nul efter beløb er udbetalt

    }
}
