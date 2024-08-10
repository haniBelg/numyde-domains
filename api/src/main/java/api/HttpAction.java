package api;

public record HttpAction(
        String name,
        Method method,
        String path,
        Input input,
        Boolean isDefault) {
}
