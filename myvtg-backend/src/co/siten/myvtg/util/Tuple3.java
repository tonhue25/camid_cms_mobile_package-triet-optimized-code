/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.siten.myvtg.util;

/**
 *
 * @author LuatNC
 */
public class Tuple3<X,Y,Z> {
    X x;
    Y y;
    Z z;

    public Tuple3(X x, Y y, Z z) {
        this.x = x;
        this.y = y;
        this.z = z;
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
    
    public Z getField2() {
        return z;
    }

    public void setField2(Z z) {
        this.z = z;
    }
    
}
