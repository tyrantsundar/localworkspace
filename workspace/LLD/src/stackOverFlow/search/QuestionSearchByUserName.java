package stackOverFlow.search;

import stackOverFlow.qa.IQuestion;

import java.util.List;
import java.util.stream.Collectors;

public class QuestionSearchByUserName implements QuestionSearchStrategy {
    private String userName;

    public QuestionSearchByUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public List<IQuestion> searchQuestions(List<IQuestion> allQuestions) {
       return allQuestions.stream()
               .parallel()
               .filter(quest -> quest.getUserName().equalsIgnoreCase(userName))
               .collect(Collectors.toList());
    }
}
