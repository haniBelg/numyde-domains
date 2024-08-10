package api;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class JsonUtil {
    public static String convertToJson(Object obj) {
        if (obj == null) {
            return "null";
        }
        if (obj instanceof String) {
            return "\"" + obj + "\"";
        }
        if (obj instanceof List<?>) {
            StringBuilder json = new StringBuilder("[");
            boolean first = true;
            for (Object item : (List<?>) obj) {
                if (!first) {
                    json.append(", ");
                }
                json.append(convertToJson(item));
                first = false;
            }
            json.append("]");
            return json.toString();
        }
        if (obj.getClass().isRecord()) {
            StringBuilder json = new StringBuilder("{");
            boolean first = true;
            for (var field : obj.getClass().getRecordComponents()) {
                try {
                    Object value = field.getAccessor().invoke(obj);
                    if (value == null) {
                        continue;
                    }
                    if (!first) {
                        json.append(", ");
                    }
                    json.append("\"").append(field.getName()).append("\": ");
                    json.append(convertToJson(value));
                    first = false;
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
            json.append("}");
            return json.toString();
        }
        throw new IllegalArgumentException("Unsupported type: " + obj.getClass().getName());
    }

}
