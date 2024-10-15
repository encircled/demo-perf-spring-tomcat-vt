package cz.encircled.perf.tomcat.service

import cz.encircled.joiner.kotlin.JoinerKt
import cz.encircled.joiner.kotlin.JoinerKtOps.eq
import cz.encircled.joiner.kotlin.JoinerKtQueryBuilder.all
import cz.encircled.perf.tomcat.model.Product
import cz.encircled.perf.tomcat.model.QProduct.product
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

interface ProductService {
    fun createData(startId: Int, perChunk: Int)
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
    override fun createData(startId: Int, perChunk: Int) {
        log.info("Creating $perChunk entries")

        if (entityManager.find(Product::class.java, startId + 1) != null) {
            log.info("Chunk already exists")
            return
        }

        for (i in 1..perChunk) {
            val id = startId + i
            entityManager.persist(Product(id.toLong(), "Test$id"))
        }
        log.info("Test data chunk created")
    }

    @Transactional(readOnly = true)
    override fun findByName(name: String): Product? {
        return joiner.findOne(product.all() where { it.name eq name })
    }

}