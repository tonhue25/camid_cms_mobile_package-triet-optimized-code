/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.siten.myvtg.bean;

import java.math.BigDecimal;

/**
 *
 * @author LuatNC
 */
public class AreaLongLatBean {

        private BigDecimal longitude;
        private BigDecimal latitude;

        public AreaLongLatBean(BigDecimal longitude, BigDecimal latitude) {
                this.longitude = longitude;
                this.latitude = latitude;
        }

        public BigDecimal getLongitude() {
                return longitude;
        }

        public void setLongitude(BigDecimal longitude) {
                this.longitude = longitude;
        }

        public BigDecimal getLatitude() {
                return latitude;
        }

        public void setLatitude(BigDecimal latitude) {
                this.latitude = latitude;
        }
}
