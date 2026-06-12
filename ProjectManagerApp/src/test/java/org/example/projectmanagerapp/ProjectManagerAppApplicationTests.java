package org.example.projectmanagerapp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {
        "spring.datasource.url=jdbc:postgresql://localhost:5432/project_manager",
        "spring.datasource.username=project_user",
        "spring.datasource.password=project_password",
        "spring.jpa.hibernate.ddl-auto=create-drop",
        "spring.jpa.show-sql=true",
        "spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect"
})
class ProjectManagerAppApplicationTests {

    @Test
    void contextLoads() {
    }

}
