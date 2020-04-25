package com.insurance.api.model.entities.pojos;

public class Balance {

    Long invested;
    Long earned;
    Long capital;
    Long cash;

    public Balance(Long invested, Long earned) {
        this.invested = invested;
        this.earned = earned;
        this.capital = invested + earned;
        this.cash = earned;
    }

    public Balance() {
    }

    public Long getInvested() {
        return invested;
    }

    public void setInvested(Long invested) {
        this.invested = invested;
    }

    public Long getEarned() {
        return earned;
    }

    public void setEarned(Long earned) {
        this.earned = earned;
    }

    public Long getCapital() {
        return capital;
    }

    public void setCapital(Long capital) {
        this.capital = capital;
    }

    public Long getCash() {
        return cash;
    }

    public void setCash(Long cash) {
        this.cash = cash;
    }

    @Override
    public String toString() {
        return " Balance { " +
                "invested = " + invested +
                ", earned = " + earned +
                ", capital = " + capital +
                ", cash = " + cash +
                " } ";
    }
}
