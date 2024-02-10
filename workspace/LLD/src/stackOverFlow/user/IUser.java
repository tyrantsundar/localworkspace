package stackOverFlow.user;

public interface IUser {
    public String getName();
    public UserType getUserType();
    public int getReputationPoint();
    public void increaseReputationPoint();
}
