import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        UserService userService = new UserService();
        QuizService quizService = new QuizService();

        System.out.print("Enter your username: ");
        String username = sc.nextLine();

        System.out.print("Enter your password: ");
        String password = sc.nextLine();

        User user = userService.login(username, password);

        if (user != null) {
            if (user.getRole().equalsIgnoreCase("admin")) {
                System.out.println("Welcome admin! Please create new questions.");
                quizService.adminMenu();
            } else if (user.getRole().equalsIgnoreCase("student")) {
                System.out.println("Welcome " + user.getUsername() + " to the quiz. Each MCQ mark is 1 and no negative marking.");
                System.out.println("Are you ready? Press 's' to start or 'q' to quit.");
                String choice = sc.nextLine();
                if (choice.equalsIgnoreCase("s")) {
                    quizService.startQuiz();
                } else {
                    System.out.println("You quit the exam");
                }
            }
        } else {
            System.out.println("Invalid username or password!");
        }
    }
}
