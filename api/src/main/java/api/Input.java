package api;

import api.Body.BodyBuilder;
import static api.JsonUtil.convertToJson;
import api.Property.PropertyBuilder;

public record Input(Body body) {

    public static InputBuilder builder() {
        return new InputBuilder();
    }
    public static class InputBuilder {
        BodyBuilder bodyBuilder;

        public PropertyBuilder body(String name) {
            if (this.bodyBuilder == null) {
                this.bodyBuilder = new BodyBuilder(this);
            }
            return bodyBuilder.property(name);
        }

        public Input build() {
            return new Input(this.bodyBuilder.form());
        }
    }

    public String json() {
        return convertToJson(this);
    }
}
