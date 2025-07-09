import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class UserService {
    public User login(String username, String password) throws IOException {
            String path = "./src/main/resources/users.json";
            File file = new File(path);
            if (!file.exists()) {
                System.out.println("users.json not found.");
                return null;
            }
            String content = new String(Files.readAllBytes(Paths.get(path)));
            JSONArray usersArray = new JSONArray(content);
            for (int i = 0; i < usersArray.length(); i++) {
                JSONObject userObj = usersArray.getJSONObject(i);
                String u = userObj.getString("username");
                String p = userObj.getString("password");
                if (u.equals(username) && p.equals(password)) {
                    String role = userObj.getString("role");
                    return new User(u, p, role);
                }
            }
        return null;
    }
}
