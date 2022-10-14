/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.siten.myvtg.bean;

/**
 *
 * @author LuatNC
 */
public class ModelAppConfigWrapper {
    private String appConfig;

    public ModelAppConfigWrapper(String appConfig) {
        this.appConfig = appConfig;
    }

    public String getAppConfig() {
        return appConfig;
    }

    public void setAppConfig(String appConfig) {
        this.appConfig = appConfig;
    }
}
