package api;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import api.Input.InputBuilder;
import api.Setting.SettingBuilder;

public record Property(String name, List<Setting> settings, List<Property> properties) {
    public static class PropertyBuilder {
        String name;
        LinkedList<SettingBuilder> settingsBuilder;
        LinkedList<PropertyBuilder> downLevel;
        LinkedList<PropertyBuilder> myLevel;
        LinkedList<PropertyBuilder> upperLevel;
        InputBuilder inputBuilder;

        public PropertyBuilder(String name, LinkedList<PropertyBuilder> myLevel, LinkedList<PropertyBuilder> upperLevel,
                InputBuilder inputBuilder) {
            this.name = name;
            this.myLevel = myLevel;
            this.upperLevel = upperLevel;
            this.inputBuilder = inputBuilder;
        }

        public PropertyBuilder setting(String key, String value) {
            if (this.settingsBuilder == null) {
                this.settingsBuilder = new LinkedList<>();
            }
            SettingBuilder currentSettingBuilder = new SettingBuilder(inputBuilder);
            this.settingsBuilder.add(currentSettingBuilder.setting(key, value));
            return this;
        }

        public PropertyBuilder down(String name) {
            if (this.downLevel == null) {
                this.downLevel = new LinkedList<>();
            }
            PropertyBuilder currentPropertyBuilder = new PropertyBuilder(name, this.downLevel, this.myLevel,
                    this.inputBuilder);
            this.downLevel.add(currentPropertyBuilder);
            return currentPropertyBuilder;
        }

        public PropertyBuilder down() {
            if (this.downLevel == null) {
                return this;
            }
            return downLevel.getLast();
        }

        public PropertyBuilder up(String name) {
            return up().property(name);
        }

        public PropertyBuilder up() {
            return upperLevel.getLast();
        }

        public PropertyBuilder property(String name) {
            PropertyBuilder currentPropertyBuilder = new PropertyBuilder(name, this.myLevel, this.upperLevel,
                    this.inputBuilder);
            this.myLevel.add(currentPropertyBuilder);
            return currentPropertyBuilder;
        }

        public Property form() {
            List<Setting> settings = Optional.ofNullable(this.settingsBuilder)
                    .map(list -> list.stream().map(SettingBuilder::form).toList()).orElse(null);
            List<Property> properties = Optional.ofNullable(this.downLevel)
                    .map(list -> list.stream().map(PropertyBuilder::form).toList()).orElse(null);
            return new Property(name, settings, properties);
        }

        public Input build() {
            return this.inputBuilder.build();
        }

    }
}
