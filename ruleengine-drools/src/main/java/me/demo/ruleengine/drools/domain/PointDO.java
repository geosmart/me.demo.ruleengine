package me.demo.ruleengine.drools.domain;

/**
 * Created by Think on 2016/8/24.
 */
public class PointDO {
    // 用户姓名
    private String userName;

    // 是否是生日月
    private boolean birthdayMonth;

    // 三个月内新用户
    private boolean newMemberIn3Months;

    // 是否周末
    private boolean weekend;

    // 月消费次数
    private int countOfMonth;

    //积分
    private long point;

    //消费金额
    private long consumeTotal;

    //是否已发放
    private boolean issue;


    public void print() {
        System.out.println(String.format("%s is issued %s point!", userName, point));
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isBirthdayMonth() {
        return birthdayMonth;
    }

    public void setBirthdayMonth(boolean birthdayMonth) {
        this.birthdayMonth = birthdayMonth;
    }

    public boolean isNewMemberIn3Months() {
        return newMemberIn3Months;
    }

    public void setNewMemberIn3Months(boolean newMemberIn3Months) {
        this.newMemberIn3Months = newMemberIn3Months;
    }

    public boolean isWeekend() {
        return weekend;
    }

    public void setWeekend(boolean weekend) {
        this.weekend = weekend;
    }

    public int getCountOfMonth() {
        return countOfMonth;
    }

    public void setCountOfMonth(int countOfMonth) {
        this.countOfMonth = countOfMonth;
    }

    public long getPoint() {
        return point;
    }

    public void setPoint(long point) {
        this.point = point;
    }

    public long getConsumeTotal() {
        return consumeTotal;
    }

    public void setConsumeTotal(long consumeTotal) {
        this.consumeTotal = consumeTotal;
    }

    public boolean issue() {
        return issue;
    }

    public void setIssue(boolean issue) {
        this.issue = issue;
    }
}
