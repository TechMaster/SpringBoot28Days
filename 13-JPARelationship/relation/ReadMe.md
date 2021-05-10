# Các loại quan hệ trong JPA

1. One to One
2. One to Many và Many to One
3. Many to Many
4. Self Reference
5. Inheritance

Sự khác nhau giữa Uni-direction vs Bidirection

Relationships may be bidirectional or unidirectional.
A bidirectional relationship has both an owning side and an inverse side
A unidirectional relationship only has an owning side.

The inverse side has to have the mappedBy attribute of the OneToOne, OneToMany, or ManyToMany mapping declaration. The mappedBy attribute contains the name of the association-field on the owning side.
The owning side has to have the inversedBy attribute of the OneToOne, ManyToOne, or ManyToMany mapping declaration.
The inversedBy attribute contains the name of the association-field on the inverse-side.
ManyToOne is always the owning side of a bidirectional association.
OneToMany is always the inverse side of a bidirectional association.
The owning side of a OneToOne association is the entity with the table containing the foreign key.
You can pick the owning side of a many-to-many association yourself.

https://www.baeldung.com/jpa-joincolumn-vs-mappedby

Quan hệ Unidirection sẽ khó xử lý.


https://thorben-janssen.com/hibernate-tip-many-to-many-association-with-additional-attributes/


https://medium.com/@udith.indrakantha/issue-related-with-infinite-recursive-fetching-of-data-from-relationships-between-entity-classes-ffc5fac6c816

https://www.baeldung.com/hibernate-inheritance
https://thorben-janssen.com/complete-guide-inheritance-strategies-jpa-hibernate/

