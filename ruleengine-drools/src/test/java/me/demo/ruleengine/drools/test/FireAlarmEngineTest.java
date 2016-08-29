package me.demo.ruleengine.drools.test; /**
 * Created by Think on 2016/7/25.
 */

import org.drools.runtime.rule.FactHandle;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.demo.ruleengine.drools.domain.FireAlarm;
import me.demo.ruleengine.drools.service.FireAlarmEngine;

/**
 * Test class for FireAlarm
 * Created by geomart on 2016/8/24.
 *
 * @see [https://github.com/liangbo/droolsdemo/blob/master/study_note.md]
 * @see FireAlarmEngine
 */
public class FireAlarmEngineTest {
    String[] names;
    FireAlarmEngine engine = new FireAlarmEngine();
    Map<String, FireAlarm.Room> name2Room = new HashMap<String, FireAlarm.Room>();

    @Before
    public void setup() {
        names = new String[]{"kitchen", "bedroom", "office", "livingroom"};

        for (String name : names) {
            FireAlarm.Room room = new FireAlarm.Room(name);
            name2Room.put(name, room);
        }
        engine.addResourceByDrl("fireAlarm.drl");
        engine.newStatefulKnowledgeSession();
        System.out.println("test begin...");
    }

    @Test
    public void test_fireAlarmEngine1() {
        //初始化房间，消防设施
        name2Room.forEach((name, room) -> {
            engine.getSession().insert(room);
            FireAlarm.Sprinkler sprinkler = new FireAlarm.Sprinkler(room);
            engine.getSession().insert(sprinkler);
        });
        engine.getSession().fireAllRules();

        //发布火警
        FireAlarm.Fire kitchenFire = new FireAlarm.Fire(name2Room.get("kitchen"));
        FireAlarm.Fire officeFire = new FireAlarm.Fire(name2Room.get("office"));
        List<FactHandle> factHandlers = engine.insertFacts(Arrays.asList(kitchenFire, officeFire));
        engine.getSession().fireAllRules();

        //消除火警
        for (FactHandle factHandle : factHandlers) {
            engine.getSession().retract(factHandle);
        }
        engine.getSession().fireAllRules();
    }

    @After
    public void teardown() {
        System.out.println("test stop...");
        engine.dispose();
    }
}
