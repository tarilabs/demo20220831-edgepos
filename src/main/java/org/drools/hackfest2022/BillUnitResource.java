package org.drools.hackfest2022;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.kie.kogito.rules.RuleUnit;
import org.kie.kogito.rules.RuleUnitInstance;

@Path("/bill")
public class BillUnitResource {

    @javax.inject.Inject
    RuleUnit<BillUnitData> ruleUnit;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public BillUnitData rules(BillUnitData unitDTO) {
        RuleUnitInstance<BillUnitData> instance = ruleUnit.createInstance(unitDTO);
        instance.fire();
        instance.dispose();
        return unitDTO; 
    }
}