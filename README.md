# Dance Battle Registration System (Lab 7)

A web-based application for registering and managing participants in a dance battle, built with **Spring Boot**, featuring a REST API and real-time file persistence.

---

## Tech Stack
* **Backend:** Java 17+, Spring Boot 3.x (Spring Web)
* **Serialization:** Jackson (JSON processing)
* **Frontend:** HTML5, CSS3, JavaScript (Fetch API)
* **Storage:** Local `participants.json` file in the project root (no heavy relational databases required)

---

## Features (CRUD)
The application supports full CRUD operations:
1. **Create:** Register a new participant (nickname, phone number, and dance style).
2. **Read:** Retrieve the list of all registered participants via the web interface or as a JSON response from the API.
3. **Update:** Edit existing participant details by ID.
4. **Delete:** Remove a participant from the system.

All changes are automatically synchronized with the local **`participants.json`** file in real time.

---

## How to Run

1. Clone the repository or open the project in **IntelliJ IDEA**.
2. Ensure your JDK is configured (Java 17 or higher recommended).
3. Run the main application class:
   * `src/main/java/kz/iitu/dance/DanceBattleApplication.java`
4. Open the web interface in your browser:
   * http://localhost:8080
5. To view the raw JSON data via the REST API, navigate to:
   * http://localhost:8080/api/dance

---

## REST API Endpoints

| Method | Endpoint | Description |
| :--- | :--- | :--- |
| **GET** | `/api/dance` | Get all participants (supports `?search=...` query parameter) |
| **GET** | `/api/dance/{id}` | Get a specific participant by ID |
| **POST** | `/api/dance` | Register a new participant |
| **PUT** | `/api/dance/{id}` | Update an existing participant |
| **DELETE** | `/api/dance/{id}` | Delete a participant |
