package gestores;

import org.json.JSONObject;
import utiles.JsonFactory;
import utiles.JsonSerializable;

public class GestorJson {
    public static JSONObject toJson(JsonSerializable obj) {
        return obj.toJson();
    }

    public static <T> T fromJson(JSONObject json, JsonFactory<T> factory) {
        return factory.fromJson(json);
    }
}

