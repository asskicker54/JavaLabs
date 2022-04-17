package data;

import java.io.Serializable;

public class Data implements Serializable {
    private double a;
    private double b;
    private String operator;

    public Data(double a, double b, String operator) {
        this.a = a;
        this.b = b;
        this.operator = operator;
    }

    public Data() {
        a = 0;
        b = 0;
        operator = "+";
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public String getOperator() {
        return operator;
    }

    public void setA(double a) {
        this.a = a;
    }

    public void setB(double b) {
        this.b = b;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
}
