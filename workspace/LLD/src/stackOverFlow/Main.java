package stackOverFlow;

import stackOverFlow.user.User;
import stackOverFlow.user.UserType;

public class Main {
    public static void main(String[] args) {
        User admin = new User("admin", UserType.Admin);
        User member = new User("member",UserType.Member);
        User guest = new User("guest",UserType.Guest);
        User moderator = new User("moderator",UserType.Moderator);



    }
}
