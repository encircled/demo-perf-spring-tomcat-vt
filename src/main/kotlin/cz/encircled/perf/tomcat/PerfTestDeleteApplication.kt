package cz.encircled.perf.tomcat

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PerfTestDeleteApplication

fun main(args: Array<String>) {
    runApplication<PerfTestDeleteApplication>(*args)
}
