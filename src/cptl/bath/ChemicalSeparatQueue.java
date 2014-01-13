package cptl.bath;

import cptl.enums.MetalType;
import cptl.metal.Driver;
import cptl.metal.Metal;
import cptl.metal.MetalPart;
import cptl.utils.CPTLLog;
import cptl.utils.CPTLLogger;
import cptl.utils.CPTLQueue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by complexityclass on 1/11/14.
 */
public class ChemicalSeparatQueue implements CPTLQueue {

    private Driver out1;
    private ChemicalDistrQueue out2;
    private Queue<MetalPart> queue;

    private boolean makeLog;
    private CPTLLogger logger;
    private CPTLLog log;

    public ChemicalSeparatQueue(CPTLLogger logger){
        queue = new LinkedList<MetalPart>();
        this.logger = logger;
        this.makeLog = false;
        this.log = new CPTLLog();
    }



    @Override
    public void take(MetalPart metalPart) {

        queue.add(metalPart);
        if(makeLog) inLog(metalPart);

    }

    public void iteration(){
        while(queue.size() > 0){
            MetalPart metalPart = queue.poll();
            if(metalPart.getParent().getMetalType() == MetalType.Carbon){
                out1.take(metalPart);
                outLog1(metalPart);
            }else{
                out2.take(metalPart);
                outLog2(metalPart);
            }
        }
        makeLog();
    }


    public void setOut(Driver driver){
        out1 = driver;
    }

    public void setOut(ChemicalDistrQueue distrQueue){
        out2 = distrQueue;
    }

    public void logOn(){
        makeLog = true;
    }

    public void logOff(){
        makeLog = false;
    }

    public void setName(String name){
        log.setName(name);
    }

    @Override
    public String getName() {
        return log.getName();
    }

    public void makeLog(){
        if(log.isMessagesInLog()){
            logger.writeLog(log);
            log.clearLog();
        }
    }

    public void inLog(MetalPart metalPart){
        if(makeLog){
            log.addMessage(String.format("Get App %s",metalPart.toString()));
        }
    }

    public void outLog1(MetalPart metalPart){

        if(makeLog){
            log.addMessage(String.format("App %s move to %s",metalPart.toString(),out1.getName()));
        }
    }

    public void outLog2(MetalPart metalPart){
        if(makeLog){
            log.addMessage(String.format("App %s is move to %s ",metalPart.toString(),out2.getName()));
        }
    }

}
