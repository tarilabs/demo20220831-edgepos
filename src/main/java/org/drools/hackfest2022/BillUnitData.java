package org.drools.hackfest2022;

import org.drools.hackfest2022.model.Alert;
import org.drools.hackfest2022.model.Coupon;
import org.drools.hackfest2022.model.Item;
import org.kie.kogito.rules.DataSource;
import org.kie.kogito.rules.DataStore;
import org.kie.kogito.rules.RuleUnitData;

public class BillUnitData implements RuleUnitData {
    private DataStore<Item> items;
    private DataStore<Coupon> coupons;
    private String loyaltyID;
    private DataStore<Alert> alerts;
    private DataStore<String> logs;

    public BillUnitData() {
        this(DataSource.createStore(), DataSource.createStore(), null);
    }
    public BillUnitData(DataStore<Item> items, DataStore<Coupon> coupons, String loyaltyID) {
        this.items = items;
        this.coupons = coupons;
        this.loyaltyID = loyaltyID;
        this.alerts = DataSource.createStore();
        this.logs = DataSource.createStore();
    }
    public DataStore<Item> getItems() {
        return items;
    }
    public void setItems(DataStore<Item> items) {
        this.items = items;
    }
    public DataStore<Alert> getAlerts() {
        return alerts;
    }
    public void setAlerts(DataStore<Alert> alerts) {
        this.alerts = alerts;
    }
    public DataStore<Coupon> getCoupons() {
        return coupons;
    }
    public void setCoupons(DataStore<Coupon> coupons) {
        this.coupons = coupons;
    }
    public String getLoyaltyID() {
        return loyaltyID;
    }
    public void setLoyaltyID(String loyaltyID) {
        this.loyaltyID = loyaltyID;
    }
    public DataStore<String> getLogs() {
        return logs;
    }
    public void setLogs(DataStore<String> logs) {
        this.logs = logs;
    }
    
}
