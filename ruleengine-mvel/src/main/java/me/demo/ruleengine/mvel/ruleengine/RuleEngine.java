package me.demo.ruleengine.mvel.ruleengine;

import java.util.List;

import me.demo.ruleengine.mvel.executor.RuleExecutor;
import me.demo.ruleengine.mvel.rule.Context;
import me.demo.ruleengine.mvel.rule.RuleSet;

/**
 * Created by Think on 2016/8/30.
 */
public interface RuleEngine {
    /**
     * 对指定上下文执行指定类型的规则
     */
    void execute(Context context, String ruleSetName);

    /**
     * 添加一组规则
     */
    void addRules(RuleSet ruleSet);

    /**
     * 删除一组规则
     */
    void removeRules(RuleSet ruleSet);

    /**
     * 设置一批规则执行器
     */
    void setRuleExecutors(List<RuleExecutor> ruleExecutors);

    /**
     * 添加规则执行器列表
     */
    void addRuleExecutors(List<RuleExecutor> ruleExecutors);

    /**
     * 添加一个规则执行器
     */
    void addRuleExecutor(RuleExecutor ruleExecutor);

    /**
     * 删除规则执行器列表
     */
    void removeRuleExecutors(List<RuleExecutor> ruleExecutors);

    /**
     * 删除一个规则执行器
     */
    void removeRuleExecutor(RuleExecutor ruleExecutor);
}
