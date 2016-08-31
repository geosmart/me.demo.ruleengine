package me.demo.ruleengine.mvel.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;

/**
 * This implementation handles a set of rules with unique name.
 *
 * Rules are fired according to their natural order which is priority by default.
 *
 * Created by Think on 2016/8/30.
 */
public class DefaultRuleEngine implements RuleEngine {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * The rules set.
     */
    protected Set<Rule> rules;
    /**
     * The engine parameters
     */
    protected RuleEngineParameter parameters;

    /**
     * The rule fact
     */
    protected RuleContext fact;

    /**
     * ruleSet Map
     */
    private Map<String, TreeSet<BasicRule>> ruleSetMap = new ConcurrentHashMap<>();

    public DefaultRuleEngine(RuleEngineParameter parameters) {
        this.parameters = parameters;
        this.rules = new TreeSet<>();
        if (parameters.isSilentMode()) {
            //cancle log
        }
    }

    @Override
    public void fireRules(RuleContext fact) {
        if (rules.isEmpty()) {
            logger.warn("No rules registered! Nothing to apply");
            return;
        }

        logEngineParameters();
        sortRules();
        applyRules(fact);
    }

    @Override
    public void registerRule(Rule rule) {
        //检查规则
        if (rule.validate()) {
            rules.add(rule);
        }
    }

    @Override
    public void registerRule(RuleSet ruleSet) {
        ruleSet.getRules().forEach(rule -> registerRule(rule));
        logRegisteredRules();
    }


    @Override
    public void unregisterRule(Rule rule) {
        rules.remove(rule);
    }

    @Override
    public void clearRules() {
        ruleSetMap.clear();
    }

    @Override
    public Set<Rule> getRules() {
        return rules;
    }

    @Override
    public Map<Rule, Boolean> checkRules() {
        logger.info("Checking rules");
        sortRules();
        Map<Rule, Boolean> result = new HashMap<>();
        for (Rule rule : rules) {
            result.put(rule, rule.evaluate(fact));
        }
        return result;
    }

    private void logEngineParameters() {
        logger.info("-----\n");
        logger.info("Engine name: {}", parameters.getName());
        logger.info("Rule priority threshold: {}", parameters.getPriorityThreshold());
        logger.info("Skip on first applied rule: {}", parameters.isSkipOnFirstAppliedRule());
        logger.info("Skip on first unapplied rule: {}", parameters.isSkipOnFirstUnAppliedRule());
        logger.info("Skip on first failed rule: {}", parameters.isSkipOnFirstFailedRule());
    }

    private void logRegisteredRules() {
        logger.info("Registered rules:");
        for (Rule rule : rules) {
            logger.info("Rule { name = {}, description = {}, priority = {}}", rule.getName(), rule.getDescription(), rule.getPriority());
        }
    }

    private void sortRules() {
        rules = new TreeSet<>(rules);
    }

    private void applyRules(RuleContext fact) {
        logger.info("Rules evaluation started");

        for (Rule rule : rules) {
            final String name = rule.getName();
            final int priority = rule.getPriority();

            if (priority > parameters.getPriorityThreshold()) {
                logger.info("Rule priority threshold ({}) exceeded at rule {} with priority={}, next rules will be skipped",
                        new Object[]{parameters.getPriorityThreshold(), name, priority});
                break;
            }

            if (rule.evaluate(fact)) {
                logger.info("Rule [{}] triggered", name);
                try {
                    rule.execute(fact);
                    logger.info("Rule {} performed successfully", name);
                    if (parameters.isSkipOnFirstAppliedRule()) {
                        logger.info("Next rules will be skipped since parameter skipOnFirstAppliedRule is set");
                        break;
                    }
                    if (parameters.isSkipOnFirstUnAppliedRule()) {
                        logger.info("Next rules will be skipped since parameter skipOnFirstUnAppliedRule is set");
                        break;
                    }
                } catch (Exception exception) {
                    logger.error("Rule [{}] performed with error {}", name, exception);

                    if (parameters.isSkipOnFirstFailedRule()) {
                        logger.info("Next rules will be skipped since parameter skipOnFirstFailedRule is set");
                        break;
                    }
                }
            } else {
                logger.info("Rule [{}] has been evaluated to false, it has not been executed", name);
            }
        }
    }

    @Override
    public RuleEngineParameter getParameters() {
        return parameters;
    }


}
