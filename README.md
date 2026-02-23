# Reactive Heroes Assignment

## Goal

You are given a reactive CRUD API using Quarkus and Mutiny.

It works in JVM mode.

---

## API Endpoints

### Base URL
- **Development mode**: `http://localhost:8080`

### Heroes Resource (`/heroes`)

#### Get All Heroes
- **Endpoint**: `GET /heroes`
- **Description**: Retrieves all superheroes from the in-memory store
- **Response**: Returns a stream of superhero objects
- **Content-Type**: `application/json`

**Example using curl:**
```bash
curl -X GET http://localhost:8080/heroes
```

**Example Response:**
```json
[
  {
    "id": 1,
    "name": "Spider-Man",
    "power": "Web-slinging",
    "secretHash": "a1b2c3d4"
  }
]
```

#### Create Hero
- **Endpoint**: `POST /heroes`
- **Description**: Creates a new superhero with an auto-generated secret hash
- **Request Body**: JSON object with `id`, `name`, and `power` fields
- **Response**: Returns the created superhero with the generated `secretHash`
- **Content-Type**: `application/json`
- **Note**: Currently includes a 3-second delay (intentional for performance testing)

**Example using curl:**
```bash
curl -X POST http://localhost:8080/heroes \
  -H "Content-Type: application/json" \
  -d "{\"id\":1,\"name\":\"Spider-Man\",\"power\":\"Web-slinging\"}"
```

**Example Request Body:**
```json
{
  "id": 1,
  "name": "Spider-Man",
  "power": "Web-slinging"
}
```

**Example Response:**
```json
{
  "id": 1,
  "name": "Spider-Man",
  "power": "Web-slinging",
  "secretHash": "a1b2c3d4"
}
```

---

## Part 1 – Investigate Performance

1. Run in dev mode:
   ./mvnw quarkus:dev

2. Send multiple concurrent POST requests.
3. Observe response time.
4. Inspect logs and thread names.

Questions:
- Is this endpoint really non-blocking?
- Which thread executes the POST method?
- Is there blocking inside the reactive pipeline?

Fix the performance issue properly.

---

## (!!!PLEASE IGNORE IT FOR THE MOMENT, I must change something here) Part 2 – Native Image (*Advanced*)
1. Make sure you have the necessary native build environment set up (GraalVM, Docker/Podman etc.)
2. Build the application native:
   hint: ./mvnw clean package -Pnative

3. Run the native executable.
   hint: executable is located in target/reactive-heroes-challenge-1.0.0-SNAPSHOT-runner.exe

4. Call the POST endpoint again with the same payload as before.

It will fail.

Questions:
- Why does it fail only in native mode?
- What is different between JVM and native?
- How can you fix it?

---

## Deliverables

- Working reactive endpoint without blocking.
- Working native image build.
- Short explanation of both problems.
