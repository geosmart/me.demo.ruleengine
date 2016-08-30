package me.demo.ruleengine.mvel.rule;

/**
 * Created by Think on 2016/8/30.
 */
public abstract class Rule implements Comparable<Rule> {
    String title;
    String description;
    /**
     * 执行器类型
     */
    String type;
    /**
     * 规则标识，后增加的会替换先增加的
     */
    String id;
    /**
     * 优先级,数值越小优先级越高，用户设置优先级必须大于0;如果没有设置，系统会随机分配一个优先级<br>
     * 注意：同一个规则集不能出现两个相同优先级的规则
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
    public abstract boolean isValidate();

    public boolean equals(Object o) {
        if (o instanceof Rule) {
            return ((Rule) o).getId().equals(id);
        }
        return false;
    }

    public int compareTo(Rule o) {
        return Integer.valueOf(priority).compareTo(Integer.valueOf(o.priority));
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
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

}
