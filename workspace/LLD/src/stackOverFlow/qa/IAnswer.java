package stackOverFlow.qa;

import stackOverFlow.user.IUser;

public interface IAnswer {
    public IUser getUser();
    public IQuestion getQuestion();
    public int getVotes();
    public void setVotes(int votes);
}
