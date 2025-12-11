#!/usr/bin/env python3
"""
Quiz Application - Questions and Answers

This script contains the questions and answers for the quiz application
in Python format as requested.
"""

# Quiz questions (first 5 questions only)
questions = [
    "What is the capital of France?",
    "Which planet is known as the Red Planet?",
    "What is the largest mammal?",
    "Which element has the chemical symbol 'O'?",
    "Who painted the Mona Lisa?"
]

# Options for each question (first 5 questions only)
options = [
    ["London", "Berlin", "Paris", "Madrid"],
    ["Venus", "Mars", "Jupiter", "Saturn"],
    ["Elephant", "Blue Whale", "Giraffe", "Hippopotamus"],
    ["Gold", "Oxygen", "Osmium", "Oganesson"],
    ["Vincent van Gogh", "Pablo Picasso", "Leonardo da Vinci", "Michelangelo"]
]

# Correct answers for each question (first 5 questions only)
correct_answers = [
    "Paris",           # Question 1
    "Mars",            # Question 2
    "Blue Whale",      # Question 3
    "Oxygen",          # Question 4
    "Leonardo da Vinci"  # Question 5
]

# Function to display all questions and answers
def display_quiz():
    """Display all quiz questions, options, and correct answers"""
    print("=== General Knowledge Quiz (5 Questions) ===\n")
    
    for i in range(len(questions)):
        print(f"Question {i+1}: {questions[i]}")
        print("Options:")
        for j, option in enumerate(options[i]):
            print(f"  {chr(97+j)}) {option}")  # a, b, c, d
        print(f"Correct Answer: {correct_answers[i]}\n")

# Function to check if an answer is correct
def check_answer(question_number, user_answer):
    """
    Check if the user's answer is correct
    
    Args:
        question_number (int): The question number (0-4)
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