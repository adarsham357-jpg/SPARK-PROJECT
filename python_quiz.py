#!/usr/bin/env python3
"""
Python Quiz - 6 Fundamental Questions

This script contains 6 Python fundamental questions with answers.
"""

# Python quiz questions
questions = [
    "What is the correct file extension for Python files?",
    "Which keyword is used to define a function in Python?",
    "Which data type is used to store True/False values in Python?",
    "What symbol is used to start a comment in Python?",
    "Which of the following is a Python list?",
    "Which of the following is used to display output in Python?"
]

# Options for each question
options = [
    [".py", ".java", ".cpp", ".txt"],
    ["func", "define", "def", "fn"],
    ["string", "boolean", "tuple", "float"],
    ["//", "#", "<!--", "/*"],
    ["{1, 2, 3}", "(1, 2, 3)", "[1, 2, 3]", "<1, 2, 3>"],
    ["print", "echo", "output", "write"]
]

# Correct answers for each question
correct_answers = [
    ".py",      # Question 1
    "def",      # Question 2
    "boolean",  # Question 3
    "#",        # Question 4
    "[1, 2, 3]", # Question 5
    "print"     # Question 6
]

def display_quiz():
    """Display all quiz questions, options, and correct answers"""
    print("=== Python Fundamentals Quiz ===\n")
    
    for i in range(len(questions)):
        print(f"Question {i+1}: {questions[i]}")
        print("Options:")
        for j, option in enumerate(options[i]):
            print(f"  {chr(97+j)}) {option}")  # a, b, c, d
        print(f"Correct Answer: {correct_answers[i]}\n")

def check_answer(question_number, user_answer):
    """
    Check if the user's answer is correct
    
    Args:
        question_number (int): The question number (0-5)
        user_answer (str): The user's answer
        
    Returns:
        bool: True if correct, False otherwise
    """
    if 0 <= question_number < len(correct_answers):
        return user_answer.lower() == correct_answers[question_number].lower()
    return False

# Example usage
if __name__ == "__main__":
    # Display all questions and answers
    display_quiz()
    
    # Example of checking an answer
    print("=== Answer Checking Example ===")
    user_input = input("Enter your answer for Question 1: ")
    if check_answer(0, user_input):
        print("Correct!")
    else:
        print(f"Incorrect. The correct answer is: {correct_answers[0]}")