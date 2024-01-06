package stackOverFlow.qa;

import stackOverFlow.user.IUser;

public class Answer implements IAnswer{
    private IUser user;
    private IQuestion question;
    private int votes;

    public Answer(IUser user, IQuestion question) {
        this.user = user;
        this.question = question;
    }

    @Override
    public IUser getUser() {
        return user;
    }

    @Override
    public IQuestion getQuestion() {
        return question;
    }

    @Override
    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }
}
