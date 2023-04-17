package API;

/*
    @author Nayoon
 */

import DTO.WifiDTO;
import DTO.WifiResponse;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ApiDeserializer implements JsonDeserializer<WifiResponse> {
    @Override
    public WifiResponse deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject responseBody = jsonElement.getAsJsonObject();
        JsonObject tbPublicWifiInfo = jsonElement.getAsJsonObject().getAsJsonObject(ApiConfig.SERVICE);
        int list_total_count = tbPublicWifiInfo.get("list_total_count").getAsInt();
        JsonArray row = tbPublicWifiInfo.get("row").getAsJsonArray();
        ArrayList<WifiDTO> list = new ArrayList<>();

        for (JsonElement element : row) {
            WifiDTO wifiDTO = new Gson().fromJson(element.getAsJsonObject(), WifiDTO.class);
            list.add(wifiDTO);
        }

        return new WifiResponse(list_total_count, list);
    }
}
