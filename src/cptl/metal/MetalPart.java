package cptl.metal;

import com.sun.swing.internal.plaf.metal.resources.metal;
import cptl.enums.OrderType;
import cptl.enums.RoastingType;

/**
 * Created by complexityclass on 1/6/14.
 */
public class MetalPart {

    private Metal parent;
    private int partNumber;
    private int maxNumber;
    private int needMuffleCount;
    private int muffleCount;
    private RoastingType roastingType;
    private OrderType orderType;

    public MetalPart(Metal metal,int number, int maxNumber){
        this.parent = metal;
        this.partNumber = number;
        this.maxNumber = maxNumber;
        this.roastingType = RoastingType.CloseRoasting;
        this.orderType = OrderType.Standard;
        this.needMuffleCount = 0;
        this.muffleCount = 0;
    }

    public MetalPart(MetalPart metalPart){
        this.parent = metalPart.getParent();
        this.roastingType = metalPart.getRoastingType();
        this.partNumber = metalPart.getPartNumber();
        this.maxNumber = metalPart.getMaxNumber();
        this.needMuffleCount = metalPart.getNeedMuffleCount();
        this.muffleCount = metalPart.muffleCount;
    }


    public void makeAppExternal(){
        this.orderType = OrderType.External;
    }

    public void addMuffle(int muffleToAdd){
        this.muffleCount += muffleToAdd;
    }

    public int getMuffleCount(){ return muffleCount; };

    public OrderType getOrderType() { return orderType; }

    public Metal getParent() { return parent; }

    public int getPartNumber() { return partNumber; }

    public int getMaxNumber() { return maxNumber; }

    public RoastingType getRoastingType() { return roastingType; }
    public void setRoastingType(RoastingType roastingType) { this.roastingType = roastingType; }

    public int getNeedMuffleCount() { return needMuffleCount; }
    public void setNeedMuffleCount(int needMuffleCount) { this.needMuffleCount = needMuffleCount; }

    @Override
    public boolean equals(Object obj) {
        if((this.partNumber == ((MetalPart) obj).getPartNumber()) &&
           (this.muffleCount == ((MetalPart) obj).getMuffleCount())&&
           (this.orderType == ((MetalPart) obj).getOrderType())){

            return true;

        }else{
            return false;
        }

    }
}
