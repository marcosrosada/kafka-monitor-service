# Kafka Monitor Service

A real-time Kafka cluster monitoring service built with Kotlin and Spring Boot, featuring REST APIs and Kafka messaging integration using Spring Cloud Stream.

## 🚀 Features

- **User Management**: REST API for creating and retrieving users
- **Kafka Integration**: Message publishing and consuming using Spring Cloud Stream
- **Real-time Monitoring**: Topic monitoring and metrics collection
- **PostgreSQL Integration**: Data persistence with JPA
- **Reactive Programming**: Built with Kotlin coroutines and Spring WebFlux
- **Docker Support**: Complete containerized development environment

## 🛠 Technology Stack

- **Language**: Kotlin 2.2.10
- **Framework**: Spring Boot 3.5.5
- **Messaging**: Spring Cloud Stream with Kafka
- **Database**: PostgreSQL 17
- **Build Tool**: Maven
- **Testing**: JUnit 5, Mockito-Kotlin
- **Containerization**: Docker & Docker Compose

## 📋 Prerequisites

- Java 17+
- Docker & Docker Compose
- Maven 3.6+

## 🏃‍♂️ Quick Start

### 1. Clone the Repository
```bash
git clone https://github.com/marcosrosada/kafka-monitor-service.git
cd kafka-monitor-service
```

### 2. Start Infrastructure Services
```bash
docker-compose up -d
```

This will start:
- **Kafka** on `localhost:9092`
- **Zookeeper** on `localhost:2181`
- **PostgreSQL** on `localhost:5432`

### 3. Run the Application
```bash
./mvnw spring-boot:run
```

The application will start on `http://localhost:8080`

## 📡 API Endpoints

### User Management

#### Create User
```http
POST /api/users
Content-Type: application/x-www-form-urlencoded

name=John Doe&email=john@example.com
```

#### Get All Users
```http
GET /api/users
```

### Topic Management

#### Get Topics
```http
GET /api/topics
```

## 🔧 Configuration

### Application Configuration (`application.yaml`)

```yaml
spring:
  cloud:
    stream:
      kafka:
        binder:
          brokers: localhost:9092
      bindings:
        consumeUserProfile-in-0:
          destination: prod.user.profile.create
        produceUserProfile-out-0:
          destination: prod.user.profile.create
  datasource:
    url: jdbc:postgresql://localhost:5432/kafka_monitor
    username: postgres
    password: postgres
```

### Environment Variables

| Variable | Default | Description |
|----------|---------|-------------|
| `KAFKA_BOOTSTRAP_SERVERS` | `localhost:9092` | Kafka broker addresses |
| `POSTGRES_DB` | `kafka_monitor` | PostgreSQL database name |
| `POSTGRES_USER` | `postgres` | PostgreSQL username |
| `POSTGRES_PASSWORD` | `postgres` | PostgreSQL password |

## 🏗 Project Structure

```
src/
├── main/kotlin/com/man/kafkamonitor/
│   ├── config/          # Configuration classes
│   ├── controller/      # REST controllers
│   ├── listener/        # Kafka message listeners
│   ├── model/          # Data models
│   ├── sender/         # Kafka message senders
│   ├── service/        # Business logic
│   └── KafkaMonitorServiceApplication.kt
└── test/kotlin/        # Test classes
```

## 📨 Kafka Integration

### Message Flow

1. **User Creation**: When a user is created via REST API, a message is published to `prod.user.profile.create` topic
2. **Message Consumption**: The service consumes messages from the same topic for processing
3. **Functional Approach**: Uses Spring Cloud Stream functional programming model

### Key Components

- **UserSender**: Publishes user creation events using `StreamBridge`
- **UserListener**: Consumes user profile messages using functional `@Bean` approach
- **KafkaConfig**: Minimal Spring Cloud Stream configuration

## 🧪 Testing

### Run All Tests
```bash
./mvnw test
```

### Run Specific Test Categories
```bash
# Unit tests
./mvnw test -Dtest="*Test"

# Integration tests
./mvnw test -Dtest="*IntegrationTest"
```

## 🐳 Docker Development

### Services Overview

| Service | Port | Purpose |
|---------|------|---------|
| Kafka | 9092 | Message broker |
| Zookeeper | 2181 | Kafka coordination |
| PostgreSQL | 5432 | Data persistence |

### Useful Docker Commands

```bash
# Start all services
docker-compose up -d

# View logs
docker-compose logs -f kafka

# Stop all services
docker-compose down

# Reset data
docker-compose down -v
```

## 🔍 Monitoring & Health Checks

The application includes Spring Boot Actuator endpoints:

- **Health**: `GET /actuator/health`
- **Metrics**: `GET /actuator/metrics`
- **Info**: `GET /actuator/info`
