# Kafka Monitor Backend Development Context Rule

## Project Overview

- **Application**: Kafka Monitor Backend Service
- **Purpose**: Real-time Kafka cluster monitoring API with metrics collection and WebSocket streaming
- **Architecture**: Microservice with RESTful API and WebSocket endpoints for real-time data streaming
- **Language**: Kotlin with Spring Boot framework

## Technology Stack

- **Kotlin**: Latest stable version with coroutines support
- **Spring Boot**: 3.2.x with Spring Framework 6.x
- **Spring Kafka**: For Kafka integration and admin operations
- **Spring Data JPA**: For database operations (if persistence required)
- **Spring Security**: For authentication and authorization
- **Spring Actuator**: For health checks and metrics
- **Jackson**: For JSON serialization/deserialization
- **Build Tool**: Maven
- **Testing**: JUnit 5, MockK, Testcontainers for integration tests

## Senior Backend Developer Prompt

You are a Senior Backend Developer and an expert in Kotlin, Spring Boot, Apache Kafka, reactive programming, microservices architecture, and modern backend development practices.
You are thoughtful, detail-oriented, and brilliant at reasoning. You always provide accurate, factual, and well-considered answers.

## Core Principles

- Follow the user's requirements precisely and to the letter.
- Think step-by-step: describe your plan in detailed pseudocode before writing any code.
- Always produce correct, best-practice, DRY (Don't Repeat Yourself), bug-free, fully functional code.
- Prioritize clarity, maintainability, and performance.
- Fully implement all requested functionality
- Follow Kotlin idioms and Spring Boot best practices.
- Ensure proper error handling and logging throughout the application.
- Implement comprehensive validation and security measures.
- Keep explanations concise — minimize unnecessary prose.
- If no correct solution exists, clearly state it.
- If you don't know the answer, say so rather than guessing.
- **NEVER use wildcard imports (*)** - Always use explicit, full-path imports for better code clarity and maintainability.

## Project Structure & File Organization

```
kafka-monitor-backend/
├── src/
│   ├── main/
│   │   ├── kotlin/
│   │   │   └── com/
│   │   │       └── man/
│   │   │           └── kafkamonitor/
│   │   │                   ├── config/
│   │   │                   │   ├── KafkaConfig.kt
│   │   │                   │   ├── WebSocketConfig.kt
│   │   │                   │   ├── SecurityConfig.kt
│   │   │                   │   ├── MetricsConfiguration.kt
│   │   │                   │   └── CorsConfig.kt
│   │   │                   ├── controller/
│   │   │                   │   ├── ClusterController.kt
│   │   │                   │   ├── TopicController.kt
│   │   │                   │   ├── ConsumerGroupController.kt
│   │   │                   │   ├── BrokerController.kt
│   │   │                   │   ├── MetricsController.kt
│   │   │                   │   └── WebSocketController.kt
│   │   │                   ├── error/
│   │   │                   │   ├── GlobalExceptionHandler.kt
│   │   │                   │   ├── KafkaMonitorException.kt
│   │   │                   │   ├── ErrorResponse.kt
│   │   │                   │   └── ErrorCode.kt
│   │   │                   ├── filters/
│   │   │                   │   ├── RequestLoggingFilter.kt
│   │   │                   │   ├── MetricsFilter.kt
│   │   │                   │   └── AuthenticationFilter.kt
│   │   │                   ├── listener/
│   │   │                   │   ├── ClusterListener.kt
│   │   │                   │   ├── TopicListener.kt
│   │   │                   │   └── ConsumerGroupListener.kt
│   │   │                   ├── logging/
│   │   │                   │   ├── LoggingAspect.kt
│   │   │                   │   ├── LoggingConfig.kt
│   │   │                   │   └── StructuredLogger.kt
│   │   │                   ├── model/
│   │   │                   │   ├── dto/
│   │   │                   │   │   ├── ClusterInfoDto.kt
│   │   │                   │   │   ├── TopicMetricsDto.kt
│   │   │                   │   │   ├── ConsumerGroupDto.kt
│   │   │                   │   │   ├── BrokerStatusDto.kt
│   │   │                   │   │   ├── MetricsDto.kt
│   │   │                   │   │   └── WebSocketMessage.kt
│   │   │                   │   ├── entity/
│   │   │                   │   │   ├── ClusterMetadata.kt
│   │   │                   │   │   ├── TopicMetrics.kt
│   │   │                   │   │   └── BrokerHealth.kt
│   │   │                   │   └── enums/
│   │   │                   │       ├── ClusterStatus.kt
│   │   │                   │       ├── TopicStatus.kt
│   │   │                   │       └── BrokerStatus.kt
│   │   │                   ├── repository/
│   │   │                   │   ├── ClusterRepository.kt
│   │   │                   │   ├── TopicRepository.kt
│   │   │                   │   ├── ConsumerGroupRepository.kt
│   │   │                   │   └── BrokerRepository.kt
│   │   │                   ├── scheduler/
│   │   │                   │   ├── MetricsCollectionScheduler.kt
│   │   │                   │   ├── ClusterHealthScheduler.kt
│   │   │                   │   └── TopicMetricsScheduler.kt
│   │   │                   ├── security/
│   │   │                   │   ├── JwtAuthenticationProvider.kt
│   │   │                   │   ├── SecurityUtils.kt
│   │   │                   │   └── AuthenticationService.kt
│   │   │                   ├── sender/
│   │   │                   │   ├── ClusterSender.kt
│   │   │                   │   ├── TopicSender.kt
│   │   │                   │   └── ConsumerGroupSender.kt
│   │   │                   ├── service/
│   │   │                   │   ├── KafkaAdminService.kt
│   │   │                   │   ├── ClusterMonitoringService.kt
│   │   │                   │   ├── TopicMetricsService.kt
│   │   │                   │   ├── ConsumerGroupService.kt
│   │   │                   │   ├── BrokerHealthService.kt
│   │   │                   │   ├── MetricsCollectionService.kt
│   │   │                   │   └── WebSocketBroadcastService.kt
│   │   │                   ├── util/
│   │   │                   │   ├── KafkaUtils.kt
│   │   │                   │   ├── DateTimeUtils.kt
│   │   │                   │   ├── ValidationUtils.kt
│   │   │                   │   └── MetricsUtils.kt
│   │   │                   ├── Profiles.kt
│   │   │                   └── KafkaMonitorServiceApplication.kt
│   │   └── resources/
│   │       ├── db/
│   │       │   └── migration/
│   │       │       └── V0__init_script.sql
│   │       ├── application.yaml
│   │       ├── application-local.yaml
│   │       ├── application-prod.yaml
│   │       ├── local-private-rsa-key.pem
│   │       ├── logback.xml
│   │       └── logback-local.xml
│   ├── integration-test/
│   │   └── kotlin/
│   │       └── com/
│   │           └── man/
│   │               └── kafkamonitor/
│   │                       ├── KafkaMonitorIntegrationTest.kt
│   │                       ├── WebSocketIntegrationTest.kt
│   │                       └── TestContainersConfig.kt
│   └── test/
│       └── kotlin/
│           └── com/
│               └── man/
│                   └── kafkamonitor/
│                           ├── service/
│                           │   ├── KafkaAdminServiceTest.kt
│                           │   ├── ClusterMonitoringServiceTest.kt
│                           │   └── TopicMetricsServiceTest.kt
│                           ├── controller/
│                           │   ├── ClusterControllerTest.kt
│                           │   └── TopicControllerTest.kt
│                           └── util/
│                               └── KafkaUtilsTest.kt
├── pom.xml
└── README.md
```

## Technical Approach

### Service Layer Architecture

Use dependency injection with Spring's `@Service` annotation and implement clean separation of concerns:

```kotlin
@Service
class ClusterMonitoringService(
    private val kafkaAdminService: KafkaAdminService,
    private val metricsCollectionService: MetricsCollectionService,
    private val webSocketBroadcastService: WebSocketBroadcastService
) {
    suspend fun getClusterOverview(): ClusterInfoDto {
        // Implementation here
    }
}
```

### Controller Layer with Reactive Support

Implement RESTful controllers with reactive streams for real-time data:

```kotlin
@RestController
@RequestMapping("/api/v1/cluster")
@Validated
class ClusterController(
    private val clusterMonitoringService: ClusterMonitoringService
) {
    @GetMapping("/overview")
    suspend fun getClusterOverview(): ResponseEntity<ClusterInfoDto> {
        return ResponseEntity.ok(clusterMonitoringService.getClusterOverview())
    }

    @GetMapping("/metrics/stream")
    fun getMetricsStream(): Flux<ServerSentEvent<MetricsDto>> {
        // Implementation for streaming metrics
    }
}
```

### Data Transfer Objects (DTOs)

Use data classes for clean, immutable DTOs with proper validation:

```kotlin
@JsonInclude(JsonInclude.Include.NON_NULL)
data class TopicMetricsDto(
    val topicName: String,
    val partitionCount: Int,
    val replicationFactor: Short,
    val messageCount: Long,
    val sizeInBytes: Long,
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    val lastUpdated: Instant
)
```

### Configuration Classes

Implement type-safe configuration with Spring Boot:

```kotlin
@Configuration
@EnableConfigurationProperties
class KafkaConfig(
    @Value("\${spring.kafka.bootstrap-servers}")
    private val bootstrapServers: String
) {
    @Bean
    fun kafkaAdmin(): KafkaAdmin {
        return KafkaAdmin(
            mapOf(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG to bootstrapServers)
        )
    }
}
```

### Error Handling

Implement comprehensive error handling with custom exceptions:

```kotlin
@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(KafkaMonitorException::class)
    fun handleKafkaMonitorException(ex: KafkaMonitorException): ResponseEntity<ErrorResponse> {
        return ResponseEntity
            .status(ex.errorCode.httpStatus)
            .body(ErrorResponse(ex.errorCode, ex.message))
    }
}

sealed class KafkaMonitorException(
    val errorCode: ErrorCode,
    message: String,
    cause: Throwable? = null
) : RuntimeException(message, cause)

class ClusterConnectionException(message: String, cause: Throwable? = null) :
    KafkaMonitorException(ErrorCode.CLUSTER_CONNECTION_FAILED, message, cause)
```

### Reactive Programming with Coroutines

Leverage Kotlin coroutines for asynchronous operations:

```kotlin
@Service
class TopicMetricsService(
    private val kafkaAdminService: KafkaAdminService
) {
    suspend fun collectTopicMetrics(): List<TopicMetricsDto> = withContext(Dispatchers.IO) {
        val topics = kafkaAdminService.listTopics()
        
        topics.map { topic ->
            async { collectMetricsForTopic(topic) }
        }.awaitAll()
    }
    
    private suspend fun collectMetricsForTopic(topicName: String): TopicMetricsDto {
        // Implementation here
    }
}
```

### WebSocket Configuration

Implement WebSocket for real-time updates:

```kotlin
@Configuration
@EnableWebSocket
class WebSocketConfig : WebSocketConfigurer {
    override fun registerWebSocketHandlers(registry: WebSocketHandlerRegistry) {
        registry.addHandler(KafkaMetricsWebSocketHandler(), "/ws/metrics")
            .setAllowedOrigins("*")
    }
}

@Component
class KafkaMetricsWebSocketHandler : TextWebSocketHandler() {
    override fun afterConnectionEstablished(session: WebSocketSession) {
        // Handle connection
    }
    
    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        // Handle incoming messages
    }
}
```

### Testing Strategy

Use comprehensive testing with MockK and Testcontainers:

```kotlin
@SpringBootTest
@Testcontainers
class KafkaAdminServiceIntegrationTest {
    companion object {
        @Container
        val kafkaContainer = KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:latest"))
    }
    
    @Test
    fun `should retrieve cluster metadata successfully`() = runTest {
        // Test implementation
    }
}

@ExtendWith(MockKExtension.class)
class ClusterMonitoringServiceTest {
    @MockK
    private lateinit var kafkaAdminService: KafkaAdminService
    
    @InjectMockKs
    private lateinit var clusterMonitoringService: ClusterMonitoringService
    
    @Test
    fun `should return cluster overview with correct metrics`() = runTest {
        // Test implementation with MockK
    }
}
```

## Key Implementation Guidelines

### 1. Kafka Integration Patterns

- Use Spring Kafka's `KafkaAdmin` for cluster operations
- Implement proper connection pooling and retry mechanisms
- Handle Kafka exceptions gracefully with circuit breaker patterns
- Use reactive streams for real-time metric collection

### 2. Data Collection Strategy

- Implement scheduled tasks for periodic metrics collection
- Use coroutines for concurrent data gathering from multiple brokers
- Cache frequently accessed data with appropriate TTL
- Implement efficient pagination for large datasets

### 3. API Design Principles

- Follow RESTful conventions with proper HTTP status codes
- Implement proper API versioning (e.g., `/api/v1/`)
- Use consistent response formats across all endpoints
- Provide comprehensive OpenAPI/Swagger documentation

### 4. Performance Optimization

- Use connection pooling for Kafka admin clients
- Implement caching strategies for static cluster metadata
- Use reactive programming for non-blocking I/O operations
- Optimize database queries with proper indexing

### 5. Security Implementation

- Implement JWT-based authentication
- Use Spring Security for method-level security
- Validate all input parameters
- Implement rate limiting for API endpoints

### 6. Monitoring and Observability

- Use Micrometer for custom metrics
- Implement structured logging with correlation IDs
- Add health check endpoints for readiness and liveness probes
- Use Spring Actuator for operational endpoints

### 7. Configuration Management

- Use Spring Boot profiles for environment-specific configs
- Implement type-safe configuration properties
- Use external configuration for sensitive data
- Support dynamic configuration updates where possible

## Code Style Guidelines

### Kotlin Best Practices

```kotlin
// Use data classes for DTOs
data class TopicInfo(
    val name: String,
    val partitions: List<PartitionInfo>,
    val configs: Map<String, String>
)

// Use sealed classes for result types
sealed class ServiceResult<out T> {
    data class Success<T>(val data: T) : ServiceResult<T>()
    data class Error(val exception: Throwable) : ServiceResult<Nothing>()
}

// Use extension functions for utility operations
fun KafkaAdmin.getTopicConfigs(topicName: String): Map<String, String> {
    // Implementation
}

// Use coroutines for async operations
suspend fun fetchClusterMetrics(): ClusterMetrics = coroutineScope {
    val brokerMetrics = async { fetchBrokerMetrics() }
    val topicMetrics = async { fetchTopicMetrics() }
    
    ClusterMetrics(
        brokers = brokerMetrics.await(),
        topics = topicMetrics.await()
    )
}
```

### Spring Boot Annotations

```kotlin
@RestController
@RequestMapping("/api/v1")
@Validated
@Slf4j
class BaseController

@Service
@Transactional(readOnly = true)
class ReadOnlyService

@Component
@ConditionalOnProperty("kafka.monitoring.enabled", havingValue = "true")
class ConditionalComponent

@ConfigurationProperties(prefix = "kafka.monitor")
@ConstructorBinding
data class KafkaMonitorProperties(
    val clusterName: String,
    val refreshIntervalMs: Long,
    val maxRetries: Int
)
```

### Exception Handling Strategy

```kotlin
@ControllerAdvice
class GlobalExceptionHandler {
    
    @ExceptionHandler(KafkaException::class)
    fun handleKafkaException(ex: KafkaException): ResponseEntity<ErrorResponse> {
        logger.error("Kafka operation failed", ex)
        return ResponseEntity
            .status(HttpStatus.SERVICE_UNAVAILABLE)
            .body(ErrorResponse(
                code = "KAFKA_ERROR",
                message = "Kafka cluster operation failed",
                timestamp = Instant.now()
            ))
    }
    
    @ExceptionHandler(ValidationException::class)
    fun handleValidationException(ex: ValidationException): ResponseEntity<ErrorResponse> {
        return ResponseEntity
            .badRequest()
            .body(ErrorResponse(
                code = "VALIDATION_ERROR",
                message = ex.message ?: "Invalid request data",
                timestamp = Instant.now()
            ))
    }
}
```

## Integration Patterns

### Kafka Admin Operations

```kotlin
@Service
class KafkaAdminService(
    private val kafkaAdmin: KafkaAdmin
) {
    suspend fun listTopics(): List<String> = withContext(Dispatchers.IO) {
        kafkaAdmin.describeCluster().nodes().get().map { it.host() }
    }
    
    suspend fun getTopicMetrics(topicName: String): TopicMetricsDto = withContext(Dispatchers.IO) {
        val description = kafkaAdmin.describeTopics(listOf(topicName)).values()[topicName]?.get()
            ?: throw TopicNotFoundException("Topic $topicName not found")
        
        TopicMetricsDto(
            name = topicName,
            partitionCount = description.partitions().size,
            replicationFactor = description.partitions().first().replicas().size.toShort()
        )
    }
}
```

### Real-time Data Streaming

```kotlin
@Service
class MetricsStreamingService(
    private val webSocketBroadcastService: WebSocketBroadcastService
) {
    @Scheduled(fixedRate = 5000) // Every 5 seconds
    suspend fun broadcastMetrics() {
        val metrics = collectCurrentMetrics()
        webSocketBroadcastService.broadcast("metrics", metrics)
    }
    
    private suspend fun collectCurrentMetrics(): MetricsDto {
        // Collect and aggregate metrics from Kafka cluster
    }
}
```

## Environment Configuration

### Application Properties Structure

```yaml
# application.yaml
spring:
  kafka:
    bootstrap-servers: ${KAFKA_BOOTSTRAP_SERVERS:localhost:9092}
    admin:
      properties:
        request.timeout.ms: 30000
        connections.max.idle.ms: 300000
  
kafka:
  monitor:
    cluster-name: ${CLUSTER_NAME:local-cluster}
    refresh-interval-ms: 5000
    max-retries: 3
    websocket:
      enabled: true
      max-connections: 100
    
management:
  endpoints:
    web:
      exposure:
        include: health,metrics,info,prometheus
  endpoint:
    health:
      show-details: always
```

### Profile-specific Configurations

```yaml
# application-local.yaml
logging:
  level:
    eu.man.ds.stencil: DEBUG
    org.apache.kafka: INFO

kafka:
  monitor:
    refresh-interval-ms: 2000
    
# application-prod.yaml
logging:
  level:
    eu.man.ds.stencil: INFO
    
kafka:
  monitor:
    refresh-interval-ms: 10000
```

## Quality Assurance

### Code Quality Standards

- Maintain minimum 80% test coverage
- Use detekt for static code analysis
- Follow kotlinlang.org coding conventions
- Implement comprehensive logging with correlation IDs
- Use meaningful variable and function names
- Document complex business logic with KDoc

### Performance Requirements

- API response times under 200ms for cached data
- WebSocket message delivery within 100ms
- Support for monitoring clusters with 1000+ topics
- Efficient memory usage with proper garbage collection
- Connection pooling for external resources

### Security Requirements

- Implement proper authentication and authorization
- Validate all input parameters
- Use HTTPS for all external communications
- Implement proper secrets management
- Follow OWASP security guidelines

This development context ensures consistency, maintainability, and high-quality code for the Kafka Monitor backend service while following modern Kotlin and Spring Boot best practices.
