import model.Customer;
import model.Order;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import org.kie.api.KieServices;
import org.kie.api.builder.*;
import org.kie.api.cdi.KSession;
import org.kie.api.io.Resource;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.StatelessKieSession;
import org.kie.internal.io.ResourceFactory;

import javax.inject.Inject;

public class RuleTest {


    private static KieContainer kc;


    @BeforeAll
    public static void setup(){
        KieServices ks = KieServices.Factory.get();
        kc = ks.getKieClasspathContainer();
    }

    @Test
    public void test01(){
        StatelessKieSession session = kc.newStatelessKieSession("TestBaseSession");
        Customer c = new Customer();
        c.setAge(21);
        session.execute(c);
        Assertions.assertTrue(c.getMembership().equals("GOLD"));
    }

    @Test
    public void test02(){
        StatelessKieSession session = kc.newStatelessKieSession("TestBaseSession");
        Customer c = new Customer();
        c.setAge(16);
        session.execute(c);
        Assertions.assertTrue(c.getMembership().equals("SILVER"));
    }

    @Test
    public void test03(){
        StatelessKieSession session = kc.newStatelessKieSession("TestBaseSession");
        Order order = new Order();
        Customer c = new Customer();
        order.setTotal(11);
        c.setOrder(order);
        c.setAge(31);
        session.execute(c);
        Assertions.assertNotNull(c.getDiscount());
        Assertions.assertTrue(c.getDiscount().getValue() > 0);
    }
}
