package io.example.repository;

import io.example.domain.dto.Page;
import io.example.domain.dto.SearchBooksQuery;
import io.example.domain.exception.NotFoundException;
import io.example.domain.model.Book;
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
public interface BookRepo extends MongoRepository<Book, ObjectId>, BookRepoCustom {

    default Book getById(ObjectId id) {
        return findById(id).orElseThrow(() -> new NotFoundException(Book.class, id));
    }

    List<Book> findAllById(Iterable<ObjectId> ids);

}

interface BookRepoCustom {

    List<Book> searchBooks(Page page, SearchBooksQuery query);

}

@RequiredArgsConstructor
class BookRepoCustomImpl implements BookRepoCustom {

    private final MongoTemplate mongoTemplate;

    @Override
    public List<Book> searchBooks(Page page, SearchBooksQuery query) {
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
        if (!StringUtils.isEmpty(query.getTitle())) {
            criteriaList.add(Criteria.where("title").regex(query.getTitle(), "i"));
        }
        if (!CollectionUtils.isEmpty(query.getGenres())) {
            criteriaList.add(Criteria.where("genres").all(query.getGenres()));
        }
        if (!StringUtils.isEmpty(query.getIsbn13())) {
            criteriaList.add(Criteria.where("isbn13").is(query.getIsbn13()));
        }
        if (!StringUtils.isEmpty(query.getIsbn10())) {
            criteriaList.add(Criteria.where("isbn10").is(query.getIsbn10()));
        }
        if (!StringUtils.isEmpty(query.getPublisher())) {
            criteriaList.add(Criteria.where("publisher").regex(query.getPublisher(), "i"));
        }
        if (query.getPublishDateStart() != null) {
            criteriaList.add(Criteria.where("publishDate").gte(query.getPublishDateStart()));
        }
        if (query.getPublishDateEnd() != null) {
            criteriaList.add(Criteria.where("publishDate").lt(query.getPublishDateEnd()));
        }
        if (!criteriaList.isEmpty()) {
            Criteria bookCriteria = new Criteria().andOperator(criteriaList.toArray(new Criteria[0]));
            operations.add(match(bookCriteria));
        }

        criteriaList = new ArrayList<>();
        if (!StringUtils.isEmpty(query.getAuthorId())) {
            criteriaList.add(Criteria.where("author._id").is(new ObjectId(query.getAuthorId())));
        }
        if (!StringUtils.isEmpty(query.getAuthorFullName())) {
            criteriaList.add(Criteria.where("author.fullName").regex(query.getAuthorFullName(), "i"));
        }
        if (!criteriaList.isEmpty()) {
            Criteria authorCriteria = new Criteria().andOperator(criteriaList.toArray(new Criteria[0]));
            operations.add(lookup("authors", "authorIds", "_id", "author"));
            operations.add(unwind("author", false));
            operations.add(match(authorCriteria));
        }

        operations.add(sort(Sort.Direction.DESC, "createdAt"));
        operations.add(skip((page.getNumber() - 1) * page.getLimit()));
        operations.add(limit(page.getLimit()));

        TypedAggregation<Book> aggregation = newAggregation(Book.class, operations);
        System.out.println(aggregation.toString());
        AggregationResults<Book> results = mongoTemplate.aggregate(aggregation, Book.class);
        return results.getMappedResults();
    }


}

