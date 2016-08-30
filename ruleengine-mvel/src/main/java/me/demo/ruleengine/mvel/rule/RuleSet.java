package me.demo.ruleengine.mvel.rule;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Think on 2016/8/30.
 */
public class RuleSet {
    private String name;
    private List<Rule> rules;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Rule> getRules() {
        if (rules == null) {
            rules = new ArrayList<Rule>();
        }
        return rules;
    }

    public void setRules(List<Rule> rules) {
        this.rules = rules;
    }
}
