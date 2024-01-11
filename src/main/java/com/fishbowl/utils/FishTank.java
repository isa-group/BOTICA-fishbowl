package com.fishbowl.utils;

public class FishTank {
    private char[][] tank;

    public FishTank() {
        this.tank = new char[9][9];
        initializeTank();
    }

    private void initializeTank() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                tank[i][j] = '-';
            }
        }
    }

    public void updateFishPosition(int x, int y, String fishSilouette) {

        for (int i = 0; i < tank.length; i++) {
            for (int j = 0; j < tank[i].length; j++) {
                if (tank[i][j] == fishSilouette.charAt(0)) {
                    tank[i][j] = '-';
                }
            }
        }

        tank[x][y] = fishSilouette.charAt(0);
    }

    public String getFishTank() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tank.length; i++) {
            for (int j = 0; j < tank[i].length; j++) {
                sb.append(tank[i][j]);
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public String getFishSilouette(int x, int y) {
        return String.valueOf(tank[x][y]);
    }

}
