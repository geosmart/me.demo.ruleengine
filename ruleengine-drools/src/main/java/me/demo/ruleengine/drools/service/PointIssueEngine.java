package me.demo.ruleengine.drools.service;

import org.drools.runtime.StatefulKnowledgeSession;

import java.util.List;

/**
 * 积分发放
 * Created by Think on 2016/8/24.
 */
public class PointIssueEngine extends BaseRuleEngine {
    public void executeRuleEngine(List<Object> list) {
        addResourceByDrl("point.drl");
        StatefulKnowledgeSession session = getKnowledgeBase().newStatefulKnowledgeSession();
        list.forEach(obj -> session.insert(obj));
        session.fireAllRules();
        session.dispose();
    }
}
