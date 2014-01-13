package cptl.utils;

import cptl.metal.MetalPart;

/**
 * Created by complexityclass on 1/9/14.
 */
public interface CPTLQueue {
    void take(MetalPart metalPart);
    String getName();
}
