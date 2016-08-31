package me.demo.ruleengine.mvel.test;

import com.alibaba.fastjson.JSON;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

import me.demo.ruleengine.mvel.MvelRuleExecutor;
import me.demo.ruleengine.mvel.RuleContext;
import me.demo.ruleengine.mvel.RuleEngine;
import me.demo.ruleengine.mvel.RuleEngineDefault;
import me.demo.ruleengine.mvel.RuleExecutor;
import me.demo.ruleengine.mvel.RuleSet;

import static org.junit.Assert.assertEquals;

/**
 * 个人所得税规则测试
 * Created by Think on 2016/8/30.
 */
public class SalaryRuleTest {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    RuleEngine ruleEngine = new RuleEngineDefault();
    String ruleSetName = "salaryRule";

    @Before
    public void setup() throws IOException {
        logger.info("setup...");

        logger.info("loading RuleExecutor ...");
        RuleExecutor ruleExecutor = new MvelRuleExecutor();
        ruleEngine.registorRuleExecutor(ruleExecutor);
        logger.info("RuleExecutor loaded");

        String rulePath = System.getProperty("user.dir") + "\\src\\test\\resources\\salaryRule.json";
        String jsonStr = FileUtils.readFileToString(new File(rulePath));
        RuleSet ruleSet = JSON.parseObject(jsonStr, RuleSet.class);
        ruleEngine.registerRule(ruleSet);
        logger.info("Rules {} loaded", ruleSetName);
    }

    @Test
    public void test_salaryRule() {
        RuleContext ruleContext = new RuleContext();
        ruleContext.put("fee", 0.0);
        ruleContext.put("salary", 1000);
        ruleEngine.execute(ruleContext, ruleSetName);

        assertEquals(0, ruleContext.get("fee"));

        ruleContext.put("salary", 4000);
        ruleEngine.execute(ruleContext, ruleSetName);

        assertEquals(15.0, ruleContext.get("fee"));

        ruleContext.put("salary", 7000);
        ruleEngine.execute(ruleContext, ruleSetName);

        assertEquals(245.0, ruleContext.get("fee"));

        ruleContext.put("salary", 10000);
        ruleEngine.execute(ruleContext, ruleSetName);
        assertEquals(745.0, ruleContext.get("fee"));

        ruleContext.put("salary", 18000);
        ruleEngine.execute(ruleContext, ruleSetName);

        assertEquals(2620.0, ruleContext.get("fee"));

        ruleContext.put("salary", 40005);
        ruleEngine.execute(ruleContext, ruleSetName);

        assertEquals(8196.50, ruleContext.get("fee"));

        ruleContext.put("salary", 70005);
        ruleEngine.execute(ruleContext, ruleSetName);

        assertEquals(17771.75, ruleContext.get("fee"));

        ruleContext.put("salary", 100000);
        ruleEngine.execute(ruleContext, ruleSetName);

        assertEquals(29920.00, ruleContext.get("fee"));
    }

    @After
    public void teardown() {
        logger.info("teardown...");
    }

}
