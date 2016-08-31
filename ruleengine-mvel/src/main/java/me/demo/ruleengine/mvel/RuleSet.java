package me.demo.ruleengine.mvel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * RuleSet规则集定义了一组规则，也是规则引擎执行的基本单元。
 *
 * @注意 RuleSet的name属性必须唯一，不可以定义两个相同名称的规则集，这样会导致重复加载。
 *
 * Created by Think on 2016/8/30.
 */
public class RuleSet {
    private String name;
    private TreeSet<Rule> rules;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Rule> getRules() {
        if (rules == null) {
            rules = new TreeSet<>();
        }
        return rules;
    }

    public void setRules(TreeSet<Rule> rules) {
        this.rules = rules;
    }
}
