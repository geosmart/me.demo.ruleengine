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

import me.demo.ruleengine.mvel.core.RuleContext;
import me.demo.ruleengine.mvel.core.RuleEngine;
import me.demo.ruleengine.mvel.core.DefaultRuleEngine;
import me.demo.ruleengine.mvel.core.RuleEngineParameter;
import me.demo.ruleengine.mvel.core.MvelRuleSet;
import me.demo.ruleengine.mvel.util.Utils;

import static org.junit.Assert.assertEquals;

/**
 * 个人所得税规则测试
 * Created by Think on 2016/8/30.
 */
public class SalaryRuleTest {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    RuleEngine ruleEngine;
    String ruleSetName = "salaryRule";

    @Before
    public void setup() throws IOException {
        logger.info("setup...");
        RuleEngineParameter parameter = new RuleEngineParameter("salaryEngine", true, false, true, Utils.DEFAULT_RULE_PRIORITY_THRESHOLD, false);
        ruleEngine = new DefaultRuleEngine(parameter);

        String rulePath = System.getProperty("user.dir") + "\\src\\test\\resources\\salaryRule.json";
        String jsonStr = FileUtils.readFileToString(new File(rulePath));
        MvelRuleSet ruleSet = JSON.parseObject(jsonStr, MvelRuleSet.class);
        ruleEngine.registerRule(ruleSet);
    }

    @Test
    public void test_salaryRule() {
        RuleContext ruleContext = new RuleContext();
        ruleContext.put("fee", 0.0);

        ruleContext.put("salary", 1000);
        ruleEngine.fireRules(ruleContext);
        assertEquals(0, ruleContext.get("fee"));

        ruleContext.put("salary", 4000);
        ruleEngine.fireRules(ruleContext);
        assertEquals(15.0, ruleContext.get("fee"));

        ruleContext.put("salary", 7000);
        ruleEngine.fireRules(ruleContext);
        assertEquals(245.0, ruleContext.get("fee"));

        ruleContext.put("salary", 10000);
        ruleEngine.fireRules(ruleContext);
        assertEquals(745.0, ruleContext.get("fee"));

        ruleContext.put("salary", 18000);
        ruleEngine.fireRules(ruleContext);
        assertEquals(2620.0, ruleContext.get("fee"));

        ruleContext.put("salary", 40005);
        ruleEngine.fireRules(ruleContext);
        assertEquals(8196.50, ruleContext.get("fee"));

        ruleContext.put("salary", 70005);
        ruleEngine.fireRules(ruleContext);
        assertEquals(17771.75, ruleContext.get("fee"));

        ruleContext.put("salary", 100000);
        ruleEngine.fireRules(ruleContext);
        assertEquals(29920.00, ruleContext.get("fee"));
    }

    @After
    public void teardown() {
        logger.info("teardown...");
    }

}
