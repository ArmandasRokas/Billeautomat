import static org.junit.jupiter.api.Assertions.*;

class BilletautomatTestAflevering {
      public String password = "1234";
      Billetautomat billetautomat = new Billetautomat();

    /** Test 1
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
      
     /** Test 2
     * Beskrivelse: Der testes, om der kan ændres billet prisen i montør tilstand.
     * Forventet: Billetprisen er ændret til en ny pris, som er større 20kr end tidligere pris i montør tilstand
     */
    @Test
    void testSetBilletPrisIMontørTilsand(){

        //Arrange
        if (!billetautomat.erMontør()){
            billetautomat.montørLogin("1234");
        }
        int nuvaerendeBilletPris = billetautomat.getBilletpris();
        int nyBilletPris = nuvaerendeBilletPris + 20;

        //Act
        billetautomat.setBilletpris(nyBilletPris); // sætter en ny billet pris

        //Assert
        assertEquals(nyBilletPris, billetautomat.getBilletpris()); // Den nye billet pris skal være lige med billetautomatens billet pris.

    }


    /** Test 3 (Forfatter: Emil)
     * Beskrivelse: Tester om billetprisen stadig er 10, hvilket er den informerede start-billetpris.
     * Forventet: Hvis billetprisen ikke er ændret af fx en montør, og prisen er 10 kroner, som den er oplyst til,
     *            vil testen køre uden fejlkode.
     */
    @Test
    void getBilletpris() {
        //Arrange
        int forventetBilletpris = 10  
        billetautomat.setBilletpris(forventetBilletpris);

        //Act
        int billetpris = billetautomat.getBilletpris();

        //Assert
        assertEquals(forventetBilletpris, billetpris);

    }
      
        /** Test 4 (Forfatter: Emil)
     * Beskrivelse: Der testes antallet af billetter , som er blevet udskrevet passer med antallet af billetter solgt.
     * Forventet: Her bliver der simuleret et salg af 2 billetter, så hvis metoden skal testes til at fungere,
     *            skal antallet af billetter solgt være 2.
     * OBS: Der testes kun for montørtilstand, da der ikke er nogen funktion til at logge ud af montørtilstand
     *      i selve programmet, der testes.
     */
    @Test
    void getAntalBilletterSolgtErMonteor() {
        //Arrange
        if (!billetautomat.erMontør()) {
            billetautomat.montørLogin("1234");
        }
        billetautomat.setAntalBilletterSolgt(0);

        //Act
        billetautomat.udskrivBillet();
        billetautomat.udskrivBillet();

        //Assert
        assertEquals(2, billetautomat.getAntalBilletterSolgt());
    }
    
    /** Test 5
     * @Author Tobias
     *
     * Beskrivelse: Tester for indsættelse af 0 kroner.
     * Forventet: Automaten har 0 kroner.
     */
    @Test
    void indsaetNulKroner_iAutomat() {
        /* Arrange */
        int forventetPenge = 0;
        int automatsPenge = 0;

        /* Act */
        automat.indsætPenge(0);
        automatsPenge = automat.getBalance();

        /* Assert */
        assertEquals(forventetPenge,automatsPenge);
    }

    /** Test 6
    * @Author Tobias
     * Beskrivelse: Indsaetter Integer.MAX kroner og tjekker balance.
     * Forventet: Balancen er lig med Integer.MAX kroner.
     */
    @Test
    void insaetMaximumKroner_iAutomat(){
        /* Arrange */
        int indsætDetteBeløb = Integer.MAX_VALUE;
        int forventetPenge = Integer.MAX_VALUE;
        int automatsPenge;

        /* Act */
        automat.indsætPenge(indsætDetteBeløb);
        automatsPenge = automat.getBalance();

        /* Assert */
        assertEquals(forventetPenge,automatsPenge);
    }


    /** Test 7
    * @Author Tobias
     * Beskrivelse: Indsaetter Integer.MIN kroner og tjekker balance.
     * Forventet: Balancen er lig med Integer.MIN kroner.
     */
    @Test
    void indsætMinnimumKroner_iAutomat(){
        /* Arrange */
        int indsætDetteBeløb = Integer.MIN_VALUE;
        int forventetPenge = Integer.MIN_VALUE;
        int automatsPenge;

        /* Act */
        automat.indsætPenge(indsætDetteBeløb);
        automatsPenge = automat.getBalance();

        /* Assert */
        assertEquals(forventetPenge,automatsPenge);
    }

    /** Test 8
    * @Author Tobias
     * Beskrivelse: Tester montør login med korrekt password.
     * Forventet: Montøren kan logge ind.
     */
    @Test
    void montørLoggerInd_KorrektPassword() {
        /* Arrange */
        // Password er sat som et field for nem ændring af adskillige tests.

        /* Act */
        automat.montørLogin(password);

        /* Assert */
        assertTrue(automat.erMontør());
    }


    /** Test 9
    * @Author Tobias
     * Beskrivelse: Tester montørlogin med forkert password.
     * Forventet: Montøren kan ikke logge ind.
     */
    @Test
    void montørLoggerInd_ForkertPassword() {
        /* Arrange */

        /* Act */
        automat.montørLogin("abcd");

        /* Assert */
        assertFalse(automat.erMontør());
    }


    /** Test 10
    * @Author Tobias
     * Beskrivelse: Tester den indloggede montør kan nulstille automaten efter der er købt billetter.
     * Forventet: Der er solgt 0 billetter.
     */
    @Test
    void nulstilAutomat_ErMontør_SolgteBilletter() {
        /* Arrange */
        int indsætDetteBeløb = automat.getBilletpris()*3;
        int forventetSolgteBilletter = 0;

        /* Act */
        automat.indsætPenge(indsætDetteBeløb);
        automat.udskrivBillet();
        automat.udskrivBillet();
        automat.montørLogin(password);
        automat.nulstil();

        /* Assert */
        assertEquals(forventetSolgteBilletter,automat.getAntalBilletterSolgt());
    }

