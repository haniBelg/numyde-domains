package api;

import java.util.List;

public record RootProperty(List<Setting> settings, List<Property> properties) {

}
