import java.sql.SQLException;
import java.util.List;

import config.AppConfig;
import entity.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import service.UserService;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);
      UserService userService = context.getBean(UserService.class);

      List<User> users = userService.getAll();
      for (User user : users) {
         System.out.println(user.toString());
      }

      context.close();
   }
}
