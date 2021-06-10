package io.example.repository;

import io.example.domain.dto.Page;
import io.example.domain.dto.SearchAuthorsQuery;
import io.example.domain.exception.NotFoundException;
import io.example.domain.model.Author;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.limit;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.lookup;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.skip;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.sort;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.unwind;

@Repository
public interface AuthorRepo extends MongoRepository<Author, ObjectId>, AuthorRepoCustom {

    default Author getById(ObjectId id) {
        return findById(id).orElseThrow(() -> new NotFoundException(Author.class, id));
    }

    List<Author> findAllById(Iterable<ObjectId> ids);

}

interface AuthorRepoCustom {

    List<Author> searchAuthors(Page page, SearchAuthorsQuery query);

}

@RequiredArgsConstructor
class AuthorRepoCustomImpl implements AuthorRepoCustom {

    private final MongoTemplate mongoTemplate;

    @Override
    public List<Author> searchAuthors(Page page, SearchAuthorsQuery query) {
        List<AggregationOperation> operations = new ArrayList<>();

        List<Criteria> criteriaList = new ArrayList<>();
        if (!StringUtils.isEmpty(query.getId())) {
            criteriaList.add(Criteria.where("id").is(new ObjectId(query.getId())));
        }
        if (!StringUtils.isEmpty(query.getCreatorId())) {
            criteriaList.add(Criteria.where("creatorId").is(new ObjectId(query.getCreatorId())));
        }
        if (query.getCreatedAtStart() != null) {
            criteriaList.add(Criteria.where("createdAt").gte(query.getCreatedAtStart()));
        }
        if (query.getCreatedAtEnd() != null) {
            criteriaList.add(Criteria.where("createdAt").lt(query.getCreatedAtEnd()));
        }
        if (!StringUtils.isEmpty(query.getFullName())) {
            criteriaList.add(Criteria.where("fullName").regex(query.getFullName(), "i"));
        }
        if (!CollectionUtils.isEmpty(query.getGenres())) {
            criteriaList.add(Criteria.where("genres").all(query.getGenres()));
        }
        if (!criteriaList.isEmpty()) {
            Criteria authorCriteria = new Criteria().andOperator(criteriaList.toArray(new Criteria[0]));
            operations.add(match(authorCriteria));
        }

        criteriaList = new ArrayList<>();
        if (!StringUtils.isEmpty(query.getBookId())) {
            criteriaList.add(Criteria.where("book._id").is(new ObjectId(query.getBookId())));
        }
        if (!StringUtils.isEmpty(query.getBookTitle())) {
            criteriaList.add(Criteria.where("book.title").regex(query.getBookTitle(), "i"));
        }
        if (!criteriaList.isEmpty()) {
            Criteria bookCriteria = new Criteria().andOperator(criteriaList.toArray(new Criteria[0]));
            operations.add(lookup("books", "bookIds", "_id", "book"));
            operations.add(unwind("book", false));
            operations.add(match(bookCriteria));
        }

        operations.add(sort(Sort.Direction.DESC, "createdAt"));
        operations.add(skip((page.getNumber() - 1) * page.getLimit()));
        operations.add(limit(page.getLimit()));

        TypedAggregation<Author> aggregation = newAggregation(Author.class, operations);
        AggregationResults<Author> results = mongoTemplate.aggregate(aggregation, Author.class);
        return results.getMappedResults();
    }
}