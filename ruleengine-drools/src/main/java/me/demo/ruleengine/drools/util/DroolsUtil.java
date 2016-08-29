package me.demo.ruleengine.drools.util;

import org.drools.spi.KnowledgeHelper;

/**
 * Created by Think on 2016/8/25.
 */
public class DroolsUtil {

    public static void help(final KnowledgeHelper drools, final String message) {
        System.out.println(message);
        System.out.println("\nrule triggered: " + drools.getRule().getName());
    }

    public static void helper(final KnowledgeHelper drools) {
        System.out.println("\nrule triggered: " + drools.getRule().getName());
    }

    public static void debug(final Object object) {
        System.out.println(object.toString());
    }

}
