package cz.encircled.perf.tomcat.service

import cz.encircled.joiner.kotlin.JoinerKt
import cz.encircled.joiner.kotlin.JoinerKtOps.eq
import cz.encircled.joiner.kotlin.JoinerKtQueryBuilder.all
import cz.encircled.perf.tomcat.model.Product
import cz.encircled.perf.tomcat.model.QProduct
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

interface ProductService {
    fun createData()
    fun findByName(name: String): Product?
}

@Service
class ProductServiceImpl : ProductService {

    val log = LoggerFactory.getLogger(ProductServiceImpl::class.java)

    @PersistenceContext
    lateinit var entityManager: EntityManager

    @Autowired
    lateinit var joiner: JoinerKt

    @Transactional
    override fun createData() {
        val count = 500000
        log.info("Creating $count test data entries")
        for (i in 1..count) {
            entityManager.persist(Product(i.toLong(), "Test$i"))
        }
        log.info("Test data created")
    }

    @Transactional(readOnly = true)
    override fun findByName(name: String): Product? {
        return joiner.findOne(QProduct.product.all() where { it.name eq name })
    }

}