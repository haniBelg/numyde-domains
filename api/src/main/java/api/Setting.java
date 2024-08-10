package api;

import api.Input.InputBuilder;

public record Setting(String key, String value) {
    public static class SettingBuilder {
        private String key;
        private String value;
        InputBuilder inputBuilder;

        public SettingBuilder(InputBuilder inputBuilder) {
            this.inputBuilder = inputBuilder;
        }

        public SettingBuilder setting(String key, String value) {
            this.key = key;
            this.value = value;
            return this;
        }

        public Input build() {
            return this.inputBuilder.build();
        }

        public Setting form() {
            return new Setting(key, value);
        }
    }
}
