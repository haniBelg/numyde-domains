package acl;

import java.util.List;
import java.util.function.Function;

public interface RelationProvider {
    Boolean assertTrue(Function<RelationQueryBuilder, RelationQueryBuilder> assertionBuilderFunction);

    List<SubjectResult> lookup(Function<LookupRelationQueryBuilder, LookupRelationQueryBuilder> queryBuilderFunction);

    Boolean add(Function<RelationQueryBuilder, RelationQueryBuilder> queryBuilderFunction);

    Boolean remove(Function<RelationQueryBuilder, RelationQueryBuilder> queryBuilderFunction);
}
