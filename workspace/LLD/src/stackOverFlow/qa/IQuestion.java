package stackOverFlow.qa;

import stackOverFlow.user.IUser;

import java.util.List;

public interface IQuestion {
    public IUser getUser();
    public List<IAnswer> getAnswers();
}
