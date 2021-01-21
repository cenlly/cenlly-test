package com.cenlly.domain;

public class JobData {
    private boolean safe_question;
    private boolean watch;
    private int coins;
    private boolean share;
    private boolean tel;
    private boolean identify_card;
    private boolean login;
    private boolean email;

    public boolean isSafe_question() {
        return safe_question;
    }

    public void setSafe_question(boolean safe_question) {
        this.safe_question = safe_question;
    }

    public boolean isWatch() {
        return watch;
    }

    public void setWatch(boolean watch) {
        this.watch = watch;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public boolean isShare() {
        return share;
    }

    public void setShare(boolean share) {
        this.share = share;
    }

    public boolean isTel() {
        return tel;
    }

    public void setTel(boolean tel) {
        this.tel = tel;
    }

    public boolean isIdentify_card() {
        return identify_card;
    }

    public void setIdentify_card(boolean identify_card) {
        this.identify_card = identify_card;
    }

    public boolean isLogin() {
        return login;
    }

    public void setLogin(boolean login) {
        this.login = login;
    }

    public boolean isEmail() {
        return email;
    }

    public void setEmail(boolean email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "JobData{" +
                "safe_question=" + safe_question +
                ", watch=" + watch +
                ", coins=" + coins +
                ", share=" + share +
                ", tel=" + tel +
                ", identify_card=" + identify_card +
                ", login=" + login +
                ", email=" + email +
                '}';
    }
}
