package com.man.kafkamonitor.listener

import com.fasterxml.jackson.databind.ObjectMapper
import com.man.kafkamonitor.model.User
import com.man.kafkamonitor.service.UserService
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.stereotype.Component
import java.util.function.BiConsumer

@Component
class UserListener(
    private val userService: UserService,
    private val objectMapper: ObjectMapper
) {
    
    private val logger = LoggerFactory.getLogger(UserListener::class.java)
    
    @Bean
    fun consumeUserProfile(): BiConsumer<String, Map<String, String>> {
        return BiConsumer { payload, headers ->
            val key = headers[KafkaHeaders.RECEIVED_KEY]
            if (key != null) {
                receive(key, payload)
            }
        }
    }
    
    private fun receive(userId: String, message: String) {
        logger.info("Received user profile create message for userId: {} - {}", userId, message)
        
        try {
            val userMap = objectMapper.readValue(message, Map::class.java)
            val user = User(
                id = userMap["id"].toString(),
                name = userMap["name"].toString(),
                email = userMap["email"].toString()
            )
            userService.addUserFromKafka(user)
        } catch (e: Exception) {
            logger.error("Error processing user profile create message: {}", e.message)
        }
    }
}