package me.demo.ruleengine.mvel.executor;

import org.mvel2.MVEL;

import java.io.Serializable;

import me.demo.ruleengine.mvel.rule.Context;
import me.demo.ruleengine.mvel.rule.MvelContext;
import me.demo.ruleengine.mvel.rule.MvelRule;

/**
 * Created by Think on 2016/8/30.
 */
public class MvelRuleExecutor implements RuleExecutor<MvelRule> {

    @Override
    public String getType() {
        return "mvel";
    }

    @Override
    public boolean execute(Context context, MvelRule rule) {
        try {
            if (executeCondition(rule.getCondition(), context)) {
                executeAction(rule.getAction(), context);
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
    protected boolean executeCondition(String condition, Context context) {
        try {
            MvelContext mvelContext = null;
            if (context instanceof MvelContext) {
                mvelContext = (MvelContext) context;
            } else {
//                mvelContext = new MvelContext(context);
            }
            Boolean isEvaluate = (Boolean) MVEL.eval(condition, context);
            mvelContext.put(condition, isEvaluate);
            return isEvaluate;
        } catch (Exception e) {
            throw new RuntimeException(String.format("条件[%s]匹配发生异常:", condition), e);
        }
    }


    /**
     * 执行条件匹配后的操作
     */
    protected void executeAction(String action, Context context) {
        try {
            MvelContext mvelContext = null;
            if (context instanceof MvelContext) {
                mvelContext = (MvelContext) context;
            } else {
//                mvelContext = new MvelContext(context);
            }
            Serializable result = MVEL.compileExpression(action, context);
            mvelContext.put(action, result);
            System.out.println(result.toString());

        } catch (Exception e) {
            throw new RuntimeException(String.format("后续操作[%s]执行发生异常:", action), e);
        }
    }

}
