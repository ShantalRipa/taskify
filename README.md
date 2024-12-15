# Taskify - Task Management System

## Project Overview
Taskify is a simple task management system built using **Spring Boot**. This project allows users to manage tasks by performing basic CRUD (Create, Read, Update, Delete) operations.

## Prerequisites
Before you begin, ensure you have the following installed:

- **Java** (JDK 17 or above) - [Download Java](https://adoptopenjdk.net/)
- **Maven** - [Install Maven](https://maven.apache.org/install.html)
- **Git** - [Install Git](https://git-scm.com/book/en/v2/Getting-Started-Installing-Git)

## Getting Started

### 1. Clone the Repository
First, clone the repository to your local machine using Git:

```bash
git clone https://github.com/ShantalRipa/taskify.git
cd taskify
```

### 2. Install Dependencies
Make sure you have **Maven** installed, then run the following command to install all dependencies:

```bash
mvn install
```

This command will download and set up all the necessary dependencies for the project.

### 3. Run the Application
To start the Spring Boot application, use the following Maven command:

```bash
mvn spring-boot:run
```

By default, the application will run on **port 8080**. You can access it in your browser at:

```plaintext
http://localhost:8080
```

### 4. Test the Health Endpoint
Once the application is running, you can test the `/health` endpoint to make sure the setup is correct. Open your browser or use a tool like **Postman** to visit:

```plaintext
http://localhost:8080/health
```

You should see a message like this:
```plaintext
Taskify application is healthy!
```

## Folder Structure
Here’s an overview of the important directories and files in this project:

- **`src/main/java`**: Contains the Java source code for the project.
- **`src/main/resources`**: Contains configuration files such as `application.properties`.
- **`pom.xml`**: Maven configuration file to manage dependencies and build setup.
- **`.gitignore`**: Specifies which files to ignore when committing to Git.
