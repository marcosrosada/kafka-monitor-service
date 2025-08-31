package com.man.kafkamonitor.service

import org.apache.kafka.clients.admin.AdminClient
import org.slf4j.LoggerFactory
import org.springframework.kafka.core.KafkaAdmin
import org.springframework.stereotype.Service

@Service
class TopicService(private val kafkaAdmin: KafkaAdmin) {
    
    private val logger = LoggerFactory.getLogger(TopicService::class.java)
    
    fun getTopics(): List<String> {
        return try {
            logger.info("Fetching topics from Kafka")
            AdminClient.create(kafkaAdmin.configurationProperties).use { client ->
                val topics = client.listTopics().names().get().toList()
                logger.info("Found {} topics", topics.size)
                topics
            }
        } catch (e: Exception) {
            logger.error("Error fetching topics: {}", e.message)
            listOf("Error: ${e.message}")
        }
    }
}