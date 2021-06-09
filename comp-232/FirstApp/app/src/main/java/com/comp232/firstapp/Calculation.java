package com.comp232.firstapp;

public class Calculation {

    private String num1;
    private String num2;
    private String operation;
    private String result;

    public Calculation(String num1, String num2, String operation, String result) {
        this.num1 = num1;
        this.num2 = num2;
        this.operation = operation;
        this.result = result;
    }

    public String getNum1() {
        return num1;
    }

    public void setNum1(String num1) {
        this.num1 = num1;
    }

    public String getNum2() {
        return num2;
    }

    public void setNum2(String num2) {
        this.num2 = num2;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "\n" + num1 + " " + operation + " " + num2 + " =" + " " + result;
    }
}
