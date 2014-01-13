package cptl.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by complexityclass on 1/9/14.
 */
public class CPTLLog {
    private String name;
    private List<String> messages;
    private int time;

    public CPTLLog(){
        name = null;
        messages = new ArrayList<String>();
        time = -1;
    }

    public CPTLLog(CPTLLog log){
        name = log.getName();
        messages = log.getMessages();
        time = log.getTime();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addMessage(String message){
        messages.add(message);
    }

    public List<String> getMessages(){
        return messages;
    }

    public void setTime(int time){
        this.time = time;
    }

    public boolean isMessagesInLog(){
        return  (messages.size() > 0) ? true : false;
    }

    public void clearLog(){
        messages.clear();
        time = -1;
    }

    public int getTime() {
        return time;
    }
}

