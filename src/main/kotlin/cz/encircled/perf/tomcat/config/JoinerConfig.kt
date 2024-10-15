package cz.encircled.perf.tomcat.config

import cz.encircled.joiner.kotlin.JoinerKt
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class JoinerConfig {

    @PersistenceContext
    lateinit var entityManager: EntityManager

    @Bean
    fun joiner() : JoinerKt {
        return JoinerKt(entityManager)
    }

}