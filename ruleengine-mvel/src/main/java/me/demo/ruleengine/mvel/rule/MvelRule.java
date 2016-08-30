package me.demo.ruleengine.mvel.rule;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import org.apache.commons.lang3.StringUtils;

/**
 * 采用MVEL表达式作为条件实现
 * Created by Think on 2016/8/30.
 */
@XStreamAlias("mvel-rule")
public class MvelRule extends Rule {
    //when:LHS
    private String condition;
    //then:RHS
    private String action;

    @Override
    public boolean isValidate() {
        if (StringUtils.isEmpty(getCondition())) {
            throw new RuntimeException(String.format("rule[%s]的when为空", getId()));
        }
        if (StringUtils.isEmpty(getAction())) {
            throw new RuntimeException(String.format("rule[%s]的then为空", getId()));
        }
        return true;
    }

    @Override
    public String getType() {
        return "mvel";
    }

    @Override
    public String toString() {
        return "MvelRule{" +
                "condition='" + condition + '\'' +
                ", action='" + action + '\'' +
                '}';
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
