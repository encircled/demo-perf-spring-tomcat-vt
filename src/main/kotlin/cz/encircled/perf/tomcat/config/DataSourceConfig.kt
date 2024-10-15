package cz.encircled.perf.tomcat.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.datasource.DriverManagerDataSource
import javax.sql.DataSource


@Configuration
class DataSourceConfig {

    @Bean
    fun dataSource(@Value("\${SPRING_DATASOURCE_URL:jdbc:postgresql://localhost:5432/demo}") url: String): DataSource {
        val dataSource = DriverManagerDataSource()
        dataSource.setDriverClassName("org.postgresql.Driver")
        dataSource.url = url
        dataSource.username = "demo"
        dataSource.password = "demo"
        return dataSource
    }
}
