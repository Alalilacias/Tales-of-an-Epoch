# Building Instructions

The following document details the steps to follow in order to build the application depending on the environment and the existing context and desired results.

## Table of Contents
1. [Development Build Instructions](#1-development-build-instructions)
2. [Production Build Instructions](#2-production-build-instructions)
3. [Important Reminders](#3-important-reminders)

## 1. Development Build Instructions
During development, it is common to prioritize faster build times and iteration without running tests to speed up the development cycle. The following commands help streamline this process:

### Steps for Development Build
1. If any change has been made that requires the database or in-memory storage to be reset, run:
   ```bash
   docker-compose down
   ```
   This ensures the database is cleared and ready for fresh initialization.

2. To build the project without running tests, use:
   ```bash
   ./gradlew clean build -x test
   ```
   This skips the test phase, allowing for quicker feedback during development.

3. Start the application:
   ```bash
   docker-compose up --build
   ```
   This command builds and runs the Docker containers.

### Quick Command Sequence for Development
To execute the entire process quickly:
```bash
docker-compose down
./gradlew clean build -x test
docker-compose up --build
```

## 2. Production Build Instructions
In a production environment, it is essential to ensure that all tests are executed to verify the integrity and reliability of the application. The following steps outline the production build process:

### Steps for Production Build
1. If any database or memory reset is needed, start with:
   ```bash
   docker-compose down
   ```
   This guarantees that the environment starts from a clean slate.

2. Build the project with tests included to ensure all code changes pass validation:
   ```bash
   ./gradlew clean build
   ```
   This runs all tests as part of the build process, verifying that the codebase is stable.

3. Run the application:
   ```bash
   docker-compose up --build
   ```
   This will build the Docker containers and start the application with all changes applied.

### Full Command Sequence for Production
To execute the full process for a production build:
```bash
docker-compose down
./gradlew clean build
docker-compose up --build
```

## 3. Important Reminders
- Always ensure that the `docker-compose down` command is run if there are significant changes that affect the database or in-memory storage.
- Use `./gradlew clean build -x test` for development to speed up the process.
- For production or before deploying to a live environment, always run `./gradlew clean build` to include test verification.

