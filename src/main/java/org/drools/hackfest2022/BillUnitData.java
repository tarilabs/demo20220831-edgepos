package org.drools.hackfest2022;

import org.drools.hackfest2022.model.Alert;
import org.drools.hackfest2022.model.Item;
import org.kie.kogito.rules.DataSource;
import org.kie.kogito.rules.DataStore;
import org.kie.kogito.rules.RuleUnitData;

public class BillUnitData implements RuleUnitData {
    private DataStore<Item> items;
    private DataStore<Alert> alerts;

    public BillUnitData() {
        this(DataSource.createStore());
    }
    public BillUnitData(DataStore<Item> items) {
        this.items = items;
        this.alerts = DataSource.createStore();
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
    
}
