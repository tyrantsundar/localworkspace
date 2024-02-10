package stackOverFlow.qa;

import stackOverFlow.user.IUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Question implements IQuestion{
    private String userName;
    private List<IAnswer> answersList;
    private Set<QuestionTags> questionTags;

    public Question(String userName,Set<QuestionTags> questionTags) {
        this.userName = userName;
        this.answersList = new ArrayList<>();
        this.questionTags = questionTags;
    }

    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public List<IAnswer> getAnswers() {
        return answersList;
    }

    @Override
    public Set<QuestionTags> getQuestionTags() {
        return this.questionTags;
    }

    @Override
    public void addQuestionTag(QuestionTags questionTags) {
        this.questionTags.add(questionTags);
    }

}
