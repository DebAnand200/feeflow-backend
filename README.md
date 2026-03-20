# feeflow-backend

FeeFlow is an Installment Control & Recovery System backend built for competitive coaching institutes. This repository contains the backend services built as a Modular Monolith.

## Architecture & Technology Stack

*   **Framework:** Spring Boot 3
*   **Language:** Java 21
*   **Database:** PostgreSQL (for transactions, indexing, JSON support)
*   **Messaging/Queue:** Redis Queue (for background tasks/reminders)
*   **Build Tool:** Maven

### Backend Modules (Logical Bounded Contexts)

*   `feeflow-auth`: Authentication and Authorization
*   `feeflow-students`: Student and Batch Management
*   `feeflow-fees`: Installment Engine (Core Brain)
*   `feeflow-payments`: Payment Tracking and Allocation Flow
*   `feeflow-reminders`: Automation Engine (e.g. async WhatsApp sending)
*   `feeflow-broadcast`: Batch-based messaging
*   `feeflow-common`: Shared utilities and domain objects
*   `feeflow-api`: Main API gateway/application runner

## Local Development Setup

Follow these steps to set up the FeeFlow backend on your local machine.

### Prerequisites

Ensure you have the following installed:
1.  **Java Development Kit (JDK) 21:** Required for compiling and running the application.
2.  **Apache Maven:** For building the project and managing dependencies (or use the provided `mvnw` wrapper).
3.  **PostgreSQL (v14+ recommended):** The primary relational database.
4.  **Redis:** Used for messaging queues and caching.

### Database Setup

1. Install and start your local PostgreSQL server.
2. Create a new database for the application (e.g., `feeflow`).
3. Ensure the database credentials match the configuration in your local properties files. *Note: You will need to configure `application.properties` or `application.yml` in the `feeflow-api/src/main/resources` directory (create it if it doesn't exist) with your database connection details.*

### Redis Setup

1. Install and start your local Redis server.
2. Ensure Redis is accessible on the default port (6379) or update your application configuration accordingly.

### Building the Project

Clone the repository and build the project using Maven from the root directory:

```bash
# Using installed Maven
mvn clean install

# Or using the Maven Wrapper
./mvnw clean install
```

### Running the Application

Once the project is built and your database/Redis instances are running, you can start the application by running the main API module:

```bash
cd feeflow-api
mvn spring-boot:run
```

Alternatively, you can run the generated `.jar` file directly:

```bash
java -jar feeflow-api/target/feeflow-api-0.0.1-SNAPSHOT.jar
```

## Module Structure

The application is structured as a Modular Monolith to keep domains logically separate while deploying as a single unit:

*   **Write Model / System of Record:** Prioritized across the core services (InstallmentGenerationService, PaymentAllocationService, ReminderEngineService).
*   **Robustness:** Incorporates retry queues and delivery event tracking for reliability (e.g., message retry queues).

