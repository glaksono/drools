package serviceTest;

import model.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieRepository;
import org.kie.api.builder.model.KieModuleModel;
import org.kie.api.builder.model.KieSessionModel;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.StatelessKieSession;
import org.kie.internal.io.ResourceFactory;

public class DecisionTableTest {

    private static KieContainer kc;

    @BeforeAll
    public static void setup(){
        KieServices ks = KieServices.get();
        KieRepository repository = ks.getRepository();
        KieModuleModel kieModule = ks.newKieModuleModel();
        kieModule.newKieBaseModel("decision-table-base").setDefault(true)
        .newKieSessionModel("decision-table-session")
                .setType(KieSessionModel.KieSessionType.STATELESS);

        KieFileSystem fs = ks.newKieFileSystem();
        fs = fs.write(ResourceFactory.newClassPathResource("rules/customer-membership.xls"));
        fs.writeKModuleXML(kieModule.toXML());
        ks.newKieBuilder(fs).buildAll();
        kc = ks.newKieContainer(repository.getDefaultReleaseId());
    }

    @Test
    public void test_gold_membership(){
        StatelessKieSession session = kc.newStatelessKieSession("decision-table-session");
        Customer c = new Customer();
        c.setAge(30);
        session.execute(c);
        Assertions.assertTrue(c.getMembership().equals("GOLD"));
    }

    @Test
    public void test_silver_membership(){
        StatelessKieSession session = kc.newStatelessKieSession("decision-table-session");
        Customer c = new Customer();
        c.setAge(15);
        session.execute(c);
        Assertions.assertTrue(c.getMembership().equals("SILVER"));

    }
}
