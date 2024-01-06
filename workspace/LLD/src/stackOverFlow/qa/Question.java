package stackOverFlow.qa;

import stackOverFlow.user.IUser;

import java.util.ArrayList;
import java.util.List;

public class Question implements IQuestion{
    private IUser user;
    private List<IAnswer> answersList;

    public Question(IUser user) {
        this.user = user;
        this.answersList = new ArrayList<>();
    }

    @Override
    public IUser getUser() {
        return user;
    }

    @Override
    public List<IAnswer> getAnswers() {
        return answersList;
    }
}
