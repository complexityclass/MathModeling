package cptl.metal;

import cptl.utils.CPTLLog;
import cptl.utils.CPTLLogger;
import cptl.utils.CPTLQueue;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * Created by complexityclass on 1/11/14.
 */
public class Driver implements CPTLQueue {

    private Queue<Metal> out;
    private List<Metal> metalList;
    private List<Integer> metalPartsIntList;

    private boolean makeLog;
    private CPTLLog log;
    private CPTLLogger logger;

    public Driver(CPTLLogger logger, Queue<Metal> out){
        this.out = out;
        metalList = new ArrayList<Metal>();
        metalPartsIntList = new ArrayList<Integer>();

        this.logger = logger;
        this.makeLog = false;
        this.log = new CPTLLog();
    }


    @Override
    public void take(MetalPart metalPart) {
        for(int i = 0; i < metalList.size(); i++){
            if(metalList.get(i).equals(metalPart.getParent())){
                metalPartsIntList.set(i,metalPartsIntList.get(i) - 1);
                if(makeLog){
                    inLog(metalPart);
                }
            }
        }
    }

    public void iteration(){
        for(int i = 0; i < metalList.size(); i++){
            if(metalPartsIntList.get(i) == 0){
                outLog(metalList.get(i));
                out.add(new Metal(metalList.get(i)));
                metalList.remove(i);
                metalPartsIntList.remove(i);
            }
        }
        makeLog();
    }

    public Metal take(Metal metal, int n){
        metalList.add(new Metal(metal));
        metalPartsIntList.add(n);

        return metalList.get(metalList.size() - 1);
    }

    public void logOn(){
        makeLog = true;

    }

    public void logOff(){
        makeLog = false;
    }

    public void makeLog(){
        if(log.isMessagesInLog()){
            logger.writeLog(log);
            log.clearLog();
        }
    }

    public void inLog(MetalPart metalPart){
        if(makeLog){
            log.addMessage(String.format("App %s arrived",metalPart.toString()));
        }
    }

    public void outLog(Metal metal){
        if(makeLog){
            log.addMessage(String.format("App %s collected",metal.toString()));
        }
    }

    @Override
    public String getName() {
        return log.getName();
    }

    public void setName(String name){
        log.setName(name);
    }


}
