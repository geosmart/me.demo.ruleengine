package me.demo.ruleengine.drools.test; /**
 * Created by geosmart on 2016/7/25.
 */

import org.drools.runtime.StatefulKnowledgeSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import me.demo.ruleengine.drools.domain.CashFlow;
import me.demo.ruleengine.drools.service.CashFlowEngine;

/**
 * Test class for CashFlowEngine
 * Created by geomart on 2016/8/26.S
 *
 * @see [https://github.com/liangbo/droolsdemo/blob/master/study_note.md]
 * @see CashFlowEngine
 */
public class CashFlowEngineTest {
    CashFlowEngine engine = new CashFlowEngine();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Before
    public void setup() {
        engine.addResourceByDrl("cashFlow.drl");
        engine.newStatefulKnowledgeSession();
        System.out.println("test begin...");
    }

    @Test
    public void test_cashFlow() throws ParseException {
        StatefulKnowledgeSession session = engine.getSession();
        /** ******************第一条记录************************* */
        CashFlow cf0 = new CashFlow();
        String time0 = "2010-01-12";
        cf0.setDate(sdf.parse(time0));
        cf0.setAmount(100);
        cf0.setType("CREDIT");
        cf0.setAccountNo(1);
        session.insert(cf0);

        CashFlow cf1 = new CashFlow();
        String time1 = "2010-02-02";
        cf1.setDate(sdf.parse(time1));
        cf1.setAmount(200);
        cf1.setType("DEBIT");
        cf1.setAccountNo(1);
        session.insert(cf1);

        CashFlow cf2 = new CashFlow();
        String time2 = "2010-05-18";
        cf2.setDate(sdf.parse(time2));
        cf2.setAmount(50);
        cf2.setType("CREDIT");
        cf2.setAccountNo(1);
        session.insert(cf2);

        CashFlow cf3 = new CashFlow();
        String time3 = "2010-03-18";
        cf3.setDate(sdf.parse(time3));
        cf3.setAmount(75);
        cf3.setType("CREDIT");
        cf3.setAccountNo(1);
        session.insert(cf3);

        CashFlow.Account acc = new CashFlow.Account();
        acc.setAccountNo(1);
        acc.setBlance(0);
        session.insert(acc);

        /** ******************第二条记录************************* */

        CashFlow cf0_else = new CashFlow();
        String time0_else = "2010-01-12";
        cf0_else.setDate(sdf.parse(time0_else));
        cf0_else.setAmount(150);
        cf0_else.setType("CREDIT");
        cf0_else.setAccountNo(2);
        session.insert(cf0_else);

        CashFlow cf1_else = new CashFlow();
        String time1_else = "2010-02-02";
        cf1_else.setDate(sdf.parse(time1_else));
        cf1_else.setAmount(500);
        cf1_else.setType("DEBIT");
        cf1_else.setAccountNo(2);
        session.insert(cf1_else);

        CashFlow cf2_else = new CashFlow();
        String time2_else = "2010-05-18";
        cf2_else.setDate(sdf.parse(time2_else));
        cf2_else.setAmount(50);
        cf2_else.setType("CREDIT");
        cf2_else.setAccountNo(2);
        session.insert(cf2_else);

        CashFlow cf3_else = new CashFlow();
        String time3_else = "2010-03-18";
        cf3_else.setDate(sdf.parse(time3_else));
        cf3_else.setAmount(75);
        cf3_else.setType("CREDIT");
        cf3_else.setAccountNo(2);
        session.insert(cf3_else);

        CashFlow.Account acc_else = new CashFlow.Account();
        acc_else.setAccountNo(2);
        acc_else.setBlance(0);
        session.insert(acc_else);

        /* 查询条件 */
        CashFlow.AccountPeriod ap = new CashFlow.AccountPeriod();
        String start = "2009-05-06";
        String end = "2012-02-25";
        ap.setStart(sdf.parse(start));
        ap.setEnd(sdf.parse(end));
        session.insert(ap);
        session.fireAllRules();
        session.dispose();

        System.out.println(acc.getAccountNo() + " : " + acc.getBlance());
        System.out.println(acc_else.getAccountNo() + " : "
                + acc_else.getBlance());
    }

    @After
    public void teardown() {
        System.out.println("test stop...");
        engine.dispose();
    }
}
