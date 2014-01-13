package cptl.utils;

import cptl.enums.DistributionType;

import java.util.Random;

/**
 * Created by complexityclass on 1/9/14.
 */
public class RandomGenerator {

    private DistributionType distributionType;
    private Random random;

    public RandomGenerator(DistributionType type){
        this.random = new Random();
        this.distributionType = type;
    }

    public int getNextIntRandom(int leftRange, int rightRange){
        return  this.random.nextInt((rightRange - leftRange) + 1) + leftRange;
    }

    public double getNextDoubleRandom(double leftRange, double rightRange){
        return leftRange + (rightRange - leftRange) * this.random.nextDouble();
    }

    public double generatorM (double mx, double s){
        double square = 0;
        double x = 0;
        double y = 0;
        while(square == 0 || square > 1){
            x = random.nextDouble() * 2 - 1;
            y = random.nextDouble() * 2 - 1;
            square = x * x + y * y;
        }
        double z = x * Math.sqrt(-2 * Math.log(square) / square);

        return mx + s * z;
    }

    public int expDistributionNextInt(double lambda){
        return (int)(-Math.log(random.nextDouble())/(lambda/24/60));
    }



}
