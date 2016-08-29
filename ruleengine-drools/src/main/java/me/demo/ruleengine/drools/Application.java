package me.demo.ruleengine.drools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.core.env.SimpleCommandLinePropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.IOException;
import java.util.Arrays;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import me.demo.ruleengine.drools.config.Constants;

/**
 * Created by geomart on 2016/7/25.
 */
@SpringBootApplication
@EnableAutoConfiguration
@EnableScheduling
public class Application implements ApplicationRunner {
    @Autowired
    private ApplicationContext context;

    private final Logger log = LoggerFactory.getLogger(Application.class);

    @Inject
    private Environment env;

    /**
     * Initializes application.
     * <p/>
     * Spring profiles can be configured with a program arguments --spring.profiles.active=your-active-profile
     * <p/>S
     */
    @PostConstruct
    public void initApplication() throws IOException {
        if (env.getActiveProfiles().length == 0) {
            log.warn("No Spring profile configured, running with default configuration");
        } else {
            log.info("Running with Spring profile(s) : {}", Arrays.toString(env.getActiveProfiles()));
        }
    }

    /**
     * Main method, used to run the application.
     */
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application.class);
        app.setShowBanner(false);

        SimpleCommandLinePropertySource source = new SimpleCommandLinePropertySource(args);
//        ApplicationContext app =
        // Check if the selected profile has been set as argument.
        // if not the development profile will be added
        addDefaultProfile(app, source);

        app.run(args);
    }

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        if (applicationArguments.getSourceArgs().length > 0) {
            //根据args执行指定操作
            customServiceHandler(applicationArguments.getSourceArgs());
            //退出程序
            SpringApplication.exit(context, (ExitCodeGenerator) () -> 2);
        }
    }

    private void customServiceHandler(String args[]) {
        log.debug("args.length：{}", args.length);

    }

    /**
     * Set a default profile if it has not been set
     */
    private static void addDefaultProfile(SpringApplication app, SimpleCommandLinePropertySource source) {
        if (!source.containsProperty("spring.profiles.active")) {
            app.setAdditionalProfiles(Constants.SPRING_PROFILE_DEVELOPMENT);
        }
    }
}
