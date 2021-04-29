package edu.upc.dsa;

import edu.upc.dsa.models.Tracing;
import edu.upc.dsa.models.Trademark;
import edu.upc.dsa.models.User;
import edu.upc.dsa.models.Vaccine;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class Covid19ManagerImplTest {
    Covid19Manager scenario;

    @Before
    public void setUp() {
        scenario = Covid19ManagerImpl.getInstance();
        scenario.addUser(new User("Toni", "0"));
        scenario.addUser(new User("Juan", "0"));
        scenario.addUser(new User("Sergi", "0"));

        scenario.addTrademark(new Trademark("Phizer", "0"));
        scenario.addTrademark(new Trademark("Moderna", "1"));
        scenario.addTrademark(new Trademark("Janssen", "2"));
        scenario.addTrademark(new Trademark("Sputnik", "3"));

        scenario.addTracing(new Tracing("29/04/2021", "0", "Mal de cap"));
        scenario.addTracing(new Tracing("29/04/2021", "1", "Es troba bé"));
    }

    @After
    public void tearDown() {
        scenario.clear();
    }

    @Test
    public void testApplyVaccine() {
        Assert.assertEquals(0, scenario.getVaccinesByTrademark().size());

        scenario.applyVaccine(new Vaccine("Phizer", "20/04/2021", "0"));

        Assert.assertEquals(1, scenario.getVaccinesByTrademark().size());

        scenario.applyVaccine(new Vaccine("Moderna", "20/03/2021", "1"));

        Assert.assertEquals(2, scenario.getVaccinesByTrademark().size());

        scenario.applyVaccine(new Vaccine("Janssen", "20/02/2021", "2"));

        Assert.assertEquals(3, scenario.getVaccinesByTrademark().size());
    }

    @Test
    public void testAddTracing() {
        Assert.assertEquals(1, scenario.getTracingsForUser("0").size());
        Assert.assertEquals(1, scenario.getTracingsForUser("1").size());
        Assert.assertEquals(0, scenario.getTracingsForUser("2").size());

        scenario.addTracing(new Tracing("29/04/2021", "2", "Febre"));

        Assert.assertEquals(1, scenario.getTracingsForUser("0").size());
        Assert.assertEquals(1, scenario.getTracingsForUser("1").size());
        Assert.assertEquals(1, scenario.getTracingsForUser("2").size());

        scenario.addTracing(new Tracing("29/04/2021", "0", "Es troba millor"));

        Assert.assertEquals(2, scenario.getTracingsForUser("0").size());
        Assert.assertEquals(1, scenario.getTracingsForUser("1").size());
        Assert.assertEquals(1, scenario.getTracingsForUser("2").size());
    }
}
