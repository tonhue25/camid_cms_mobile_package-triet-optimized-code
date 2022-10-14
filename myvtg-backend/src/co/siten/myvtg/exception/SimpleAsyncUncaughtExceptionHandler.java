/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.siten.myvtg.exception;

import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;

/**
 *
 * @author LuatNC
 */
public class SimpleAsyncUncaughtExceptionHandler implements AsyncUncaughtExceptionHandler {

    @Override
    public void handleUncaughtException(Throwable thrwbl, Method method, Object... os) {
        try {
            throw thrwbl;
        } catch (Throwable ex) {
            Logger.getLogger(SimpleAsyncUncaughtExceptionHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
