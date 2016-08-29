package me.demo.ruleengine.drools.domain;

import java.util.Date;

/**
 * 银行资金流
 * Created by Think on 2016/8/26.
 */
public class CashFlow {
    private Date date;
    private double amount;
    private String type;
    private long accountNo;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(long accountNo) {
        this.accountNo = accountNo;
    }


    public static class Account {

        private long accountNo;
        private double blance;

        public long getAccountNo() {
            return accountNo;
        }

        public void setAccountNo(long accountNo) {
            this.accountNo = accountNo;
        }

        public double getBlance() {
            return blance;
        }

        public void setBlance(double blance) {
            this.blance = blance;
        }
    }

    public static class AccountPeriod {

        private Date start;
        private Date end;

        public Date getStart() {
            return start;
        }

        public void setStart(Date start) {
            this.start = start;
        }

        public Date getEnd() {
            return end;
        }

        public void setEnd(Date end) {
            this.end = end;
        }
    }

}