    /** Test 11
     * @Author Tobias
     * Beskrivelse: Tester den indloggede montør kan nulstille automaten efter der er købt billetter.
     * Forventet: Omsætningen er 0 kr.
     */
    @Test
    void nulstilAutomat_ErMontør_Omsætning() {
        /* Arrange */
        int indsætDetteBeløb = automat.getBilletpris()*3;
        int forventetOmsætning = 0;

        /* Act */
        automat.indsætPenge(indsætDetteBeløb);
        automat.udskrivBillet();
        automat.udskrivBillet();
        automat.montørLogin(password);
        automat.nulstil();

        /* Assert */
        assertEquals(forventetOmsætning,automat.getTotal());
    }


    /** Test 12
     * @Author Tobias
     * Beskrivelse: Tester man ikke kan nulstille automaten som uathoriseret almen person. (Læs: ikke-montør).
     * Forventet: Antal solgte billetter er lig det faktiske antal af solgte billetter.
     */
    @Test
    void nulstilAutomat_ErIkkeMontør_BilletterSolgt() {
        /* Arrange */
        int indsætDetteBeløb = automat.getBilletpris()*3;
        int forventetSolgteBilletter = 0;
        int solgteBilletter;

        /* Act */
        automat.indsætPenge(indsætDetteBeløb);
        automat.udskrivBillet();
        automat.udskrivBillet();
        automat.nulstil();
        solgteBilletter = automat.getAntalBilletterSolgt();

        /* Assert */
        assertEquals(forventetSolgteBilletter,solgteBilletter);
    }

    /** Test 13
     * @Author Tobias
     * Beskrivelse: Tester man ikke kan nulstille automaten som uathoriseret almen person. (Læs: ikke-montør).
     * Forventet: Den totale omsætning er lig den reelle omsætning.
     */
    @Test
    void nulstilAutomat_ErIkkeMontør_Omsætning() {
        /* Arrange */
        int indsætDetteBeløb = automat.getBilletpris()*3;
        int forventetOmsætning = 0;
        int omsætning;

        /* Act */
        automat.indsætPenge(indsætDetteBeløb);
        automat.udskrivBillet();
        automat.udskrivBillet();
        automat.nulstil();
        omsætning = automat.getTotal();

        /* Assert */
        assertEquals(forventetOmsætning,omsætning);
    }
      
     /** Test 14 (Forfatter: Armandas)
     * Beskrivelse: Der testes, at efter man er logget ind, får vi montørtilstand
     * Forventet: montørtilstand er ændret til true efter logget ind. 
     */
    @Test
    void testErMontør(){

        //Arrange
        if (billetautomat.erMontør()){
            billetautomat.montørLogin("0000"); // log out
        }

        //Act

        billetautomat.montørLogin("1234");

        //Assert
        assertTrue(billetautomat.erMontør());
    }
    
    /* Test 15
    @Author Kristian
    
    Tester om det er muligt at se total tjent som montør.
    Forventet: Det er muligt at læse hvor meget der er solgt for.
     */
    @Test
    public void testGetTotalSomMontør() {
        automat.montørLogin("1234"); //Logger ind som montør
        int forventetTotal = automat.getBilletpris() * automat.getAntalBilletterSolgt(); //SKAL OPDATERES NÅR GETTOTAL() BLIVER FIKSET

        int returneretTotal = automat.getTotal();

        assertEquals(forventetTotal, returneretTotal);
    }

    /* Test 16
    @Author Kristian
    
    Beskrivelse: Tester om det er muligt at se total tjent når man ikke er montør.
    Forventet: Det skal ikke være muligt at se hvor meget der er solgt for.
     */
    @Test
    public void testGetTotalIkkeSomMontør() {
        automat.montørLogin(""); //Sikrer man er logget ud som montør.
        int forventetTotal = 0; //SKAL MULIGVIS OPDATERES NÅR GETTOTAL() BLIVER FIKSET

        int returneretTotal = automat.getTotal();

        assertEquals(forventetTotal, returneretTotal);
    }

    /* Test 17
    @Author Kristian
    
    Beskrivelse: Tester om man kan sætte antal billetter solgt som montør.
    Forventet: Antal billetter solgt skal ændres til input.
     */
    @Test
    public void testSetAntalBilletterSolgtSomMontør() {
        automat.montørLogin("1234"); //Logger ind som montør.
        int forventetAntalSolgteBiletter = 4321;

        automat.setAntalBilletterSolgt(forventetAntalSolgteBiletter);
        int returneretAntalSolgteBilletter = automat.getAntalBilletterSolgt();

        assertEquals(forventetAntalSolgteBiletter, returneretAntalSolgteBilletter);
    }

    /* Test 18
    @Author Kristian
    
    Beskrivelse: Tester om man kan sætte antal billetter solgt når man ikke er montør.
    Forventet: Antal billetter solgt skal forblive det samme.
     */
    @Test
    public void testSetAntalBilletterSolgtIkkeSomMontør() {
        automat.montørLogin(""); //Sikrer man er logget ud som montør.
        int forventetAntalSolgteBiletter = automat.getAntalBilletterSolgt();
        int ændringsInput = 9876;

        automat.setAntalBilletterSolgt(ændringsInput);
        automat.montørLogin("1234"); //Logger ind så man kan se antal billetter solgt.
        int returneretAntalSolgteBilletter = automat.getAntalBilletterSolgt();

        assertEquals(forventetAntalSolgteBiletter, returneretAntalSolgteBilletter);
    }
}
