package config;

import org.drools.compiler.kproject.ReleaseIdImpl;
import org.kie.api.KieServices;
import org.kie.api.builder.ReleaseId;
import org.kie.api.runtime.KieContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DroolsConfiguration {

    @Bean
    KieContainer kieContainer(){
        KieServices ks = KieServices.get();
        ReleaseId releaseId = new ReleaseIdImpl("gl-codework-drools","rules-central","LATEST");
        KieContainer container = ks.newKieContainer(releaseId);
        return container;
    }
}
