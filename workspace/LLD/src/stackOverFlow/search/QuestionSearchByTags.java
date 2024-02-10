package stackOverFlow.search;

import stackOverFlow.qa.IQuestion;
import stackOverFlow.qa.QuestionTags;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class QuestionSearchByTags implements QuestionSearchStrategy {
    private Set<QuestionTags> questionTags;

    public QuestionSearchByTags(Set<QuestionTags> questionTags) {
        this.questionTags = questionTags;
    }

    @Override
    public List<IQuestion> searchQuestions(List<IQuestion> allQuestions) {
        return allQuestions.stream()
                .parallel()
                .filter(quest -> questionTags.contains(quest.getQuestionTags()))
                .collect(Collectors.toList());
    }
}
