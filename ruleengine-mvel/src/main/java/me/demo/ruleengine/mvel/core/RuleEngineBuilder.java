package me.demo.ruleengine.mvel.core;

import me.demo.ruleengine.mvel.util.Utils;

/**
 * Builder for rules engine instances.
 *
 * Created by Think on 2016/8/31.
 */
public class RuleEngineBuilder {
    private RuleEngineParameter parameters;

    private RuleEngineBuilder() {
        parameters = new RuleEngineParameter(Utils.DEFAULT_ENGINE_NAME, false, false, false, Utils.DEFAULT_RULE_PRIORITY_THRESHOLD, false);
    }

    public static RuleEngineBuilder newRuleEngine() {
        return new RuleEngineBuilder();
    }

    public RuleEngine build() {
        return new DefaultRuleEngine(parameters);
    }

    public RuleEngineBuilder named(final String name) {
        parameters.setName(name);
        return this;
    }

    public RuleEngineBuilder withSkipOnFirstAppliedRule(final boolean skipOnFirstAppliedRule) {
        parameters.setSkipOnFirstAppliedRule(skipOnFirstAppliedRule);
        return this;
    }

    public RuleEngineBuilder withSkipOnFirstFailedRule(final boolean skipOnFirstFailedRule) {
        parameters.setSkipOnFirstFailedRule(skipOnFirstFailedRule);
        return this;
    }

    public RuleEngineBuilder withRulePriorityThreshold(final int priorityThreshold) {
        parameters.setPriorityThreshold(priorityThreshold);
        return this;
    }

    public RuleEngineBuilder withSilentMode(final boolean silentMode) {
        parameters.setSilentMode(silentMode);
        return this;
    }
}
