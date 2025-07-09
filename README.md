#  Quiz Management System

This is a simple Java project that supports a role-based quiz system.

## 👥 User Roles

- **Admin**: Can add multiple-choice questions to the quiz.
- **Student**: Can take a quiz with 10 random questions.

## 📁 Data Storage

- `users.json`: Stores usernames, passwords, and roles.
- `quiz.json`: Stores quiz questions and answers.

## 🔐 Login Credentials

Example data in `users.json`:

```json
[
  { "username": "admin", "password": "1234", "role": "admin" },
  { "username": "salman", "password": "1234", "role": "student" }
]
```
## 🛠️ Features
### Admin
- Login with admin credentials
- Add multiple-choice questions
- Save questions to quiz.json

### Student
- Login with student credentials
- Answer 10 random questions
- See score and result message at the end

## 📊 Score Evaluation
At the end of the quiz, the system shows the result based on your score:

- 8–10: Excellent! You have got [marks] out of 10
- 5–7: Good. You have got [marks] out of 10
- 3–4: Very Poor! You have got [marks] out of 10
- 0–2: Very Sorry, You Are Failed. You have got [marks] out of 10

System: Would you like to start again? Press s to start or q to quit.

## ▶️ How to Run
- Clone or download the project
- Open a terminal in the project folder
- Compile and run the Java program:
```
javac Main.java
java Main
```
## 📽 Demo Video

## Technology Used 
- JAVA
- JSON

## Author
Sanjida Akter Samanta
