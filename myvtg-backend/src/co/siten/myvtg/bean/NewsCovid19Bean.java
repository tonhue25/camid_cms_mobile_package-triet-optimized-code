/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.siten.myvtg.bean;

import co.siten.myvtg.dto.NewsCovidDto;
import java.util.List;

/**
 * NewsCovid19Bean
 * 
 * @author phuonghc
 */
public class NewsCovid19Bean {
    private Long totalNews;
    private List<NewsCovidDto> newsCovid19DtoList;

    public NewsCovid19Bean() {
    }

    public NewsCovid19Bean(Long totalNews, List<NewsCovidDto> newsCovid19DtoList) {
        this.totalNews = totalNews;
        this.newsCovid19DtoList = newsCovid19DtoList;
    }

    public Long getTotalNews() {
        return totalNews;
    }

    public void setTotalNews(Long totalNews) {
        this.totalNews = totalNews;
    }

    public List<NewsCovidDto> getNewsCovid19DtoList() {
        return newsCovid19DtoList;
    }

    public void setNewsCovid19DtoList(List<NewsCovidDto> newsCovid19DtoList) {
        this.newsCovid19DtoList = newsCovid19DtoList;
    }
    
    
}
