# me.demo.ruleengine.mvel
参考easyrule自己实现一个轻量级的规则引擎  
* 以类MVEL表达式来编写Rule；
* Rule以数据库（如MongoDB）进行存储；
* Rule支持优先级，支持排他Rule； 


# 规则文件
``` json
{
  "name": "salaryRule",
  "rules": [
    {
      "name": "step1",
      "action": "fee=0",
      "condition": "salary<=3500"
    },
    {
      "name": "step2",
      "action": "fee=(salary-3500)*0.03",
      "condition": "salary>3500 && salary<=5000"
    },
    {
      "name": "step3",
      "action": "fee=(salary-3500)*0.1-105",
      "condition": "salary>5000 && salary<=8000",
      "priority": 3
    },
    {
      "name": "step4",
      "action": "fee=(salary-3500)*0.2-555",
      "condition": "salary>8000 && salary<=12500",
      "priority": 4
    },
    {
      "name": "step5",
      "action": "fee=(salary-3500)*0.25-1005",
      "condition": "salary>12500 && salary<=38500",
      "priority": 5
    },
    {
      "name": "a",
      "action": "fee=(salary-3500)*0.3-2755",
      "condition": "salary>38500 && salary<=58500",
      "priority": 6
    },
    {
      "name": "step7",
      "action": "fee=(salary-3500)*0.35-5505",
      "condition": "salary>58500 && salary<=83500",
      "priority": 7
    },
    {
      "name": "step8",
      "action": "fee=(salary-3500)*0.45-13505",
      "condition": "salary>83500",
      "priority": 8
    }
  ]
}
```
# 调用示例
```java
public class SalaryRuleTest {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    RuleEngine ruleEngine;
    String ruleSetName = "salaryRule";

    @Before
    public void setup() throws IOException {
        RuleEngineParameter parameter = new RuleEngineParameter("salaryEngine", true, false, true, Utils.DEFAULT_RULE_PRIORITY_THRESHOLD, false);
        ruleEngine = new DefaultRuleEngine(parameter);

        logger.info("setup...");
        String rulePath = System.getProperty("user.dir") + "\\src\\test\\resources\\salaryRule.json";
        String jsonStr = FileUtils.readFileToString(new File(rulePath));
        RuleSet ruleSet = JSON.parseObject(jsonStr, RuleSet.class);
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
    }

    @After
    public void teardown() {
        logger.info("teardown...");
    }

}
```
