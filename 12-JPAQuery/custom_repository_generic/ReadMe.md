Mã nguồn demo cho bài viết [Bổ xung phương thức vào JPA repository / tham chiếu đến EntityManager trong Repository](https://techmaster.vn/posts/preview/36447/truy-cap-vao-entitymanager-voi-spring-data-jpa)


org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'personRepository' defined in vn.techmaster.custom_repository.repository.PersonRepository defined in @EnableJpaRepositories declared on JpaRepositoriesRegistrar.EnableJpaRepositoriesConfiguration: Invocation of init method failed; nested exception is java.lang.IllegalArgumentException: Failed to create query for method public abstract void vn.techmaster.custom_repository.repository.RepositoryCustom.refresh(java.lang.Object)! No property refresh found for type Person!

https://www.petrikainulainen.net/programming/spring-framework/spring-data-jpa-tutorial-adding-custom-methods-into-all-repositories/

https://stackoverflow.com/questions/41467894/no-property-found-for-type-custom-spring-data-repository