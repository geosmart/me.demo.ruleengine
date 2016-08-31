package me.demo.ruleengine.mvel;

/**
 * 规则引擎
 * Created by Think on 2016/8/30.
 */
public interface RuleEngine {
    /**
     * 对指定上下文执行指定类型的规则
     */
    void execute(RuleContext ruleContext, String ruleSetName);

    /**
     * 注册规则
     */
    void registerRule(RuleSet ruleSet);

    /**
     * 取消注册规则
     */
    void unregisterRule(RuleSet ruleSet);

    /**
     * 清空规则列表
     */
    void clearRules();


    /**
     * 注册一个规则执行器
     */
    void registorRuleExecutor(RuleExecutor ruleExecutor);

    /**
     * 取消注册规则执行器
     */
    void unregistorRuleExecutors(RuleExecutor ruleExecutors);

}
