If any changes have been done to the code, remember to use:
```bash
./gradlew clean build -x test
```
If any change has been made that requires for the database or any memory to be deleted and created anew, remember to use:
```bash
docker-compose down
```
Before running:
```bash
docker-compose up --build
```