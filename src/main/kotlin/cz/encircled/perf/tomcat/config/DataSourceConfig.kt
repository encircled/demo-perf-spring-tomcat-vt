package cz.encircled.perf.tomcat.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.datasource.DriverManagerDataSource
import org.testcontainers.containers.PostgreSQLContainer
import javax.sql.DataSource


@Configuration
class DataSourceConfig() {

    @Bean
    fun postgresContainer(): PostgreSQLContainer<*> {
        val container: PostgreSQLContainer<*> = PostgreSQLContainer("postgres:15-alpine")
            .withDatabaseName("demo")
            .withUsername("demo")
            .withPassword("demo")
            .withCommand("postgres", "-c", "max_connections=200");

        container.start()
        return container
    }

    @Bean
    fun dataSource(): DataSource {
        val postgres = postgresContainer()
        val dataSource = DriverManagerDataSource()
        dataSource.setDriverClassName("org.postgresql.Driver")
        dataSource.url = postgres.jdbcUrl
        dataSource.username = postgres.username
        dataSource.password = postgres.password
        return dataSource
    }
}
