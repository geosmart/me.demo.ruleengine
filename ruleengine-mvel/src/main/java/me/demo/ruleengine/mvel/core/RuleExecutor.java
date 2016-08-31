package me.demo.ruleengine.mvel.core;

/**
 * 规则执行器
 * Created by Think on 2016/8/30.
 */
public interface RuleExecutor<Rule> {

    /**
     * 返回执行器类型
     */
    String getType();

    /**
     * 执行规则，并把结果放到上下文上
     *
     * @return 返回条件是否成立
     */
    boolean execute(RuleContext ruleContext, Rule Rule);

}
