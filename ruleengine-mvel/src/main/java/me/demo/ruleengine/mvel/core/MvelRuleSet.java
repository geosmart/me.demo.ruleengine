package me.demo.ruleengine.mvel.core;

import org.apache.commons.lang3.StringUtils;

import java.util.Set;
import java.util.TreeSet;

import me.demo.ruleengine.mvel.util.Utils;

/**
 * RuleSet规则集定义了一组规则
 *
 * Created by Think on 2016/8/30.
 */
public class MvelRuleSet {
    private String name;
    private TreeSet<MvelRule> rules;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (StringUtils.isEmpty(name)) {
            name = Utils.DEFAULT_RULE_NAME;
        }
        this.name = name;
    }

    public Set<MvelRule> getRules() {
        if (rules == null) {
            rules = new TreeSet<>();
        }
        return rules;
    }

    public void setRules(TreeSet<MvelRule> rules) {
        this.rules = rules;
    }
}
