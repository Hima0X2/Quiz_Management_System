import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.nio.file.Files;
import java.util.*;

public class QuizService {
    String quiz_file = "./src/main/resources/quiz.json";
    Scanner sc = new Scanner(System.in);

    public void adminMenu() throws IOException {
        while (true) {
            System.out.print("Press 's' to add question or 'q' to quit: ");
            String input = sc.nextLine();
            if (input.equalsIgnoreCase("q")) {
                System.out.println("Exiting admin mode...");
                break;
            }
            System.out.print("Input your question: ");
            String question = sc.nextLine();
            System.out.print("Input option 1: ");
            String option1 = sc.nextLine();
            System.out.print("Input option 2: ");
            String option2 = sc.nextLine();
            System.out.print("Input option 3: ");
            String option3 = sc.nextLine();
            System.out.print("Input option 4: ");
            String option4 = sc.nextLine();
            System.out.print("What is the answer key (1-4): ");
            int answerKey = sc.nextInt();
            sc.nextLine();
            Question q = new Question(question, option1, option2, option3, option4, answerKey);
            saveQuestion(q);
            System.out.println("Saved successfully!");
        }
    }

    public void saveQuestion(Question question) throws IOException {
        File file = new File(quiz_file);
        JSONArray questionArray;

        if (file.exists()) {
            String content = new String(Files.readAllBytes(file.toPath()));
            questionArray = new JSONArray(content);
        } else {
            questionArray = new JSONArray();
        }
        Map<String, Object> questionMap = new LinkedHashMap<>();
        questionMap.put("question", question.getQuestion());
        questionMap.put("option1", question.getOption1());
        questionMap.put("option2", question.getOption2());
        questionMap.put("option3", question.getOption3());
        questionMap.put("option4", question.getOption4());
        questionMap.put("answerkey", question.getAnswerkey());

        JSONObject obj = new JSONObject(questionMap);
        questionArray.put(obj);

        try (FileWriter writer = new FileWriter(file)) {
            writer.write(questionArray.toString(4)); // Pretty print with indent
        }
    }

    public List<Question> loadQuestions() throws IOException {
        File file = new File(quiz_file);
        List<Question> questionList = new ArrayList<>();

        if (!file.exists()) {
            return questionList;
        }

        String content = new String(Files.readAllBytes(file.toPath()));
        JSONArray array = new JSONArray(content);

        for (int i = 0; i < array.length(); i++) {
            JSONObject obj = array.getJSONObject(i);
            String question = obj.getString("question");
            String option1 = obj.getString("option1");
            String option2 = obj.getString("option2");
            String option3 = obj.getString("option3");
            String option4 = obj.getString("option4");
            int answerKey = obj.getInt("answerkey");

            Question q = new Question(question, option1, option2, option3, option4, answerKey);
            questionList.add(q);
        }

        Collections.shuffle(questionList);
        return questionList.subList(0, Math.min(10, questionList.size()));
    }

    public void startQuiz() throws Exception {
        List<Question> questions = loadQuestions();
        if (questions.size() < 1) {
            System.out.println("Question bank is empty. Contact admin.");
            return;
        }
        Collections.shuffle(questions);
        int score = 0;
        int total = Math.min(10, questions.size());

        for (int i = 0; i < total; i++) {
            Question q = questions.get(i);
            System.out.println("Question " + (i + 1) + ": " + q.getQuestion());
            System.out.println("1. " + q.getOption1());
            System.out.println("2. " + q.getOption2());
            System.out.println("3. " + q.getOption3());
            System.out.println("4. " + q.getOption4());
            System.out.print("Your answer (1-4): ");

            int ans = Integer.parseInt(sc.nextLine());
            if (ans == q.getAnswerkey()) {
                score++;
            }
        }

        System.out.println("You have completed the quiz!");
//        System.out.println("Your Score: " + score + " out of " + total);
        if (score >= 8) {
            System.out.println("Excellent! You have got " + score + " out of 10");
        } else if (score >= 5) {
            System.out.println("Good. You have got " + score + " out of 10");
        } else if (score >= 3) {
            System.out.println("Very Poor! You have got " + score + " out of 10");
        } else {
            System.out.println("Very Sorry, You Are Failed. You have got " + score + " out of 10");
        }

        System.out.print("Would you like to start again? Press 's' to start or 'q' to quit: ");
        String choice = sc.nextLine();
        if (choice.equalsIgnoreCase("s")) {
            startQuiz();
        } else {
            System.out.println("Thank you!");
        }
    }
}