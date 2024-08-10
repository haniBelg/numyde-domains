package acl;

public class LookupRelationQueryBuilder {
    private String resource;
    private String resourceId;
    private String permission;

    public LookupRelationQueryBuilder resource(String resource, String id) {
        this.resource = resource;
        this.resourceId = id;
        return this;
    }

    public LookupRelationQueryBuilder permission(String permission) {
        this.permission = permission;
        return this;
    }

    public LookupRelationQuery build() {
        return new LookupRelationQuery(resource, resourceId, permission);
    }

}
