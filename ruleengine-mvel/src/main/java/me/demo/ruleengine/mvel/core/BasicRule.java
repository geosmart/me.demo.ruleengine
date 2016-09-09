package me.demo.ruleengine.mvel.core;

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

    @Override
    public boolean evaluate(RuleContext ruleContext) {
        //default false
        return false;
    }

    @Override
    public void execute(RuleContext ruleContext) {
        //do nothing
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
