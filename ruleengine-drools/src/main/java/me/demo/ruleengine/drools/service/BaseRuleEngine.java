package me.demo.ruleengine.drools.service;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderConfiguration;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;

/**
 * Created by Think on 2016/8/24.
 */
public abstract class BaseRuleEngine {
    private static String droolsPath = System.getProperty("user.dir") + "\\bin\\drools";

    // KnowledgeBase是用来收集应用当中知识定义的知识库对象，在一个KnowledgeBase当中，可以包含普通的规则(rule)，规则流(rule flow)，函数定义(function)，用户自定义对象(type model)，
    private static KnowledgeBase knowledgeBase;

    private KnowledgeBuilderConfiguration config = KnowledgeBuilderFactory.newKnowledgeBuilderConfiguration();

    public KnowledgeBase getKnowledgeBase() {
        if (knowledgeBase == null) {
            init();
        }
        return knowledgeBase;
    }

    public void init() {
        if (knowledgeBase == null) {
            config.setProperty("drools.dump.dir", droolsPath);
            config.setProperty("drolls.dateformat", "yyyy-MM-dd HH:mm:ss");
            knowledgeBase = KnowledgeBaseFactory.newKnowledgeBase();
        }
    }

    public void addResourceByDrl(String drlFileName) {
        if (knowledgeBase == null) {
            init();
        }
        // KnowledgeBuilder用来在业务代码中收集已经编写好的规则，然后对这些规则文件进行编译，最终产生一批编译好的规则包(KnowledgePackage)给其他的应用程序使用
        KnowledgeBuilder knowledgeBuilder = KnowledgeBuilderFactory.newKnowledgeBuilder(config);
        // 解析并且编译规则文件
        String fileName = droolsPath + "\\" + drlFileName;
        System.out.println("load rule resource：" + fileName);
        knowledgeBuilder.add(ResourceFactory.newFileResource(fileName), ResourceType.DRL);
        if (knowledgeBuilder.hasErrors()) {
            throw new RuntimeException("rule has error! pls check it!" + knowledgeBuilder.getErrors());
        }
        // // 得到编译后的包并部署到知识库
        knowledgeBase.addKnowledgePackages(knowledgeBuilder.getKnowledgePackages());
    }
}
