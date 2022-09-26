package com.example.ayanmath;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GameScore implements Serializable {

    int totalScore = 0;
    List<GameResults> list;

    GameScore(){
        list = new ArrayList<GameResults>();
    }

    public void addGame(GameResults gr){
        list.add(gr);
    }

    public void resetScore(){
        list.clear();
    }

    public List<GameResults> getList(){
        return this.list;
    }

    public int getTotalScore(){
        int totalTime = 0;
        int countCorrect=0;
        int totalScore=0;
        for (GameResults gr:list){
            if(gr.getResult()){
                totalTime = totalTime + gr.getTimeTaken();
                countCorrect++;
            }
        }

        totalScore = (10*countCorrect) + (int)(((2000-(int)(totalTime/countCorrect))/8000.0)*100.0) ;
        return totalScore;
    }
}
