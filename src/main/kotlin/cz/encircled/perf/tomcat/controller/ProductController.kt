package cz.encircled.perf.tomcat.controller

import cz.encircled.perf.tomcat.model.Product
import cz.encircled.perf.tomcat.service.ProductService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ProductController(
    val productService: ProductService
) {

    @GetMapping("/createData")
    fun createData() {
        productService.createData()
    }

    @GetMapping("/{name}")
    @ResponseBody
    fun byName(@PathVariable("name") name: String): Product? {
        return productService.findByName(name)
    }

}