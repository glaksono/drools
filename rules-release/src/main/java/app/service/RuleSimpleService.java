package service;


import model.Customer;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.StatelessKieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RuleSimpleService {

    @Autowired
    private KieContainer container;

    public void advise(Customer c){
        StatelessKieSession session = container.newStatelessKieSession("springRuleSession");
        session.execute(c);
    }
}
