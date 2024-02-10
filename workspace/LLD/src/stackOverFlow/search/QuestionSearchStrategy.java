package stackOverFlow.search;

import stackOverFlow.qa.IQuestion;

import java.util.List;

public interface QuestionSearchStrategy {
    public List<IQuestion> searchQuestions(List<IQuestion> allQuestions);

}
