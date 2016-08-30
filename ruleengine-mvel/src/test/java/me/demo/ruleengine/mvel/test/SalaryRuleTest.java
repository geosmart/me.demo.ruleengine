package me.demo.ruleengine.mvel.test;

import com.alibaba.fastjson.JSON;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import me.demo.ruleengine.mvel.executor.MvelRuleExecutor;
import me.demo.ruleengine.mvel.executor.RuleExecutor;
import me.demo.ruleengine.mvel.rule.Context;
import me.demo.ruleengine.mvel.rule.MvelContext;
import me.demo.ruleengine.mvel.rule.MvelRule;
import me.demo.ruleengine.mvel.rule.RuleSet;
import me.demo.ruleengine.mvel.ruleengine.RuleEngine;
import me.demo.ruleengine.mvel.ruleengine.RuleEngineDefault;

import static org.junit.Assert.assertEquals;

/**
 * Created by Think on 2016/8/30.
 */
public class SalaryRuleTest {

    RuleEngine ruleEngine = new RuleEngineDefault();

    public static <T> T XML2Bean(InputStream input, Class<T> clazz) {
        XStream xstream = new XStream(new DomDriver());
        xstream.processAnnotations(clazz);
        return (T) xstream.fromXML(input);
    }

    /**
     * 获得指定文件的byte数组
     */
    public static byte[] getBytes(String filePath) {
        byte[] buffer = null;
        try {
            java.io.File file = new java.io.File(filePath);
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;
    }

    @Test
    public void tets_formatRuleJsonData() {
        RuleSet ruleSet = new RuleSet();
        MvelRule rule = new MvelRule();
        rule.setId("step1");
        rule.setCondition("salary<=3500");
        rule.setAction("fee=0");
        ruleSet.getRules().add(rule);
        String jsonStr = JSON.toJSONString(ruleSet);
        System.out.println(jsonStr);
    }

    @Before
    public void setup() throws FileNotFoundException {
        System.out.println("setup...");

        System.out.println("set RuleExecutor");
        RuleExecutor ruleExecutor = new MvelRuleExecutor();
        ruleEngine.addRuleExecutor(ruleExecutor);

        System.out.println("set Rules");
        String rulePath = System.getProperty("user.dir") + "\\src\\test\\resources\\salaryRule.json";
        //TODO rules is null
        RuleSet ruleSet = JSON.parseObject(getBytes(rulePath), RuleSet.class);
        ruleEngine.addRules(ruleSet);
    }

    @Test
    public void test_salaryRule() {
        Context context = new MvelContext();
        context.put("fee", 0.0);
        context.put("salary", 1000);
        ruleEngine.execute(context, "feerule");

        assertEquals(0, context.get("fee"));

        context.put("salary", 4000);
        ruleEngine.execute(context, "feerule");

        assertEquals(15.0, context.get("fee"));

        context.put("salary", 7000);
        ruleEngine.execute(context, "feerule");

        assertEquals(245.0, context.get("fee"));

        context.put("salary", 21000);
        ruleEngine.execute(context, "feerule");

        assertEquals(3370.0, context.get("fee"));

        context.put("salary", 40005);
        ruleEngine.execute(context, "feerule");

        assertEquals(8196.50, context.get("fee"));

        context.put("salary", 70005);
        ruleEngine.execute(context, "feerule");

        assertEquals(17771.75, context.get("fee"));

        context.put("salary", 100000);
        ruleEngine.execute(context, "feerule");

        assertEquals(29920.00, context.get("fee"));
    }

    @After
    public void teardown() {
        System.out.println("tear down...");
    }

}
