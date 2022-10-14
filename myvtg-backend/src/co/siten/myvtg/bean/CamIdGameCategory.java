/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.siten.myvtg.bean;

import java.util.List;

/**
 * CamIdGameCategory
 *
 * @author partner7
 */
public class CamIdGameCategory {

    private Long id;
    private String status;
    private String name;
    private String name_km;
    private List<CamIdGame> games;

    public CamIdGameCategory() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName_km() {
        return name_km;
    }

    public void setName_km(String name_km) {
        this.name_km = name_km;
    }

    public List<CamIdGame> getGames() {
        return games;
    }

    public void setGames(List<CamIdGame> games) {
        this.games = games;
    }

}
