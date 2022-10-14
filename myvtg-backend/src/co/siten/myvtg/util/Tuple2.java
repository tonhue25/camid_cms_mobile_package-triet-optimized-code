/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.siten.myvtg.util;

/**
 *
 * @author nguye
 */
public class Tuple2<X,Y> {
    X x;
    Y y;

    public Tuple2(X x, Y y) {
        this.x = x;
        this.y = y;
    }
    
    public X getField0() {
        return x;
    }

    public void setField0(X x) {
        this.x = x;
    }

    public Y getField1() {
        return y;
    }

    public void setField1(Y y) {
        this.y = y;
    }
}
