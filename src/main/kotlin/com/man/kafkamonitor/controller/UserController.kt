package com.man.kafkamonitor.controller

import com.man.kafkamonitor.model.User
import com.man.kafkamonitor.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class UserController(private val userService: UserService) {

    @PostMapping("/users")
    fun createUser(
        @RequestParam name: String,
        @RequestParam email: String
    ): ResponseEntity<User> {
        val user = userService.createUser(name, email)
        return ResponseEntity.ok(user)
    }

    @GetMapping("/users")
    fun getAllUsers(): ResponseEntity<List<User>> {
        val users = userService.getAllUsers()
        return ResponseEntity.ok(users)
    }
}
