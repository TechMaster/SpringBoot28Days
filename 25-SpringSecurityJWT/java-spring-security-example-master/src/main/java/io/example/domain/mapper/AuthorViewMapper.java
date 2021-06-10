package io.example.domain.mapper;

import io.example.domain.dto.AuthorView;
import io.example.domain.model.Author;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring", uses = ObjectIdMapper.class)
public abstract class AuthorViewMapper {

    private UserViewMapper userViewMapper;

    @Autowired
    public void setUserViewMapper(UserViewMapper userViewMapper) {
        this.userViewMapper = userViewMapper;
    }

    public abstract AuthorView toAuthorView(Author author);

    public abstract List<AuthorView> toAuthorView(List<Author> authors);

    @AfterMapping
    protected void after(Author author, @MappingTarget AuthorView authorView) {
        authorView.setCreator(userViewMapper.toUserViewById(author.getCreatorId()));
    }

}
