package me.demo.ruleengine.mvel.core;

import org.mvel2.MVEL;

import java.io.Serializable;

import me.demo.ruleengine.mvel.util.Utils;

/**
 * Basic rule implementation class that provides common methods.
 *
 * You can extend this class and override {@link BasicRule#evaluate(RuleContext)} and {@link
 * BasicRule#execute(RuleContext)} to provide rule conditions and actions logic.
 *
 * Created by Think on  2016/8/30.
 */
public class BasicRule implements Rule, Comparable<Rule> {
    /**
     * Rule name.
     */
    protected String name;

    /**
     * Rule description.
     */
    protected String description;

    /**
     * Rule priority.
     */
    protected int priority;

    /**
     * Rule Condition,when:LHS
     */

    private String condition;
    /**
     * Rule Action,then:RHS
     */
    private String action;

    public BasicRule() {
    }

    public BasicRule(String name, String description, int priority) {
        this.name = name;
        this.description = description;
        this.priority = priority;
    }

    /*
    * Rules are unique according to their names within a rules engine registry.
    */
    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BasicRule basicRule = (BasicRule) o;

        if (priority != basicRule.priority) return false;
        if (!name.equals(basicRule.name)) return false;
        return !(description != null ? !description.equals(basicRule.description) : basicRule.description != null);

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + priority;
        return result;
    }

    @Override
    public String toString() {
        return name;
    }

    public int compareTo(Rule rule) {
        if (priority < rule.getPriority()) {
            return -1;
        } else if (priority > rule.getPriority()) {
            return 1;
        } else {
            return getName().compareTo(rule.getName());
        }
    }

    @Override
    public boolean validate() {
        Utils.checkNotNull(getCondition(), "rule condition");
        Utils.checkNotNull(getAction(), "rule action");
        return true;
    }

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

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    @Override
    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    @Override
    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
