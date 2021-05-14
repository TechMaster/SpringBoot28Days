

```
-[           main] o.h.t.s.i.ExceptionHandlerLoggedImpl     : GenerationTarget encountered exception accepting command : Error executing DDL "alter table employee add constraint FK39s2j5kxvqgi1gva4f800kk77 foreign key (id) references employee (id)" via JDBC Statement
-
org.hibernate.tool.schema.spi.CommandAcceptanceException: Error executing DDL "alter table employee add constraint FK39s2j5kxvqgi1gva4f800kk77 foreign key (id) references employee (id)" via JDBC Statement
        at org.hibernate.tool.schema.internal.exec.GenerationTargetToDatabase.accept(GenerationTargetToDatabase.java:67) ~[hibernate-core-5.4.30.Final.jar:5.4.30.Final]
        at org.hibernate.tool.schema.internal.AbstractSchemaMigrator.applySqlString(AbstractSchemaMigrator.java:559) ~[hibernate-core-5.4.30.Final.jar:5.4.30.Final]
        at org.hibernate.tool.schema.internal.AbstractSchemaMigrator.applySqlStrings(AbstractSchemaMigrator.java:504) ~[hibernate-core-5.4.30.Final.jar:5.4.30.Final]
        at org.hibernate.tool.schema.internal.AbstractSchemaMigrator.applyForeignKeys(AbstractSchemaMigrator.java:433) ~[hibernate-core-5.4.30.Final.jar:5.4.30.Final]
        at org.hibernate.tool.schema.internal.AbstractSchemaMigrator.performMigration(AbstractSchemaMigrator.java:249) ~[hibernate-core-5.4.30.Final.jar:5.4.30.Final]
        at org.hibernate.tool.schema.internal.AbstractSchemaMigrator.doMigration(AbstractSchemaMigrator.java:114) ~[hibernate-core-5.4.30.Final.jar:5.4.30.Final]
        at org.hibernate.tool.schema.spi.SchemaManagementToolCoordinator.performDatabaseAction(SchemaManagementToolCoordinator.java:184) ~[hibernate-core-5.4.30.Final.jar:5.4.30.Final]
        at org.hibernate.tool.schema.spi.SchemaManagementToolCoordinator.process(SchemaManagementToolCoordinator.java:73) ~[hibernate-core-5.4.30.Final.jar:5.4.30.Final]
        at org.hibernate.internal.SessionFactoryImpl.<init>(SessionFactoryImpl.java:318) ~[hibernate-core-5.4.30.Final.jar:5.4.30.Final]
        at org.hibernate.boot.internal.SessionFactoryBuilderImpl.build(SessionFactoryBuilderImpl.java:468) ~[hibernate-core-5.4.30.Final.jar:5.4.30.Final]
        at org.hibernate.jpa.boot.internal.EntityManagerFactoryBuilderImpl.build(EntityManagerFactoryBuilderImpl.java:1259) ~[hibernate-core-5.4.30.Final.jar:5.4.30.Final]
        at org.springframework.orm.jpa.vendor.SpringHibernateJpaPersistenceProvider.createContainerEntityManagerFactory(SpringHibernateJpaPersistenceProvider.java:58) ~[spring-orm-5.3.6.jar:5.3.6]
        at org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean.createNativeEntityManagerFactory(LocalContainerEntityManagerFactoryBean.java:365) ~[spring-orm-5.3.6.jar:5.3.6]
        at org.springframework.orm.jpa.AbstractEntityManagerFactoryBean.buildNativeEntityManagerFactory(AbstractEntityManagerFactoryBean.java:409) ~[spring-orm-5.3.6.jar:5.3.6]
        at org.springframework.orm.jpa.AbstractEntityManagerFactoryBean.afterPropertiesSet(AbstractEntityManagerFactoryBean.java:396) ~[spring-orm-5.3.6.jar:5.3.6]
        at org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean.afterPropertiesSet(LocalContainerEntityManagerFactoryBean.java:341) ~[spring-orm-5.3.6.jar:5.3.6]
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.invokeInitMethods(AbstractAutowireCapableBeanFactory.java:1845) ~[spring-beans-5.3.6.jar:5.3.6]
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.initializeBean(AbstractAutowireCapableBeanFactory.java:1782) ~[spring-beans-5.3.6.jar:5.3.6]
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:602) ~[spring-beans-5.3.6.jar:5.3.6]
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:524) ~[spring-beans-5.3.6.jar:5.3.6]
        at org.springframework.beans.factory.support.AbstractBeanFactory.lambda$doGetBean$0(AbstractBeanFactory.java:335) ~[spring-beans-5.3.6.jar:5.3.6]
        at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:234) ~[spring-beans-5.3.6.jar:5.3.6]
        at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:333) ~[spring-beans-5.3.6.jar:5.3.6]
        at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:208) ~[spring-beans-5.3.6.jar:5.3.6]
        at org.springframework.context.support.AbstractApplicationContext.getBean(AbstractApplicationContext.java:1154) ~[spring-context-5.3.6.jar:5.3.6]
        at org.springframework.context.support.AbstractApplicationContext.finishBeanFactoryInitialization(AbstractApplicationContext.java:908) ~[spring-context-5.3.6.jar:5.3.6]
        at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:583) ~[spring-context-5.3.6.jar:5.3.6]
        at org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext.refresh(ServletWebServerApplicationContext.java:144) ~[spring-boot-2.4.5.jar:2.4.5]
        at org.springframework.boot.SpringApplication.refresh(SpringApplication.java:782) ~[spring-boot-2.4.5.jar:2.4.5]
        at org.springframework.boot.SpringApplication.refresh(SpringApplication.java:774) ~[spring-boot-2.4.5.jar:2.4.5]
        at org.springframework.boot.SpringApplication.refreshContext(SpringApplication.java:439) ~[spring-boot-2.4.5.jar:2.4.5]
        at org.springframework.boot.SpringApplication.run(SpringApplication.java:339) ~[spring-boot-2.4.5.jar:2.4.5]
        at org.springframework.boot.SpringApplication.run(SpringApplication.java:1340) ~[spring-boot-2.4.5.jar:2.4.5]
        at org.springframework.boot.SpringApplication.run(SpringApplication.java:1329) ~[spring-boot-2.4.5.jar:2.4.5]
        at vn.techmaster.relation.RelationApplication.main(RelationApplication.java:10) ~[classes/:na]
Caused by: java.sql.SQLException: Failed to add the foreign key constraint. Missing index for constraint 'FK39s2j5kxvqgi1gva4f800kk77' in the referenced table 'employee'
        at com.mysql.cj.jdbc.exceptions.SQLError.createSQLException(SQLError.java:129) ~[mysql-connector-java-8.0.25.jar:8.0.25]
        at com.mysql.cj.jdbc.exceptions.SQLExceptionsMapping.translateException(SQLExceptionsMapping.java:122) ~[mysql-connector-java-8.0.25.jar:8.0.25]
        at com.mysql.cj.jdbc.StatementImpl.executeInternal(StatementImpl.java:762) ~[mysql-connector-java-8.0.25.jar:8.0.25]
        at com.mysql.cj.jdbc.StatementImpl.execute(StatementImpl.java:646) ~[mysql-connector-java-8.0.25.jar:8.0.25]
        at com.zaxxer.hikari.pool.ProxyStatement.execute(ProxyStatement.java:95) ~[HikariCP-3.4.5.jar:na]
        at com.zaxxer.hikari.pool.HikariProxyStatement.execute(HikariProxyStatement.java) ~[HikariCP-3.4.5.jar:na]
        at org.hibernate.tool.schema.internal.exec.GenerationTargetToDatabase.accept(GenerationTargetToDatabase.java:54) ~[hibernate-core-5.4.30.Final.jar:5.4.30.Final]
        ... 34 common frames omitted
```

Hãy chú ý
```
Caused by: java.sql.SQLException: Failed to add the foreign key constraint. Missing index for constraint 'FK39s2j5kxvqgi1gva4f800kk77' in the referenced table 'employee'
```