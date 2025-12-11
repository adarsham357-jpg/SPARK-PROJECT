# Spark Quiz Application

A simple web-based quiz application built with Java and the Spark framework.

## Features

- User registration with name, USN, branch, and specialization
- 5-question general knowledge quiz
- Immediate scoring and feedback
- Responsive web design

## Prerequisites

- Java 8 or higher
- Windows OS (for batch scripts)

## Project Structure

```
spark-quiz-app/
├── src/
│   └── main/
│       ├── java/com/quiz/App.java      # Main application class
│       └── resources/templates/        # HTML templates (if using)
├── libs/                               # Dependencies (Spark framework JARs)
├── target/                             # Compiled classes
├── compile.bat                         # Compilation script
├── run.bat                             # Run script
└── pom.xml                             # Maven configuration (for reference)
```

## Getting Started

### Compiling the Application

Run the compilation script:

```cmd
compile.bat
```

This will compile the Java source code and place the resulting class files in the `target/` directory.

### Running the Application

Run the application using the run script:

```cmd
run.bat
```

The application will start on port 4567.

### Accessing the Application

Open your web browser and navigate to:

```
http://localhost:4567
```

## How to Use

1. Visit the home page and click "Register/Login"
2. Fill in your details in the registration form:
   - Full Name
   - USN (University Seat Number)
   - Branch (Computer Science, Information Science, etc.)
   - Specialization (Software Engineering, Data Science, etc.)
3. Take the 5-question general knowledge quiz
4. Submit your answers to see your score and detailed feedback
5. You can retake the quiz or return to the home page

## Technology Stack

- **Java 8**: Primary programming language
- **Spark Framework**: Lightweight web framework for Java
- **SLF4J Simple**: Logging framework
- **Embedded Jetty**: Web server (included with Spark)

## Dependencies

All required JAR files are located in the `libs/` directory:
- spark-core-2.9.4.jar
- slf4j-simple-1.7.36.jar
- Other supporting JARs

## Routes

- `GET /` - Home page
- `GET /register` - User registration page
- `POST /register` - Process registration form
- `GET /quiz` - Quiz page (requires registration)
- `POST /result` - Display quiz results

## Stopping the Application

To stop the application, press `Ctrl+C` in the terminal where it's running.

## Development

### Modifying Questions

To change the quiz questions, modify the arrays in the `App.java` file:
- `CORRECT_ANSWERS`: Array of correct answers
- `QUESTIONS`: Array of question texts
- `OPTIONS`: 2D array of answer options

### Styling

CSS styles are embedded directly in the HTML response strings in `App.java`.

## License

This project is available for educational purposes.