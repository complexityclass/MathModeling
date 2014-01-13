package cptl.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by complexityclass on 1/9/14.
 */
public class CPTLLogger {
    private List<CPTLLog> logs;
    private int currentTime;

    public CPTLLogger(){
        logs = new ArrayList<CPTLLog>();
        currentTime = 0;
    }

    public void writeLog(CPTLLog log){
        log.setTime(currentTime);
        logs.add(new CPTLLog(log));
    }

    public void iteration(){
        currentTime++;
    }
}
