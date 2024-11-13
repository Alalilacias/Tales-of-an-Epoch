# Build Instructions Cheat Sheet

This cheat sheet provides a quick reference for building and running the **Tales of an Epoch** application in different environments.

---

## Development Build

### Quick Command Sequence
1. Stop containers and reset the database:
   ```bash
   docker-compose down
   ```
2. Build without tests for faster feedback:
   ```bash
   ./gradlew clean build -x test
   ```
3. Start the application:
   ```bash
   docker-compose up --build
   ```

### Full Command Sequence
```bash
docker-compose down
./gradlew clean build -x test
docker-compose up --build
```

---

## Production Build

### Full Command Sequence for Production
1. Stop containers and reset the database:
   ```bash
   docker-compose down
   ```
2. Build with tests to verify stability:
   ```bash
   ./gradlew clean build
   ```
3. Start the application:
   ```bash
   docker-compose up --build
   ```

### Quick Full Production Commands
```bash
docker-compose down
./gradlew clean build
docker-compose up --build
```

---

## Important Reminders
- Run `docker-compose down` if database or in-memory storage changes.
- Use `./gradlew clean build -x test` in development for speed.
- For production, always include tests with `./gradlew clean build`.