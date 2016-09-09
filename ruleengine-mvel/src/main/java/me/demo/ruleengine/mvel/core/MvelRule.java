package me.demo.ruleengine.mvel.core;

import org.mvel2.MVEL;

import java.io.Serializable;

/**
 * Basic rule implementation class that provides common methods.
 *
 * You can extend this class and override {@link MvelRule#evaluate(RuleContext)} and {@link
 * MvelRule#execute(RuleContext)} to provide rule conditions and actions logic.
 *
 * Created by Think on  2016/8/30.
 */
public class MvelRule extends BasicRule {
    /**
     * 判断条件是否匹配
     */
    public boolean evaluate(RuleContext ruleContext) {
        try {
            Boolean isEvaluate = (Boolean) MVEL.eval(getCondition(), ruleContext);
            return isEvaluate;
        } catch (Exception e) {
            throw new RuntimeException(String.format("条件[%s]匹配发生异常:", getCondition()), e);
        }
    }

    /**
     * 执行条件匹配后的操作
     */
    public void execute(RuleContext ruleContext) {
        try {
            Serializable exp = MVEL.compileExpression(getAction(), ruleContext);
            MVEL.executeExpression(exp, ruleContext);
        } catch (Exception e) {
            throw new RuntimeException(String.format("后续操作[%s]执行发生异常:", getAction()), e);
        }
    }
}

