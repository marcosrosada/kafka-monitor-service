package com.man.kafkamonitor.service

import com.man.kafkamonitor.model.User
import com.man.kafkamonitor.sender.UserSender
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.UUID
import java.util.concurrent.ConcurrentHashMap

@Service
class UserService(private val userSender: UserSender) {
    
    private val logger = LoggerFactory.getLogger(UserService::class.java)
    private val users = ConcurrentHashMap<String, User>()
    
    fun createUser(name: String, email: String): User {
        val userId = UUID.randomUUID().toString()
        val user = User(userId, name, email)
        users[userId] = user
        
        logger.info("Created user: {}", user)
        
        // Send to Kafka
        val profileData = "{\"id\":\"$userId\",\"name\":\"$name\",\"email\":\"$email\"}"
        userSender.sendUserProfileCreate(userId, profileData)
        
        return user
    }
    
    fun getAllUsers(): List<User> {
        return users.values.toList()
    }
    
    fun addUserFromKafka(user: User) {
        users[user.id] = user
        logger.info("Added user from Kafka: {}", user)
    }
}