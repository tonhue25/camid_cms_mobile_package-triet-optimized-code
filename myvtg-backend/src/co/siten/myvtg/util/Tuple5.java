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
public class Tuple5<X,Y,Z,V,T> {
    X x;
    Y y;
    Z z;
    V v;
    T t;

    public Tuple5(X x, Y y, Z z, V v, T t) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.v = v;
        this.t = t;
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
    
    public V getField3() {
        return v;
    }

    public void setField3(V v) {
        this.v = v;
    }
    
    public T getField4() {
        return t;
    }

    public void setField4(T t) {
        this.t = t;
    }
}
