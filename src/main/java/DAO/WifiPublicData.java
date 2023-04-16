package DAO;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import DTO.WifiDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WifiPublicData {
    @SerializedName("list_total_count")
    private int totalCount;

    @SerializedName("row")
    private List<WifiDTO> wifiList;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<WifiDTO> getWifiList() {
        return wifiList;
    }

    public void setWifiList(List<WifiDTO> wifiList) {
        this.wifiList = wifiList;
    }
}
