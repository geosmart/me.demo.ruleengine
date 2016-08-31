package me.demo.ruleengine.mvel.core;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Rule执行 输入/输出的上下文
 * Created by Think on 2016/8/30.
 */
public class RuleContext extends ConcurrentHashMap<String, Object> implements Map<String, Object> {
}
