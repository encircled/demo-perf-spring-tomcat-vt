package cz.encircled.perf.tomcat.controller

import cz.encircled.perf.tomcat.model.Product
import cz.encircled.perf.tomcat.service.ProductService
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ProductController(
    val productService: ProductService
) {

    private val log = LoggerFactory.getLogger(ProductController::class.java)

    @GetMapping("/createData")
    fun createData() {
        val perChunk = 50000
        val chunks = 10
        log.info("Creating ${perChunk * chunks} test data entries")
        for (i in 1..chunks) {
            productService.createData((i-1) * perChunk, perChunk)
        }
    }

    @GetMapping("/{name}")
    @ResponseBody
    fun byName(@PathVariable("name") name: String): Product? {
        return productService.findByName(name)
    }

}