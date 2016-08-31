package me.demo.ruleengine.mvel.util;

import org.apache.commons.lang3.StringUtils;

import java.util.logging.Logger;

import static java.lang.String.format;

/**
 * Utilities class.
 *
 * Created by Think on 2016/8/31.
 */
public final class Utils {

    /**
     * Default rule name.
     */
    public static final String DEFAULT_RULE_NAME = "rule";
    /**
     * Default engine name.
     */
    public static final String DEFAULT_ENGINE_NAME = "engine";
    /**
     * Default rule description.
     */
    public static final String DEFAULT_RULE_DESCRIPTION = "description";
    /**
     * Default rule priority.
     */
    public static final int DEFAULT_RULE_PRIORITY = Integer.MAX_VALUE - 1;
    /**
     * Default rule priority threshold.
     */
    public static final int DEFAULT_RULE_PRIORITY_THRESHOLD = Integer.MAX_VALUE;
    private static final Logger LOGGER = Logger.getLogger(Utils.class.getName());


    private Utils() {
    }

    public static void checkNotNull(final Object argument, final String argumentName) {
        if (argument == null || StringUtils.isEmpty((String) argument)) {
            throw new IllegalArgumentException(format("The %s must not be null or empty", argumentName));
        }
    }

}
