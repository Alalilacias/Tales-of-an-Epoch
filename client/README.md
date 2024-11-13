# Tales of an Epoch Client

This folder contains the **Godot project files** for the client side of **Tales of an Epoch**. The client is developed using the Godot game engine and serves as the main interface for players to interact with the game.

## Table of Contents
- [Overview](#overview)
- [Setup Instructions](#setup-instructions)
- [Folder Structure](#folder-structure)
- [Building and Running the Client](#building-and-running-the-client)
- [Client-Backend Communication](#client-backend-communication)
- [Contributing](#contributing)

---

## Overview

The client for Tales of an Epoch is built in **Godot 4** and provides the visual and interactive components of the game. It connects with the backend microservices to manage player data, combat, resources, and other game functionalities.

## Setup Instructions

### Prerequisites
- **Godot Engine** (Version 4.0 or later): [Download Godot](https://godotengine.org/download)
- **Git**: For version control and pulling the latest project files.

### Cloning the Repository
Clone the repository and navigate to the client directory:
```bash
git clone https://github.com/yourusername/tales-of-an-epoch.git
cd tales-of-an-epoch/client
```

### Opening the Project in Godot
1. Open the Godot Engine.
2. Select "Import" and navigate to the `client` directory.
3. Choose the `project.godot` file to open the project in Godot.

## Folder Structure

The client folder contains the following subdirectories:

TODO

Each folder is organized to ensure modularity and ease of navigation, promoting clear separation between gameplay elements, assets, and scripts.

## Building and Running the Client

1. **Run in Editor**: To test the game in Godot, click the "Play Scene" button to run the current scene or "Play Project" to start the game from the main scene.
2. **Exporting the Client**: For production builds, use Godot’s Export feature:
    - Go to `Project > Export`.
    - Choose your target platform (e.g., Windows, macOS, Linux, Android).
    - Configure export settings and specify the export path.
    - Click "Export Project" to generate a standalone build.

For deployment, ensure that the client has access to the correct backend server URLs, which may be configured through environment variables or config files within the project.

## Client-Backend Communication

The client communicates with the backend services via **REST APIs** and **WebSockets**. The backend manages persistent game data, authentication, player interactions, and real-time game logic. Documentation on API endpoints and WebSocket usage can be found in `docs/api/` and `docs/client/architecture/communication.md`.

## Contributing

If you are interested in contributing to the client, please reach out through GitHub for collaboration. Ensure that all contributions follow project standards and are compatible with the backend services. For details on contribution guidelines, refer to the main `CONTRIBUTING.md` in the root directory.