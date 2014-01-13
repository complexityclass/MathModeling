package cptl.bath;

import cptl.enums.BathMode;
import cptl.enums.BathSolute;
import cptl.enums.DistributionType;
import cptl.metal.MetalPart;
import cptl.utils.*;

import java.util.Random;

/**
 * Created by complexityclass on 1/9/14.
 */
public class Bath {
    private BathMode bathMode;
    private BathSolute bathSolute;
    private boolean isAvailable;
    //private Random alphaBetweenTimes;
    //private Random betaNeedCorrecting;
    //private Random gammaTimeToCorrecting;
    private int time;
    private int timeMin;
    private int timeMax;
    private int correctingTime;
    private RandomGenerator delta;

     private int clearingTime;
    private MetalPart metalPart;
    private CPTLQueue out;

    private CPTLLog log;
    private CPTLLogger logger;
    private boolean makeLog;
    private int totalWorkTime;

    public Bath(int timeMin, int timeMax, CPTLLogger logger){
        this.bathMode = BathMode.Operation;

        this.logger = logger;
        this.makeLog = false;
        this.log = new CPTLLog();

        //alphaBetweenTimes = new Random();
        //betaNeedCorrecting = new Random();
        //gammaTimeToCorrecting = new Random();
        delta = new RandomGenerator(DistributionType.Normal);

        this.time = 0;
        this.timeMin = timeMin;
        this.timeMax = timeMax;

        this.correctingTime = 0;
        this.clearingTime = 0;

        this.isAvailable = true;
        this.totalWorkTime = 0;

    }

    public int getCorrectingTime() {
        return correctingTime;
    }

    public int getClearingTime() {
        return clearingTime;
    }

    public void setOut(CPTLQueue out){ this.out = out; }


    public void setBathSolute(BathSolute bathSolute) {
        this.bathSolute = bathSolute;
    }

    public boolean isAvailable(){
        if(bathMode == BathMode.Operation){
            return isAvailable;
        }else{
            return false;
        }
    }

    public boolean needAcidCorrection(){
        if(delta.getNextIntRandom(Global.MIN_CORRECTING,Global.MAX_CORRECTING) <= Global.NEED_ACID_CORRECTING_VALUE){
            return true;
        }else{
            return false;
        }
    }

    public boolean needAlkaliCorrection(){
        if(delta.getNextIntRandom(Global.MIN_CORRECTING,Global.MAX_CORRECTING) <= Global.NEED_ALKALI_CORRECTING_VALUE){
            return true;
        }else{
            return false;
        }
    }

    public void correcting(){
        bathMode = BathMode.Correction;

        if(bathSolute == BathSolute.Acid){
            if(needAcidCorrection()){
                correctingTime = delta.getNextIntRandom(Global.MIN_ACID_CORRECTION_TIME,Global.MAX_ACID_CORRECTION_TIME);
            }
        }else if(bathSolute == BathSolute.Alkalizator){
            if(needAlkaliCorrection()){
                correctingTime = delta.getNextIntRandom(Global.MIN_ALKALI_CORRECTION_TIME,Global.MAX_ALKALI_CORRECTION_TIME);
            }
        }
    }

    public void cleaning(){
        bathMode = BathMode.Clearing;

        double time = 0;

        while(true){
            time = delta.generatorM(Global.GENERATOR_MX,Global.GENERATOR_S);
            if(Global.GENERATOR_TIME_MIN <= time && time <= Global.GENERATOR_TIME_MAX){
                break;
            }
        }

        clearingTime = (int)time;
    }

    public void take(MetalPart metalPart){
        isAvailable = false;
        time = delta.getNextIntRandom(timeMin,timeMax);
        this.metalPart = new MetalPart(metalPart);
        inLog(metalPart, time);
    } 
    public void iteration(){
        if(bathMode == BathMode.Operation){
            if(!isAvailable){

                totalWorkTime++;
                if(time > 0){
                    time--;
                }else{
                    outLog(metalPart, out);
                    out.take(metalPart);
                    metalPart = null;
                    isAvailable = true;
                }
            }
        }

        if(bathMode == BathMode.Correction){
            if(correctingTime > 0){
                correctingTime--;
            }else{
                bathMode = BathMode.Operation;
            }
        }

        if(bathMode == BathMode.Clearing){
            if(clearingTime > 0){
                clearingTime--;
            }else{
                bathMode = BathMode.Operation;
            }
        }

        makeLog();
    }

    public void onLog(){
        makeLog = true;
    }

    public void offLog(){
        makeLog = false;
    }

    public void setName(String name){
        log.setName(name);
    }

    public String getName(){
        return log.getName();
    }

    public void makeLog(){
        if(log.isMessagesInLog()){
            logger.writeLog(log);
            log.clearLog();
        }
    }

    public void inLog(MetalPart metalPart, int time){
        if(makeLog){
            log.addMessage(String.format("app %s taken and time %d",metalPart.toString(), time));
        }
    }

    public void outLog(MetalPart metalPart, CPTLQueue out){
        if(makeLog){
            log.addMessage(String.format("app %s processed and send to %s ",metalPart.toString(),out.getName()));
        }
    }

    public int getTotalWorkTime(){
        return totalWorkTime;
    }
}


































