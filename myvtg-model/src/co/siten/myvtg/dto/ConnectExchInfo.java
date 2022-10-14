/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.siten.myvtg.dto;

/**
 *
 * @author daibq
 */
public class ConnectExchInfo {

    private String address;
    private String host;
    private Integer port;
    private String pass;
    private String user;
    private Integer receiverTimeOut;
    private Integer errorCountWarNing;
    private Integer syntheticMinutes;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Integer getReceiverTimeOut() {
        return receiverTimeOut;
    }

    public void setReceiverTimeOut(Integer receiverTimeOut) {
        this.receiverTimeOut = receiverTimeOut;
    }

    public Integer getErrorCountWarNing() {
        return errorCountWarNing;
    }

    public void setErrorCountWarNing(Integer errorCountWarNing) {
        this.errorCountWarNing = errorCountWarNing;
    }

    public Integer getSyntheticMinutes() {
        return syntheticMinutes;
    }

    public void setSyntheticMinutes(Integer syntheticMinutes) {
        this.syntheticMinutes = syntheticMinutes;
    }
    

}
