package service;

import gl.drools.model.Customer;
import gl.drools.model.Order;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.model.KieModuleModel;
import org.kie.api.builder.model.KieSessionModel;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.StatelessKieSession;
import org.kie.internal.io.ResourceFactory;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class DslTest {

    private static KieContainer kc;

    @BeforeAll
    public static void setup()  {
        KieServices ks = KieServices.get();
        KieModuleModel module = ks.newKieModuleModel();
        module.newKieBaseModel("new-base-model").setDefault(true)
        .newKieSessionModel("dsl-session").
                setType(KieSessionModel.KieSessionType.STATELESS);
        KieFileSystem fs = ks.newKieFileSystem();
        fs.writeKModuleXML(module.toXML());
        fs.write(ResourceFactory.newClassPathResource("rules/dsl-rule.dslr"));
        fs.write(ResourceFactory.newClassPathResource("rules/customer_shopping.dsl"));
        KieBuilder builder = ks.newKieBuilder(fs);
        builder.buildAll();

        kc = ks.newKieContainer(ks.getRepository().getDefaultReleaseId());
    }

    @Test
    public void test_dsl(){
        StatelessKieSession session = kc.newStatelessKieSession("dsl-session");
        Order o1 = new Order();
        Order o2 = new Order();
        o1.setPaid(true);
        o2.setPaid(true);
        List<Order> orders = Arrays.asList(o1,o2);
        Customer c = new Customer();
        c.setEmail("test@email.com");
        c.setOrders(orders);
        session.execute(c);
        Assertions.assertNotNull(c.getDiscount());
        Assertions.assertTrue(c.getDiscount().getValue() > 0);
        Assertions.assertNotNull(c.getDiscount().getValidUntil());
        Assertions.assertTrue(c.getDiscount().getValidUntil().after(new Date()));
    }
}
