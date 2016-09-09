package me.demo.ruleengine.mvel.core;

import java.util.Map;
import java.util.Set;

/**
 * Rules engine interface.
 * Created by Think on 2016/8/30.
 */
public interface RuleEngine {

    /**
     * 规则引擎 设置参数
     *
     * @return The rules engine parameters
     */
    RuleEngineParameter getParameters();

    /**
     * 注册rule
     */
    void registerRule(Rule rule);

    /**
     * 注册ruleSet
     */
    void registerRule(MvelRuleSet ruleSet);

    /**
     * 取消注册rule
     */
    void unregisterRule(Rule rule);

    /**
     * 清空规则列表
     */
    void clearRules();

    /**
     * Return the set of registered rules.
     *
     * @return the set of registered rules
     */
    Set<Rule> getRules();

    /**
     * Check rules without firing them.
     *
     * @return a map with the result of evaluation of each rule
     */
    Map<Rule, Boolean> checkRules();

    /**
     * Fire all registered rules.
     */
    void fireRules(RuleContext ruleContext);

}
