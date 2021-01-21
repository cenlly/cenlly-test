package com.cenlly.domain;

import java.util.Date;

public class CoinsLog {
    private int delta;
    private String reason;
    private Date time;

    public int getDelta() {
        return delta;
    }

    public void setDelta(int delta) {
        this.delta = delta;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "CoinsLog{" +
                "delta=" + delta +
                ", reason='" + reason + '\'' +
                ", time=" + time +
                '}';
    }
}
