/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.viettel.bccs.cm.database.DAO.Webservice.Pr;

/**
 *
 * @author TUANTM2
 */
public class ProConfig {
    private String ip;
    private String port;
    private String username;
    private String password;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
