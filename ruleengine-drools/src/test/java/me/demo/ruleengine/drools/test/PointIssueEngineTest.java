package me.demo.ruleengine.drools.test; /**
 * Created by geosmart on 2016/7/25.
 */

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import me.demo.ruleengine.drools.domain.PointDO;
import me.demo.ruleengine.drools.domain.Promotion;
import me.demo.ruleengine.drools.service.PointIssueEngine;

/**
 * Test class for ESService
 * Created by geomart on 2016/8/24.
 *
 * @see [https://github.com/liangbo/droolsdemo/blob/master/study_note.md]
 * @see PointIssueEngine
 */
public class PointIssueEngineTest {

    @Before
    public void setup() {
        System.out.println("test begin...");
    }

    @Test
    public void test_pointIssueEngine() {
        PointIssueEngine engine = new PointIssueEngine();

        PointDO point = new PointDO();
        point.setUserName("Tom");
        point.setConsumeTotal(1000);
        point.setNewMemberIn3Months(false);
        point.setWeekend(false);
        point.setCountOfMonth(10);
        point.setBirthdayMonth(true);

        Promotion promotion = new Promotion();
        promotion.setInPromotion(false);
        engine.executeRuleEngine(Arrays.asList(point, promotion));
        point.print();
    }

    @After
    public void teardown() {
        System.out.println("test stop...");
    }
}
