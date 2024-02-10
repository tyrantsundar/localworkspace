package stackOverFlow.qa;

import stackOverFlow.user.IUser;

import java.util.List;
import java.util.Set;

public interface IQuestion {
    public String getUserName();
    public List<IAnswer> getAnswers();
    public Set<QuestionTags> getQuestionTags();
    public void addQuestionTag(QuestionTags questionTags);
}
