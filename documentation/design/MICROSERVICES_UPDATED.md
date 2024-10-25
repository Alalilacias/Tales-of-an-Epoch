# Microservices Structure for Tales of an Epoch

The following document details the current microservice structure of the development of the Tales of an Epoch game's backend. A microservice architecture was chosen as each one can scale independently, they can be designed separately depending on the needs of the service, and they allow for the compartmentalization of development into easier to handle parts.

Each service will focus on a different core aspect of the game and, depending on the needs of each, use different technologies as needed. They will communicate with each other through APIs or message brokers, as the needs and performance requirements of the project determine.

This is a prototype of a document, meaning it will change as the development continues to grow to adapt to the new growth of the game and to changes in design paradigms.

### Table of contents

- [Overview]()
- [Design philosophy]()
- 