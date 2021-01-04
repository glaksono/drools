package app.config;

import org.drools.compiler.kproject.ReleaseIdImpl;
import org.kie.api.KieServices;
import org.kie.api.builder.KieScanner;
import org.kie.api.builder.ReleaseId;
import org.kie.api.runtime.KieContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DroolsConfiguration {

    @Bean
    KieServices kieServices(){
        System.setProperty("kie.maven.settings.custom","/Users/gregory.laksono01/.m2/settings.xml");
        KieServices ks = KieServices.get();
        return ks;
    }

    @Bean
    KieContainer kieContainer(KieServices ks){
        ReleaseId releaseId = new ReleaseIdImpl("gl-codework-drools","rules-central","1.0-SNAPSHOT");
        KieContainer container = ks.newKieContainer("release",releaseId);
        KieScanner scanner = ks.newKieScanner(container);
        scanner.start(500L);
        return container;
    }

}
