package me.demo.ruleengine.mvel.executor;

import me.demo.ruleengine.mvel.rule.Context;
import me.demo.ruleengine.mvel.rule.Rule;

/**
 * 规则执行器
 * Created by Think on 2016/8/30.
 */
public interface RuleExecutor<T extends Rule> {

    /**
     * 返回执行器类型
     */
    String getType();

    /**
     * 执行规则，并把结果放到上下文上
     *
     * @return 返回条件是否成立
     */
    boolean execute(Context context, T Rule);

}
