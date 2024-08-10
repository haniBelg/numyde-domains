package acl;

public class RelationQueryBuilder {
    private String resource;
    private String resourceId;
    private String permission;
    private String subject;
    private String subjectId;

    public RelationQueryBuilder resource(String resource, String id) {
        this.resource = resource;
        this.resourceId = id;
        return this;
    }

    public RelationQueryBuilder permission(String permission) {
        this.permission = permission;
        return this;
    }

    public RelationQueryBuilder subject(String subject, String id) {
        this.subject = subject;
        this.subjectId = id;
        return this;
    }

    public RelationQuery build() {
        return new RelationQuery(resource, resourceId, permission, subject, subjectId);
    }

}
