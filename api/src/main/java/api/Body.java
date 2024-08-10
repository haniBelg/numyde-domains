package api;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import api.Input.InputBuilder;
import api.Property.PropertyBuilder;
import api.Setting.SettingBuilder;

public record Body(List<Setting> settings, List<Property> properties) {
    public static class BodyBuilder {
        LinkedList<SettingBuilder> settingsBuilder;
        LinkedList<PropertyBuilder> propertiesBuilder;
        InputBuilder inputBuilder;

        public BodyBuilder(InputBuilder inputBuilder) {
            this.inputBuilder = inputBuilder;
        }

        public PropertyBuilder property(String name) {
            if (this.propertiesBuilder == null) {
                this.propertiesBuilder = new LinkedList<>();
            }
            PropertyBuilder currenPropertyBuilder = new PropertyBuilder(name, this.propertiesBuilder,
                    this.propertiesBuilder,
                    this.inputBuilder);
            this.propertiesBuilder.add(currenPropertyBuilder);
            return currenPropertyBuilder;
        }

        public BodyBuilder setting(String key, String value) {
            if (this.settingsBuilder == null) {
                this.settingsBuilder = new LinkedList<>();
            }
            SettingBuilder currentSettingBuilder = new SettingBuilder(this.inputBuilder);
            this.settingsBuilder.add(currentSettingBuilder.setting(key, value));
            return this;
        }

        public Input build() {
            return this.inputBuilder.build();
        }

        public Body form() {
            List<Setting> settings = Optional.ofNullable(this.settingsBuilder)
                    .map(list -> list.stream().map(SettingBuilder::form).toList()).orElse(null);
            List<Property> properties = Optional.ofNullable(this.propertiesBuilder)
                    .map(list -> list.stream().map(PropertyBuilder::form).toList()).orElse(null);
            return new Body(settings, properties);
        }

    }
}
