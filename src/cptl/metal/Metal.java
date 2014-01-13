package cptl.metal;

import cptl.enums.MetalType;
import cptl.enums.RollingShedule;
import cptl.enums.Route;

/**
 * Created by complexityclass on 1/6/14.
 */

/*Metal Application*/
public class Metal {

    private int weight;
    private int thermGroup;
    private int startTime;
    private int appId;
    private int caseId;
    private Route route;
    private MetalType metalType;
    private RollingShedule rollingShedule;

    public Metal(MetalType type, int weight, RollingShedule shedule, int thermGroup){
        this.metalType = type;
        this.weight = weight;
        this.rollingShedule = shedule;
        this.thermGroup = thermGroup;
        this.route = Route.ToColdRolling;
        this.startTime = -1;
    }

    public Metal(Metal metal){
        this.appId = metal.getAppId();
        this.weight = metal.getWeight();
        this.metalType = metal.getMetalType();
        this.rollingShedule = metal.getRollingShedule();
        this.thermGroup = metal.getThermGroup();
        this.route = metal.getRoute();
        this.caseId = metal.getCaseId();
        this.startTime = metal.getStartTime();
    }

    /*appId getter/setter*/
    public int getAppId() {  return appId; }
    public void setAppId(int appId) { this.appId = appId; }

    /*caseId getter/setter*/
    public int getCaseId() { return caseId; }
    public void setCaseId(int caseId) { this.caseId = caseId; }

    /*start getter/setter */
    public int getStartTime() { return startTime; }
    public void setStartTime(int startTime) { this.startTime = startTime; }

    public int getWeight() { return weight; }

    public MetalType getMetalType() { return metalType; }

    public RollingShedule getRollingShedule() { return rollingShedule; }

    public int getThermGroup() { return thermGroup; }

    public Route getRoute() { return route; }

    @Override
    public String toString() {
        return String.format("№ %d | case %d | %s | %s | %s | %d ",
                appId, caseId, metalType, rollingShedule, thermGroup, weight);
    }
}


























