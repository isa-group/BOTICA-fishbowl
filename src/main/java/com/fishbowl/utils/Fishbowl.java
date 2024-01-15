package com.fishbowl.utils;

public class Fishbowl {

    private String fishbowlState;

    public Fishbowl(String fishbowlState) {
        this.fishbowlState = fishbowlState;
    }

    public String getFishSilouette(Integer x, Integer y){
        String getYString = fishbowlState.split("\n")[y];
        return getYString.substring(x, x+1);
    }

    public void updateFishPosition(Integer x, Integer y, String fishSilouette){
        fishbowlState = fishbowlState.replace(fishSilouette, "-");
        String[] fishbowlLines = fishbowlState.split("\n");
        String getYString = fishbowlLines[y];
        String newYString = getYString.substring(0, x) + fishSilouette + getYString.substring(x+1);
        fishbowlLines[y] = newYString;
        this.fishbowlState = String.join("\n", fishbowlLines);
    }

    public String getFishbowl(){
        return fishbowlState;
    }


}
