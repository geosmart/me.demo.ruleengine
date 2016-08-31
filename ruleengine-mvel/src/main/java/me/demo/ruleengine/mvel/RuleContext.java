package me.demo.ruleengine.mvel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Rule执行结果上下文
 * Created by Think on 2016/8/30.
 */
public class RuleContext extends ConcurrentHashMap<String, Object> implements Map<String, Object> {
}
