package com.man.kafkamonitor.sender

import org.slf4j.LoggerFactory
import org.springframework.cloud.stream.function.StreamBridge
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.support.MessageBuilder
import org.springframework.stereotype.Component

@Component
class UserSender(private val streamBridge: StreamBridge) {
    
    private val logger = LoggerFactory.getLogger(UserSender::class.java)
    
    fun sendUserProfileCreate(userId: String, profileData: String) {
        val message = MessageBuilder
            .withPayload(profileData)
            .setHeader(KafkaHeaders.KEY, userId)
            .build()
            
        streamBridge.send(USER_PROFILE_CHANNEL, message)
        logger.info("Published user profile create message for userId: {}", userId)
    }
    
    companion object {
        const val USER_PROFILE_CHANNEL = "produceUserProfile-out-0"
    }
}