package cptl.bath;

import cptl.metal.MetalPart;
import cptl.utils.CPTLLog;
import cptl.utils.CPTLLogger;
import cptl.utils.CPTLQueue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by complexityclass on 1/9/14.
 */
public class ChemicalDistrQueue implements CPTLQueue {

    private Queue<MetalPart> queue;
    private List<Bath> baths;
    private boolean isBlocked;

    private boolean makeLog;
    private CPTLLogger logger;
    private CPTLLog log;

    public ChemicalDistrQueue(CPTLLogger logger){
        this.logger = logger;
        makeLog = false;
        log = new CPTLLog();

        queue = new LinkedList<MetalPart>();
        baths = new ArrayList<Bath>();
        isBlocked = false;
    }

    public void addBath(Bath bath){
        baths.add(bath);
    }

    @Override
    public void take(MetalPart metalPart) {

        if(makeLog){ inLog(metalPart); }

        queue.add(new MetalPart(metalPart));

    }

    public void block(){
        isBlocked = true;
    }

    public void unblock(){
        isBlocked = false;
    }

    public int getLength(){
        return queue.size();
    }

    public void iteration(){
        if(!isBlocked){
            for(int i = 0; i < baths.size(); i++){
                if(queue.size() == 0){
                    break;
                }
                if(baths.get(i).isAvailable()){
                    MetalPart metalPart = queue.poll();
                    outLog(metalPart, baths.get(i));
                    baths.get(i).take(metalPart);
                }
            }
        }
        makeLog();
    }

    public void logOn(){
        makeLog = true;
    }

    public void logOff(){
        makeLog = false;
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
            log.addMessage(String.format("App %s in queue",metalPart.toString()));
        }
    }

    public void outLog(MetalPart metalPart, Bath bath){
        if(makeLog){
            log.addMessage(String.format("App %s send to %s, %d apps in queue",metalPart.toString(),
                    bath.getName(),queue.size()));
        }
    }
}
