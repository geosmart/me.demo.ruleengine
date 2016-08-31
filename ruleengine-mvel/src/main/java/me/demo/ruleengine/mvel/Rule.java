package me.demo.ruleengine.mvel;

import org.apache.commons.lang3.StringUtils;

/**
 * 规则-基类
 * Created by Think on 2016/8/30.
 */
public class Rule implements Comparable<Rule> {
    /**
     * 规则标识，后增加的会替换先增加的
     */
    String id;
    String title;
    String description;
    /**
     * 规则类型（如mvel）
     */
    String type;

    /**
     * when:LHS
     */

    private String condition;
    /**
     * then:RHS
     */
    private String action;
    /**
     * 优先级,数值越小优先级越高，用户设置优先级必须大于0;如果没有设置，系统会随机分配一个优先级<br>
     */
    private int priority;
    /**
     * 是否允许执行多次
     */
    private boolean repeat;
    /**
     * 是否是独占规则，如果是独占规则，执行完毕之后立即结束
     */
    private boolean exclusive;

    /**
     * 验证规则是否有效
     */
    public boolean isValidate() {
        if (StringUtils.isEmpty(getCondition())) {
            throw new RuntimeException(String.format("rule[%s]的condition为空", getId()));
        }
        if (StringUtils.isEmpty(getAction())) {
            throw new RuntimeException(String.format("rule[%s]的action为空", getId()));
        }
        return true;
    }

    public boolean equals(Object o) {
        if (o instanceof Rule) {
            return ((Rule) o).getId().equals(id);
        }
        return false;
    }

    public int compareTo(Rule o) {
        return Integer.valueOf(priority).compareTo(Integer.valueOf(o.priority));
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public boolean isRepeat() {
        return repeat;
    }

    public void setRepeat(boolean repeat) {
        this.repeat = repeat;
    }

    public boolean isExclusive() {
        return exclusive;
    }

    public void setExclusive(boolean exclusive) {
        this.exclusive = exclusive;
    }
}
