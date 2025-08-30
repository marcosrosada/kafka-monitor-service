package com.man.kafkamonitor

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KafkaMonitorServiceApplication

fun main(args: Array<String>) {
    runApplication<KafkaMonitorServiceApplication>(*args)
}
