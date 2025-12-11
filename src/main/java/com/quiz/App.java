package com.quiz;

import static spark.Spark.*;
import java.util.*;

public class App {
    // In-memory storage for user data (in a real app, you'd use a database)
    private static Map<String, Map<String, String>> users = new HashMap<>();
    
    // Correct answers for the quiz (first 5 questions only)
    private static final String[] CORRECT_ANSWERS = {
        "Paris", "Mars", "Blue Whale", "Oxygen", "Leonardo da Vinci"
    };
    
    // Quiz questions and options (first 5 questions only)
    private static final String[] QUESTIONS = {
        "What is the capital of France?",
        "Which planet is known as the Red Planet?",
        "What is the largest mammal?",
        "Which element has the chemical symbol 'O'?",
        "Who painted the Mona Lisa?"
    };
    
    private static final String[][] OPTIONS = {
        {"London", "Berlin", "Paris", "Madrid"},
        {"Venus", "Mars", "Jupiter", "Saturn"},
        {"Elephant", "Blue Whale", "Giraffe", "Hippopotamus"},
        {"Gold", "Oxygen", "Osmium", "Oganesson"},
        {"Vincent van Gogh", "Pablo Picasso", "Leonardo da Vinci", "Michelangelo"}
    };
    
    public static void main(String[] args) {
        // Set the port for the application
        port(4567);
        
        // Home page route
        get("/", (req, res) -> {
            return "<!DOCTYPE html>" +
                   "<html>" +
                   "<head>" +
                   "    <title>Quiz Application</title>" +
                   "    <style>" +
                   "        body {" +
                   "            font-family: Arial, sans-serif;" +
                   "            max-width: 800px;" +
                   "            margin: 0 auto;" +
                   "            padding: 20px;" +
                   "            background-color: #f5f5f5;" +
                   "        }" +
                   "        .container {" +
                   "            background-color: white;" +
                   "            padding: 30px;" +
                   "            border-radius: 10px;" +
                   "            box-shadow: 0 0 10px rgba(0,0,0,0.1);" +
                   "            text-align: center;" +
                   "        }" +
                   "        h1 {" +
                   "            color: #333;" +
                   "        }" +
                   "        .btn {" +
                   "            background-color: #4CAF50;" +
                   "            color: white;" +
                   "            padding: 15px 30px;" +
                   "            text-decoration: none;" +
                   "            border-radius: 5px;" +
                   "            font-size: 18px;" +
                   "            margin-top: 20px;" +
                   "            display: inline-block;" +
                   "        }" +
                   "        .btn:hover {" +
                   "            background-color: #45a049;" +
                   "        }" +
                   "    </style>" +
                   "</head>" +
                   "<body>" +
                   "    <div class=\"container\">" +
                   "        <h1>Welcome to the Quiz Application</h1>" +
                   "        <p>Test your knowledge with our 5-question General Knowledge quiz!</p>" +
                   "        <a href=\"/register\" class=\"btn\">Register/Login</a>" +
                   "    </div>" +
                   "</body>" +
                   "</html>";
        });
        
        // Registration page route
        get("/register", (req, res) -> {
            return "<!DOCTYPE html>" +
                   "<html>" +
                   "<head>" +
                   "    <title>User Registration</title>" +
                   "    <style>" +
                   "        body {" +
                   "            font-family: Arial, sans-serif;" +
                   "            max-width: 800px;" +
                   "            margin: 0 auto;" +
                   "            padding: 20px;" +
                   "            background-color: #f5f5f5;" +
                   "        }" +
                   "        .container {" +
                   "            background-color: white;" +
                   "            padding: 30px;" +
                   "            border-radius: 10px;" +
                   "            box-shadow: 0 0 10px rgba(0,0,0,0.1);" +
                   "        }" +
                   "        h1 {" +
                   "            color: #333;" +
                   "            text-align: center;" +
                   "        }" +
                   "        .form-group {" +
                   "            margin-bottom: 20px;" +
                   "        }" +
                   "        label {" +
                   "            display: block;" +
                   "            margin-bottom: 5px;" +
                   "            font-weight: bold;" +
                   "        }" +
                   "        input, select {" +
                   "            width: 100%;" +
                   "            padding: 10px;" +
                   "            border: 1px solid #ddd;" +
                   "            border-radius: 5px;" +
                   "            box-sizing: border-box;" +
                   "        }" +
                   "        .btn {" +
                   "            background-color: #4CAF50;" +
                   "            color: white;" +
                   "            padding: 15px 30px;" +
                   "            text-decoration: none;" +
                   "            border-radius: 5px;" +
                   "            font-size: 18px;" +
                   "            margin-top: 20px;" +
                   "            display: inline-block;" +
                   "            border: none;" +
                   "            cursor: pointer;" +
                   "            width: 100%;" +
                   "        }" +
                   "        .btn:hover {" +
                   "            background-color: #45a049;" +
                   "        }" +
                   "    </style>" +
                   "</head>" +
                   "<body>" +
                   "    <div class=\"container\">" +
                   "        <h1>User Registration</h1>" +
                   "        <form action=\"/register\" method=\"post\">" +
                   "            <div class=\"form-group\">" +
                   "                <label for=\"name\">Full Name:</label>" +
                   "                <input type=\"text\" id=\"name\" name=\"name\" required>" +
                   "            </div>" +
                   "            <div class=\"form-group\">" +
                   "                <label for=\"usn\">USN (University Seat Number):</label>" +
                   "                <input type=\"text\" id=\"usn\" name=\"usn\" required>" +
                   "            </div>" +
                   "            <div class=\"form-group\">" +
                   "                <label for=\"branch\">Branch:</label>" +
                   "                <select id=\"branch\" name=\"branch\" required>" +
                   "                    <option value=\"\">Select Branch</option>" +
                   "                    <option value=\"Computer Science\">Computer Science</option>" +
                   "                    <option value=\"Information Science\">Information Science</option>" +
                   "                    <option value=\"Electronics & Communication\">Electronics & Communication</option>" +
                   "                    <option value=\"Mechanical Engineering\">Mechanical Engineering</option>" +
                   "                    <option value=\"Civil Engineering\">Civil Engineering</option>" +
                   "                    <option value=\"Electrical Engineering\">Electrical Engineering</option>" +
                   "                </select>" +
                   "            </div>" +
                   "            <div class=\"form-group\">" +
                   "                <label for=\"specialization\">Specialization:</label>" +
                   "                <select id=\"specialization\" name=\"specialization\" required>" +
                   "                    <option value=\"\">Select Specialization</option>" +
                   "                    <option value=\"Software Engineering\">Software Engineering</option>" +
                   "                    <option value=\"Data Science\">Data Science</option>" +
                   "                    <option value=\"Artificial Intelligence\">Artificial Intelligence</option>" +
                   "                    <option value=\"Cyber Security\">Cyber Security</option>" +
                   "                    <option value=\"Machine Learning\">Machine Learning</option>" +
                   "                    <option value=\"Web Development\">Web Development</option>" +
                   "                </select>" +
                   "            </div>" +
                   "            <input type=\"submit\" value=\"Register & Start Quiz\" class=\"btn\">" +
                   "        </form>" +
                   "    </div>" +
                   "</body>" +
                   "</html>";
        });
        
        // Handle registration form submission
        post("/register", (req, res) -> {
            String name = req.queryParams("name");
            String usn = req.queryParams("usn");
            String branch = req.queryParams("branch");
            String specialization = req.queryParams("specialization");
            
            // Store user data in memory (in a real app, you'd save to a database)
            Map<String, String> userData = new HashMap<>();
            userData.put("name", name);
            userData.put("usn", usn);
            userData.put("branch", branch);
            userData.put("specialization", specialization);
            users.put(usn, userData);
            
            // Store user session data
            req.session().attribute("user", userData);
            
            // Redirect to quiz page
            res.redirect("/quiz");
            return null;
        });
        
        // Quiz page route (protected - requires registration)
        get("/quiz", (req, res) -> {
            // Check if user is registered
            Map<String, String> user = req.session().attribute("user");
            if (user == null) {
                res.redirect("/");
                return null;
            }
            
            StringBuilder quizHtml = new StringBuilder();
            quizHtml.append("<!DOCTYPE html>");
            quizHtml.append("<html>");
            quizHtml.append("<head>");
            quizHtml.append("    <title>Quiz Page</title>");
            quizHtml.append("    <style>");
            quizHtml.append("        body {");
            quizHtml.append("            font-family: Arial, sans-serif;");
            quizHtml.append("            max-width: 800px;");
            quizHtml.append("            margin: 0 auto;");
            quizHtml.append("            padding: 20px;");
            quizHtml.append("            background-color: #f5f5f5;");
            quizHtml.append("        }");
            quizHtml.append("        .container {");
            quizHtml.append("            background-color: white;");
            quizHtml.append("            padding: 30px;");
            quizHtml.append("            border-radius: 10px;");
            quizHtml.append("            box-shadow: 0 0 10px rgba(0,0,0,0.1);");
            quizHtml.append("        }");
            quizHtml.append("        h1 {");
            quizHtml.append("            color: #333;");
            quizHtml.append("            text-align: center;");
            quizHtml.append("        }");
            quizHtml.append("        .user-info {");
            quizHtml.append("            background-color: #e9f7ef;");
            quizHtml.append("            padding: 15px;");
            quizHtml.append("            border-radius: 5px;");
            quizHtml.append("            margin-bottom: 20px;");
            quizHtml.append("        }");
            quizHtml.append("        .question {");
            quizHtml.append("            margin-bottom: 20px;");
            quizHtml.append("            padding: 15px;");
            quizHtml.append("            border: 1px solid #ddd;");
            quizHtml.append("            border-radius: 5px;");
            quizHtml.append("        }");
            quizHtml.append("        .question-label {");
            quizHtml.append("            font-weight: bold;");
            quizHtml.append("            margin-bottom: 10px;");
            quizHtml.append("        }");
            quizHtml.append("        .option {");
            quizHtml.append("            margin: 5px 0;");
            quizHtml.append("        }");
            quizHtml.append("        .btn {");
            quizHtml.append("            background-color: #4CAF50;");
            quizHtml.append("            color: white;");
            quizHtml.append("            padding: 15px 30px;");
            quizHtml.append("            text-decoration: none;");
            quizHtml.append("            border-radius: 5px;");
            quizHtml.append("            font-size: 18px;");
            quizHtml.append("            margin-top: 20px;");
            quizHtml.append("            display: inline-block;");
            quizHtml.append("            border: none;");
            quizHtml.append("            cursor: pointer;");
            quizHtml.append("        }");
            quizHtml.append("        .btn:hover {");
            quizHtml.append("            background-color: #45a049;");
            quizHtml.append("        }");
            quizHtml.append("    </style>");
            quizHtml.append("</head>");
            quizHtml.append("<body>");
            quizHtml.append("    <div class=\"container\">");
            quizHtml.append("        <div class=\"user-info\">");
            quizHtml.append("            <strong>Name:</strong> " + user.get("name") + " | ");
            quizHtml.append("            <strong>USN:</strong> " + user.get("usn") + " | ");
            quizHtml.append("            <strong>Branch:</strong> " + user.get("branch") + " | ");
            quizHtml.append("            <strong>Specialization:</strong> " + user.get("specialization"));
            quizHtml.append("        </div>");
            quizHtml.append("        <h1>General Knowledge Quiz</h1>");
            quizHtml.append("        <form action=\"/result\" method=\"post\">");
            
            for (int i = 0; i < 5; i++) {
                quizHtml.append("            <div class=\"question\">");
                quizHtml.append("                <div class=\"question-label\">Question " + (i+1) + ": " + QUESTIONS[i] + "</div>");
                for (int j = 0; j < 4; j++) {
                    String optionId = "q" + (i+1) + "_" + OPTIONS[i][j];
                    quizHtml.append("                <div class=\"option\">");
                    quizHtml.append("                    <input type=\"radio\" name=\"q" + (i+1) + "\" value=\"" + OPTIONS[i][j] + "\" id=\"" + optionId + "\" required>");
                    quizHtml.append("                    <label for=\"" + optionId + "\">" + OPTIONS[i][j] + "</label>");
                    quizHtml.append("                </div>");
                }
                quizHtml.append("            </div>");
            }
            
            quizHtml.append("            <input type=\"submit\" value=\"Submit Answers\" class=\"btn\">");
            quizHtml.append("        </form>");
            quizHtml.append("    </div>");
            quizHtml.append("</body>");
            quizHtml.append("</html>");
            
            return quizHtml.toString();
        });
        
        // Result page route (POST)
        post("/result", (req, res) -> {
            // Check if user is registered
            Map<String, String> user = req.session().attribute("user");
            if (user == null) {
                res.redirect("/");
                return null;
            }
            
            // Get answers from form
            String[] userAnswers = new String[5];
            for (int i = 0; i < 5; i++) {
                userAnswers[i] = req.queryParams("q" + (i+1));
            }
            
            // Calculate score
            int score = 0;
            for (int i = 0; i < 5; i++) {
                if (userAnswers[i] != null && userAnswers[i].equals(CORRECT_ANSWERS[i])) {
                    score++;
                }
            }
            
            StringBuilder resultHtml = new StringBuilder();
            resultHtml.append("<!DOCTYPE html>");
            resultHtml.append("<html>");
            resultHtml.append("<head>");
            resultHtml.append("    <title>Quiz Results</title>");
            resultHtml.append("    <style>");
            resultHtml.append("        body {");
            resultHtml.append("            font-family: Arial, sans-serif;");
            resultHtml.append("            max-width: 800px;");
            resultHtml.append("            margin: 0 auto;");
            resultHtml.append("            padding: 20px;");
            resultHtml.append("            background-color: #f5f5f5;");
            resultHtml.append("        }");
            resultHtml.append("        .container {");
            resultHtml.append("            background-color: white;");
            resultHtml.append("            padding: 30px;");
            resultHtml.append("            border-radius: 10px;");
            resultHtml.append("            box-shadow: 0 0 10px rgba(0,0,0,0.1);");
            resultHtml.append("        }");
            resultHtml.append("        h1 {");
            resultHtml.append("            color: #333;");
            resultHtml.append("            text-align: center;");
            resultHtml.append("        }");
            resultHtml.append("        .user-info {");
            resultHtml.append("            background-color: #e9f7ef;");
            resultHtml.append("            padding: 15px;");
            resultHtml.append("            border-radius: 5px;");
            resultHtml.append("            margin-bottom: 20px;");
            resultHtml.append("        }");
            resultHtml.append("        .score {");
            resultHtml.append("            text-align: center;");
            resultHtml.append("            font-size: 24px;");
            resultHtml.append("            margin: 20px 0;");
            resultHtml.append("        }");
            resultHtml.append("        .passed {");
            resultHtml.append("            color: green;");
            resultHtml.append("        }");
            resultHtml.append("        .failed {");
            resultHtml.append("            color: red;");
            resultHtml.append("        }");
            resultHtml.append("        .result-item {");
            resultHtml.append("            margin-bottom: 15px;");
            resultHtml.append("            padding: 10px;");
            resultHtml.append("            border: 1px solid #ddd;");
            resultHtml.append("            border-radius: 5px;");
            resultHtml.append("        }");
            resultHtml.append("        .correct {");
            resultHtml.append("            border-left: 5px solid green;");
            resultHtml.append("        }");
            resultHtml.append("        .incorrect {");
            resultHtml.append("            border-left: 5px solid red;");
            resultHtml.append("        }");
            resultHtml.append("        .btn {");
            resultHtml.append("            background-color: #4CAF50;");
            resultHtml.append("            color: white;");
            resultHtml.append("            padding: 15px 30px;");
            resultHtml.append("            text-decoration: none;");
            resultHtml.append("            border-radius: 5px;");
            resultHtml.append("            font-size: 18px;");
            resultHtml.append("            margin-top: 20px;");
            resultHtml.append("            display: inline-block;");
            resultHtml.append("        }");
            resultHtml.append("        .btn:hover {");
            resultHtml.append("            background-color: #45a049;");
            resultHtml.append("        }");
            resultHtml.append("        .back-btn {");
            resultHtml.append("            background-color: #2196F3;");
            resultHtml.append("        }");
            resultHtml.append("        .back-btn:hover {");
            resultHtml.append("            background-color: #1976D2;");
            resultHtml.append("        }");
            resultHtml.append("    </style>");
            resultHtml.append("</head>");
            resultHtml.append("<body>");
            resultHtml.append("    <div class=\"container\">");
            resultHtml.append("        <div class=\"user-info\">");
            resultHtml.append("            <strong>Name:</strong> " + user.get("name") + " | ");
            resultHtml.append("            <strong>USN:</strong> " + user.get("usn") + " | ");
            resultHtml.append("            <strong>Branch:</strong> " + user.get("branch") + " | ");
            resultHtml.append("            <strong>Specialization:</strong> " + user.get("specialization"));
            resultHtml.append("        </div>");
            resultHtml.append("        <h1>Quiz Results</h1>");
            
            resultHtml.append("        <div class=\"score\">");
            resultHtml.append("            Your Score: <span class=\"").append(score >= 3 ? "passed" : "failed").append("\">").append(score).append("/5</span>");
            resultHtml.append("        </div>");
            
            resultHtml.append("        <div class=\"results\">");
            
            for (int i = 0; i < 5; i++) {
                boolean isCorrect = userAnswers[i] != null && userAnswers[i].equals(CORRECT_ANSWERS[i]);
                resultHtml.append("            <div class=\"result-item ").append(isCorrect ? "correct" : "incorrect").append("\">");
                resultHtml.append("                <strong>Question ").append(i+1).append(":</strong> ").append(QUESTIONS[i]).append("<br>");
                if (isCorrect) {
                    resultHtml.append("                <span class=\"passed\">✓ Correct! Your answer: ").append(userAnswers[i]).append("</span>");
                } else {
                    resultHtml.append("                <span class=\"failed\">✗ Incorrect. Your answer: ").append(userAnswers[i] != null ? userAnswers[i] : "No answer").append(". Correct answer: ").append(CORRECT_ANSWERS[i]).append("</span>");
                }
                resultHtml.append("            </div>");
            }
            
            boolean passed = score >= 3;
            resultHtml.append("        </div>");
            resultHtml.append("        <div style=\"text-align: center;\">");
            resultHtml.append("            <p style=\"font-size: 20px;").append(passed ? "color: green;" : "color: red;").append("\">").append(passed ? "Congratulations! You passed!" : "Better luck next time!").append("</p>");
            resultHtml.append("            <a href=\"/quiz\" class=\"btn\">Retake Quiz</a>");
            resultHtml.append("            <a href=\"/\" class=\"btn back-btn\">Home</a>");
            resultHtml.append("        </div>");
            resultHtml.append("    </div>");
            resultHtml.append("</body>");
            resultHtml.append("</html>");
            
            return resultHtml.toString();
        });
    }
}