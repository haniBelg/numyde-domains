package api;

import java.util.List;

public record Response<T>(T body, List<HttpAction> actions) {

    public static <T> Response<T> of(T body, HttpAction... actions) {
        return new Response<>(body, List.of(actions));
    }

    public static <T> Response<T> of(HttpAction... actions) {
        return new Response<>((T) null, List.of(actions));
    }
}
