package com.man.kafkamonitor.controller

import com.man.kafkamonitor.service.TopicService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class TopicController(private val topicService: TopicService) {
    
    @GetMapping("/topics")
    fun listTopics(): List<String> {
        return topicService.getTopics()
    }
}