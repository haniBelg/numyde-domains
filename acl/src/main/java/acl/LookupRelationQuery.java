package acl;

public record LookupRelationQuery(String resource,
        String resourceId,
        String permission) {
}
