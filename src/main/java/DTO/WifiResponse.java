package DTO;

import com.google.gson.annotations.SerializedName;

import DAO.WifiPublicData;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WifiResponse {
    private WifiPublicData wifiPublicData;

    public WifiPublicData getWifiPublicData() {
        return wifiPublicData;
    }

    public void setWifiPublicData(WifiPublicData wifiPublicData) {
        this.wifiPublicData = wifiPublicData;
    }
}