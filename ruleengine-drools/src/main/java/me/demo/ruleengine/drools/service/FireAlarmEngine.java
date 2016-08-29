package me.demo.ruleengine.drools.service;

import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.rule.FactHandle;

import java.util.ArrayList;
import java.util.List;

/**
 * 火警
 * Created by Think on 2016/8/24.
 */
public class FireAlarmEngine extends BaseRuleEngine {
    private StatefulKnowledgeSession session;

    public StatefulKnowledgeSession getSession() {
        return session;
    }

    public void newStatefulKnowledgeSession() {
        session = getKnowledgeBase().newStatefulKnowledgeSession();
    }

    public List<FactHandle> insertFacts(List<Object> list) {
        List<FactHandle> factHandles = new ArrayList<>();
        list.forEach(obj -> factHandles.add(session.insert(obj)));
        return factHandles;
    }

    public void dispose() {
        if (session != null)
            session.dispose();
    }
}
