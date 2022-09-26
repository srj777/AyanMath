package com.example.ayanmath;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AdditionSingleDigit implements Serializable {
    private int operand1;
    private int operand2;
    private String operator = "+";
    private int result;
    private int questionCounter;
    private int totalQuestions;
    private int gameState; // not_started=0, in_game=1, complete=2
    Integer[] optionArray = new Integer[6];

    public AdditionSingleDigit(){
        this.gameState=0;
        this.totalQuestions=10;
        this.questionCounter=0;
    }

    public void resetGame(){
        this.questionCounter=0;
        this.gameState=0;
    }

    public void play(){
        int max=10;
        int min=1;

        this.operand1 = (int)(Math.random()*(max-min+1)+min);
        this.operand2 = (int)(Math.random()*(max-min+1)+min);

        this.result=this.operand1 + this.operand2;
        this.questionCounter = this.questionCounter + 1;
        populateOptionArray();

    }

    private void populateOptionArray(){
        int max=7;
        int min=1;
        this.optionArray[0] = this.result;

        for(int i=1;i<6;i++){
            int tempAdd = (int)(Math.random()*(max-min+1)+min);
            optionArray[i] = this.result + tempAdd;
        }
        List<Integer> intList = Arrays.asList(this.optionArray);
        Collections.shuffle(intList);
        intList.toArray(this.optionArray);
    }

    public Integer[] getOptionArray(){
        return this.optionArray;
    }

    public int getOperand1(){
        return this.operand1;
    }

    public int getOperand2(){
        return this.operand2;
    }

    public int getResult(){
        return this.result;
    }

    public String getOperator(){
        return this.operator;
    }

    public boolean validateResult(int value){
        boolean ret = false;
        if(value == this.result){
            return true;
        }
        return ret;
    }

    public int getQuestionCounter(){
        return this.questionCounter;
    }

    public int getTotalQuestions(){
        return this.totalQuestions;
    }

}
