package com.fishbowl.utils;

public class FishTank {

    private String tank;

    public FishTank(String tank) {
        this.tank = tank;
    }

    public String getFishSilouette(Integer x, Integer y){
        String getYString = tank.split("\n")[y];
        return getYString.substring(x, x+1);
    }

    public void updateFishPosition(Integer x, Integer y, String fishSilouette){
        tank = tank.replace(fishSilouette, "-");
        String[] tankLines = tank.split("\n");
        String getYString = tankLines[y];
        String newYString = getYString.substring(0, x) + fishSilouette + getYString.substring(x+1);
        tankLines[y] = newYString;
        this.tank = String.join("\n", tankLines);
    }

    public String getFishTank(){
        return tank;
    }


}
