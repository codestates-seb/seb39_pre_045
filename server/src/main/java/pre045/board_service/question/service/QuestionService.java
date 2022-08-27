package pre045.board_service.question.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pre045.board_service.question.repository.QuestionRepository;

@Transactional
@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }







}
