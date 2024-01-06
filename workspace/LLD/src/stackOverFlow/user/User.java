package stackOverFlow.user;

public class User implements IUser{
    private String name;
    private UserType userType;
    private int reputationPoint;

    public User(String name, UserType userType) {
        this.name = name;
        this.userType = userType;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public UserType getUserType() {
        return userType;
    }

    @Override
    public int getReputationPoint() {
        return reputationPoint;
    }
}
