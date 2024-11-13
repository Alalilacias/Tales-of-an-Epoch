# Tales of an Epoch

### Multiplayer Empire Building Strategy Game

**Tales of an Epoch** is a multiplayer empire-building strategy game where players nurture and develop a nation, facing various challenges to lead it to greatness. Players can engage in battles, manage resources, explore territories, and form alliances to enhance their empire's strength.

## Table of Contents
- [Overview](#overview)
- [Technology Stack](#technology-stack)
- [Microservices Architecture](#microservices-architecture)
- [Setup Instructions](#setup-instructions)
- [Running the Project](#running-the-project)
- [Contributing](#contributing)

---

## Overview

Tales of an Epoch invites players to create, grow, and guide their nation to prosperity. Engage in strategic battles, form alliances, and explore new territories in a world full of challenges. The game’s microservice-based backend enables scalability and efficient handling of high-traffic, multiplayer interactions.

## Technology Stack

- **Backend Framework**: Java (Spring Boot), Node.js, Python, Go
- **Database**: MongoDB, Redis
- **Message Brokers**: RabbitMQ, Kafka
- **WebSockets**: Real-time multiplayer interactions
- **Containerization**: Docker and Docker Compose for deployment and environment consistency

## Microservices Architecture

This game backend uses a microservices approach, dividing core functionalities into specialized services that interact through synchronous and asynchronous protocols. Here’s an overview of each service and its responsibilities:

1. **Authentication & Player Management**: Manages user registration, authentication, and player profiles.
2. **Resource Management**: Handles player resources (gold, wood, etc.) and production systems.
3. **Combat System**: Controls battles, unit management, and combat outcomes.
4. **World Map & Exploration**: Oversees map-related actions, exploration, and territory management.
5. **Alliance & Social System**: Facilitates player alliances, social interactions, and invites.
6. **Real-Time Multiplayer**: Manages WebSocket connections for real-time player interactions.
7. **Notifications**: Manages in-game notifications and alerts for players.

Each service has its own database, allowing for independent scaling and improved resilience. Communication is handled via REST APIs for synchronous interactions and RabbitMQ or Kafka for event-driven communication.

## Setup Instructions

### Prerequisites
- [Docker](https://www.docker.com/) and [Docker Compose](https://docs.docker.com/compose/) installed.
- Java 11+, Node.js 14+, Python 3.7+, Go 1.15+.
- MongoDB and Redis (containers or managed instances are recommended for local development).

### Development Setup

1. Clone the repository:
   ```bash
   git clone https://github.com/Alalilacias/Tales-of-an-Epoch.git
   cd Tales-of-an-Epoch
   ```
2. Set up environment variables following the [Configuration Guide](docs/environment/configuration.md).
3. Build and run the project without running tests for faster feedback:
   ```bash
   docker-compose down  # Clears previous instances if necessary
   ./gradlew clean build -x test
   docker-compose up --build
   ```

For more details, refer to [Development Build Instructions](docs/environment/configuration.md).

### Production Setup

To set up a production environment, use the following commands to ensure all tests are validated and the environment is correctly prepared:
```bash
docker-compose down
./gradlew clean build
docker-compose up --build
```

## Running the Project

Each microservice can be started individually or together using Docker Compose for a full local setup. The services and their ports are configured in `docker-compose.yml`.

For more detailed deployment guidelines, see [Production Build Instructions](docs/environment/deployment.md).

## Contributing

We currently do not openly invite contributions. However, if you are interested in contributing to Tales of an Epoch, please reach out directly through GitHub. Contact information can be found in the repository profile, and we’ll be happy to discuss collaboration on a case-by-case basis.