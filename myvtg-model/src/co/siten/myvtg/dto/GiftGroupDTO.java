/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.siten.myvtg.dto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author buiquangdai
 */
public class GiftGroupDTO {

    private String groupId;
    private String groupName;
    private List<Object> listItem = new ArrayList<>();

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<Object> getListItem() {
        return listItem;
    }

    public void setListItem(List<Object> listItem) {
        this.listItem = listItem;
    }


    

}
