import model.Customer;
import model.Order;
import org.drools.compiler.kproject.models.KieSessionModelImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import org.kie.api.KieServices;
import org.kie.api.builder.*;
import org.kie.api.builder.model.KieBaseModel;
import org.kie.api.builder.model.KieModuleModel;
import org.kie.api.builder.model.KieSessionModel;
import org.kie.api.cdi.KSession;
import org.kie.api.conf.EqualityBehaviorOption;
import org.kie.api.conf.EventProcessingOption;
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
        KieFileSystem fileSystem = ks.newKieFileSystem();
        KieRepository repository = ks.getRepository();
        KieModuleModel module = ks.newKieModuleModel();
        module.newKieBaseModel("my-new-module").setDefault(true)
//                .addPackage("rules")
                .newKieSessionModel("TestBaseSession")
                .setType(KieSessionModel.KieSessionType.STATELESS);
        String kmodule = module.toXML();
        fileSystem.writeKModuleXML(kmodule);

        fileSystem.write(ResourceFactory.newClassPathResource("this/test.drl"));
        ks.newKieBuilder(fileSystem).buildAll();
        kc = ks.newKieContainer(repository.getDefaultReleaseId());
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
