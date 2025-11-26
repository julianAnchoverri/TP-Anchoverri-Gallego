package utiles;
import org.json.JSONObject;

@FunctionalInterface
public interface JsonFactory<T> {
    T fromJson(JSONObject json);
}
