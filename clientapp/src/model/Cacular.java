package model;

import java.io.Serializable;

public class Cacular implements Serializable {
    private static final long serialVersionUID = -6500665823330706018L;
    public int aNumber;
    public int bNumber;
    public char kt;
    public double rs;
    public String err;
    public Cacular(int a, int b, char c) {
        aNumber=a;
        bNumber=b;
        kt=c;
    }
    public Cacular(double rs, String err) {
        this.rs=rs;
        this.err=err;
    }


    public void setaNumber(int aNumber) {
        this.aNumber = aNumber;
    }

    public int getbNumber() {
        return bNumber;
    }

    public void setbNumber(int bNumber) {
        this.bNumber = bNumber;
    }

    public char getKt() {
        return kt;
    }

    public void setKt(char kt) {
        this.kt = kt;
    }

    public double getRs() {
        return rs;
    }

    public void setRs(double rs) {
        this.rs = rs;
    }

    public String getErr() {
        return err;
    }

    public void setErr(String err) {
        this.err = err;
    }

    @Override
    public String toString() {
        return "cacular{" +
                "aNumber=" + aNumber +
                ", bNumber=" + bNumber +
                ", kt=" + kt +
                ", rs=" + rs +
                ", err='" + err + '\'' +
                '}';
    }
}
