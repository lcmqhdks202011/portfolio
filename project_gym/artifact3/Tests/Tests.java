import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class Tests {

    private ControlleurMembre cm;
    private ControlleurProfessionnel cp;
    private ControlleurServices cs;
    private ControlleurInscriptions ci;

    @BeforeEach
    public void setUp() throws Exception{
        cm = new ControlleurMembre();
        cp = new ControlleurProfessionnel();
        cs = new ControlleurServices();
        ci = new ControlleurInscriptions();
    }

    @Test
    public void testAddUpdateRemoveMember(){
        assertEquals(0,cm.membres.size());
        InformationPersonnelle infoTest = new InformationPersonnelle("a","b","c","d","e","f","g");
        InformationPersonnelle info2 =new InformationPersonnelle("x","x","x","x","x","x","x");

        cm.addMembre(new Membre("111111111",infoTest));
        cm.addMembre(new Membre("222222222",infoTest));
        cm.deleteMembre("222222222");
        cm.updateMembre("111111111", info2);

        assertEquals("x",cm.membres.get(0).getInfoPerso().getNom());
        assertEquals(1,cm.membres.size());
    }

    @Test
    public void testAddUpdateRemoveService(){

        Date testDate = new Date();

        Service soccer = new Service("123456789","soccer", testDate,testDate,"s",22,5f);
        Service basket = new Service("123456789","basket",testDate,testDate,"b",30,6f);
        Service tennis = new Service("123456789","tennis",testDate,testDate,"t",2,7f);
        Service golf = new Service("123456789","golf",testDate,testDate,"g",1,8f);

        cs.addService(soccer);
        cs.addService(basket);
        cs.addService(tennis);

        cs.updateService(soccer, "soccer2", testDate, testDate, "s2", 999);
        cs.addService(golf);

        assertEquals(4,cs.services.size());
        assertEquals(999,cs.services.get(0).getCapacity());
        assertEquals("soccer2",cs.services.get(0).getName());
    }

    @Test
    public void testInscription(){

        InformationPersonnelle infoTest = new InformationPersonnelle("a","b","c","d","e","f","g");
        Date testDate = new Date();

        Membre membre1 = new Membre("111111111", infoTest);
        Membre membre2 = new Membre("222222222", infoTest);
        Membre membre3 = new Membre("333333333", infoTest);

        Service service1 = new Service("444444444","service1",testDate,testDate,"...",2, 15f);
        Session cours1 = new Session("1", Day.FRIDAY, service1);
        Session cours2 = new Session("2", Day.SATURDAY, service1);

        service1.sessions.add(cours1);
        service1.sessions.add(cours2);

        ci.addInscription(membre1,cours1);
        ci.addInscription(membre1,cours2);
        ci.addInscription(membre2,cours1);
        ci.addInscription(membre3,cours2);

        assertEquals(2,membre1.inscriptions.size());
        assertEquals(1,membre2.inscriptions.size());
        assertEquals(1,membre3.inscriptions.size());
    }

    @Test
    public void testConfirmations(){

        InformationPersonnelle infoTest = new InformationPersonnelle("a","b","c","d","e","f","g");
        Date testDate = new Date();

        Membre membre1 = new Membre("111111111", infoTest);
        Membre membre2 = new Membre("222222222", infoTest);
        Membre membre3 = new Membre("333333333", infoTest);

        Service service1 = new Service("444444444","service1",testDate,testDate,"...",2, 5f);
        Session cours1 = new Session("1", Day.FRIDAY, service1);
        Session cours2 = new Session("2", Day.SATURDAY, service1);

        service1.sessions.add(cours1);
        service1.sessions.add(cours2);

        ci.addInscription(membre1,cours1);
        ci.addInscription(membre1,cours2);
        ci.addInscription(membre2,cours1);
        ci.addInscription(membre3,cours2);

        ci.confirmerInscription("111111111", service1.getUID());
        ci.confirmerInscription("333333333", service1.getUID());

        assertFalse(membre1.inscriptions.get(1).isConfirmed());
        assertFalse(membre2.inscriptions.get(0).isConfirmed());
        assertFalse(membre3.inscriptions.get(0).isConfirmed());
    }

    @Test
    public void testGenerateurCode(){
        String code1 = Generator.generateNum(3);
        String code2 = Generator.generateNum(0);
        String code3 = Generator.generateNum(10);

        assertEquals(3,code1.length());
        assertEquals(0,code2.length());
        assertEquals(10,code3.length());
    }

    @Test
    public void testValiderMembre(){

        InformationPersonnelle infoTest = new InformationPersonnelle("a","b","c","d","e","f","g");

        Membre membre1 = new Membre("000000001", infoTest);
        Membre membre2 = new Membre("000000002", infoTest);
        Membre membre3 = new Membre("123456789", infoTest);
        Professionnel prof1 = new Professionnel("000000003", infoTest);

        membre2.setSuspendu(true);

        Main.contM.addMembre(membre1);
        Main.contM.addMembre(membre2);
        Main.contM.addMembre(membre3);
        Main.contP.addProfessionnel(prof1);

        Main.contM.deleteMembre("123456789");

        assertTrue(Validateur.validerMembre("000000001"));
        assertFalse(Validateur.validerMembre("000000002"));
        assertFalse(Validateur.validerMembre("123456789"));
        assertTrue(Validateur.validerMembre("000000003"));
    }

}
