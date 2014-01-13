package cptl.metal;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by complexityclass on 1/9/14.
 */
public class MetalStorage {

    public static final double INDICATOR = 0.05;
    public static final int DAYS_IN_YEAR = 365;

    private int metalWeight;
    private OrderCase orderCase;
    private int currentTime;
    private List<Integer> orderCaseWeight;
    private List<Boolean> orderCaseCompleted;
    private List<Integer> orderCaseStart;
    private List<Integer> orderCaseEnd;
    private int totalWeight;
    private int totalStandardOrdersCount;
    private int totalExtraOrdersCount;
    private int totalCompleteOrderCasesCount;
    private int averageProcessingOrderTime;
    private int averageOrdersCountDaily;

    public MetalStorage(int metalWeight){
        orderCaseWeight = new ArrayList<Integer>();
        orderCaseCompleted = new ArrayList<Boolean>();
        orderCaseStart = new ArrayList<Integer>();
        orderCaseEnd = new ArrayList<Integer>();
        this.metalWeight = metalWeight;
        totalWeight = 0;
        totalStandardOrdersCount = 0;
        totalExtraOrdersCount = 0;
        totalCompleteOrderCasesCount = 0;
        averageOrdersCountDaily = 0;
        averageProcessingOrderTime = 0;
    }

    public void addOrderCase(int orderCase){
        orderCaseWeight.add(metalWeight);
        orderCaseCompleted.add(false);
        orderCaseStart.add(currentTime);
    }

    public void take(Metal metal){
        totalWeight += metal.getWeight();
        totalStandardOrdersCount++;
        int temp = metal.getCaseId();
        orderCaseWeight.set(temp,orderCaseWeight.get(temp) - metal.getWeight());
        averageProcessingOrderTime += currentTime - metal.getStartTime();
    }

    public void take(MetalPart metalPart){
        totalExtraOrdersCount++;
    }

    public String getName(){
        return "Metal Storage";
    }


    public void setOrderCase(OrderCase orderCase) {
        this.orderCase = orderCase;
    }

    public int getAverageProcessingOrderTime() {
        int temp = 0;
        for(int i = 0; i < orderCaseEnd.size();i++){
            temp += orderCaseEnd.get(i) - orderCaseStart.get(i);
        }

        return (int)((double)temp / (double)orderCaseStart.size());
    }

    public int getTotalWeight() {
        return totalWeight;
    }

    public int getTotalCompleteOrderCasesCount() {
        return totalCompleteOrderCasesCount;
    }

    public int getTotalStandardOrdersCount() {
        return totalStandardOrdersCount;
    }

    public int getTotalExtraOrdersCount() {
        return totalExtraOrdersCount;
    }

    public void iteration(){
        currentTime++;
        /*averageOrdersCountDaily += orderCase.*!!!!!!!!!!!!!!!!? */
        for(int i = 0; i < orderCaseWeight.size(); i++){
            if(orderCaseWeight.get(i) <= INDICATOR * metalWeight && orderCaseCompleted.get(i) == false ){
                orderCaseCompleted.set(i,true);
                totalCompleteOrderCasesCount++;
                orderCaseEnd.add(currentTime);
            }
        }
    }

    public double getAverageOrdersCountDaily(){
        return averageOrdersCountDaily / (double)DAYS_IN_YEAR;
    }


}













































