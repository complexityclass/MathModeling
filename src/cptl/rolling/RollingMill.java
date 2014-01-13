package cptl.rolling;

import cptl.enums.DistributionType;
import cptl.enums.RollingMillType;
import cptl.metal.Metal;
import cptl.utils.CPTLLog;
import cptl.utils.CPTLLogger;
import cptl.utils.RandomGenerator;

import java.util.Queue;

/**
 * Created by complexityclass on 1/12/14.
 */
public class RollingMill {
    private RollingMillType rollingMillType;
    private RandomGenerator randomGenerator;
    private int time;
    private Metal metal;
    private boolean isAvailable;
    private Queue<Metal> out;

    private boolean makeLog;
    private CPTLLogger logger;
    private CPTLLog log;

    private int totalWorkTime;

    public RollingMill(CPTLLogger logger, Queue<Metal> out, RollingMillType rollingMillType){
        this.logger = logger;
        this.makeLog = false;
        this.log = new CPTLLog();
        this.out = out;

        this.rollingMillType = rollingMillType;
        this.randomGenerator = new RandomGenerator(DistributionType.Normal);
        this.isAvailable = true;
        this.totalWorkTime = 0;
    }

    public boolean isAvailable(){
        return  isAvailable;
    }

    public void take(Metal metal){
        this.metal = new Metal(metal);
        isAvailable = false;

        double velocity = randomGenerator.getNextDoubleRandom(0,100);
        if(rollingMillType == RollingMillType.ROLLING_MILL_TYPE_1400){

        }
    }

}
