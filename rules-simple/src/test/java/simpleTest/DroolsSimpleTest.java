package simpleTest;


import model.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.StatelessKieSession;

public class DroolsSimpleTest {

    private static KieContainer kc;

    @BeforeAll
    public static void setup(){
        KieServices ks = KieServices.get();
        kc = ks.getKieClasspathContainer();
    }

    @Test
    public void test01() throws InterruptedException {
            KieServices ks = KieServices.get();
            kc = ks.getKieClasspathContainer();
            StatelessKieSession session = kc.newStatelessKieSession("simpleTestSession");
            Customer c = new Customer();
            c.setAge(21);
            session.execute(c);
            Assertions.assertTrue(c.getMembership().equals("GOLD"));
    }
}
