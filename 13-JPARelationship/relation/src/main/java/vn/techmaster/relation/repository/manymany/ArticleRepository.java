package vn.techmaster.relation.repository.manymany;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.techmaster.relation.model.manymany.noextracolumns.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {
  
}
