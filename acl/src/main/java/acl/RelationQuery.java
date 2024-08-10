package acl;

public record RelationQuery(String resource,
        String resourceId,
        String permission,
        String subject,
        String subjectId) {
}
