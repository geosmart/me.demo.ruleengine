package me.demo.ruleengine.mvel;

import org.mvel2.MVEL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * Mvel规则执行器
 * Created by Think on 2016/8/30.
 */
public class MvelRuleExecutor implements RuleExecutor<Rule> {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public String getType() {
        return "mvel";
    }

    @Override
    public boolean execute(RuleContext ruleContext, Rule rule) {
        try {
            //TODO 可新增哈希索引提升性能
            if (when(rule.getCondition(), ruleContext)) {
                logger.info("rule {} triggered", rule.getId());
                then(rule.getAction(), ruleContext);
                return true;
            } else {
                return false;
            }
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Mvel规则引擎执行器发生异常:", e);
        }
    }

    /**
     * 判断条件是否匹配
     */
    protected boolean when(String condition, RuleContext ruleContext) {
        try {
            Boolean isEvaluate = (Boolean) MVEL.eval(condition, ruleContext);
            return isEvaluate;
        } catch (Exception e) {
            throw new RuntimeException(String.format("条件[%s]匹配发生异常:", condition), e);
        }
    }

    /**
     * 执行条件匹配后的操作
     */
    protected void then(String action, RuleContext ruleContext) {
        try {
            Serializable exp = MVEL.compileExpression(action, ruleContext);
            MVEL.executeExpression(exp, ruleContext);
            logger.info(ruleContext.toString());
        } catch (Exception e) {
            throw new RuntimeException(String.format("后续操作[%s]执行发生异常:", action), e);
        }
    }

}
