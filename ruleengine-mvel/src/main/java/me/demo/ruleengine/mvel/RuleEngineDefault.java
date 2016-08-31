package me.demo.ruleengine.mvel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 默认RuleEngine
 * Created by Think on 2016/8/30.
 */
public class RuleEngineDefault implements RuleEngine {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    private Map<String, TreeSet<Rule>> ruleSetMap = new ConcurrentHashMap<>();

    private List<RuleExecutor> ruleExecutors = null;
    private Map<String, RuleExecutor> ruleExecutorMap = new ConcurrentHashMap<>();

    @Override
    public void execute(RuleContext ruleContext, String ruleSetName) {
        Set<Rule> ruleSet = ruleSetMap.get(ruleSetName);
        if (ruleSet != null) {
            Vector<Rule> newSet = new Vector<>(ruleSet);
            processRuleSet(ruleContext, newSet);
        }
    }

    private void processRuleSet(RuleContext ruleContext, Vector<Rule> newSet) {
        //如果没有后续规则，则退出
        if (newSet.size() == 0) {
            return;
        }
        Rule rule = newSet.get(0);
        RuleExecutor ruleExecutor = ruleExecutorMap.get(rule.getType());
        if (ruleExecutor != null) {
            boolean executed = ruleExecutor.execute(ruleContext, rule);
            if (executed) {
                //是否为排他规则
                if (rule.isExclusive()) {
                    //如果条件成立，则是独占条件，则直接返回
                    return;
                } else if (!rule.isRepeat()) {
                    //如果不是可重复执行的规则，则删除之
                    newSet.remove(0);
                }
            } else {
                //如果不匹配，则删除之
                newSet.remove(0);
            }
        } else {
            throw new RuntimeException("找不到对应" + rule.getType() + "的执行器");
        }
        processRuleSet(ruleContext, newSet);
    }

    @Override
    public void registerRule(RuleSet ruleSet) {
        TreeSet<Rule> rules = ruleSetMap.get(ruleSet.getName());
        if (rules == null) {
            rules = new TreeSet<>();
            ruleSetMap.put(ruleSet.getName(), rules);
        }
        //检查规则
        for (Rule rule : ruleSet.getRules()) {
            if (rule.isValidate()) {
                rules.add(rule);
            } else {
                logger.error("规则[{}]检查无效.", rule.getId());
            }
            rule.isValidate();
        }
        System.out.println(rules);
    }


    @Override
    public void unregisterRule(RuleSet ruleSet) {
        Set<Rule> rules = ruleSetMap.get(ruleSet.getName());
        if (rules != null) {
            rules.removeAll(ruleSet.getRules());
        }
    }

    @Override
    public void clearRules() {
        ruleSetMap.clear();
    }


    @Override
    public void registorRuleExecutor(RuleExecutor ruleExecutor) {
        if (ruleExecutors == null) {
            ruleExecutors = new ArrayList<RuleExecutor>();
        }
        ruleExecutors.add(ruleExecutor);
        ruleExecutorMap.put(ruleExecutor.getType(), ruleExecutor);
    }


    @Override
    public void unregistorRuleExecutors(RuleExecutor ruleExecutor) {
        if (ruleExecutors == null) {
            ruleExecutors = new ArrayList<RuleExecutor>();
        }
        ruleExecutors.remove(ruleExecutor);
        ruleExecutorMap.remove(ruleExecutor.getType());
    }
}
