package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestConstructor;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.context.TestConstructor.AutowireMode.ALL;

@TestConstructor(autowireMode = ALL)
@DataJpaTest
class AnswerRepositoryTest {

    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;
    private final  UserRepository userRepository;

    public AnswerRepositoryTest(final AnswerRepository answers, final QuestionRepository questionRepository, final UserRepository userRepository) {
        this.answerRepository = answers;
        this.questionRepository = questionRepository;
        this.userRepository = userRepository;
    }

    @DisplayName("")
    @Test
    void findByIdAndDeletedFalse() {
        // given
        User user = userRepository.save(AnswerTest.A1.getWriter());
        Question question = questionRepository.save(AnswerTest.A1.getQuestion());
        Answer expect = answerRepository.save(AnswerTest.A1);

        // when
        Optional<Answer> actual = answerRepository.findByIdAndDeletedFalse(expect.getId());

        // then
        assertThat(actual.get()).isEqualTo(expect);
    }

    @DisplayName("")
    @Test
    void findByQuestionIdAndDeletedFalse() {
        // given
//        List<Answer> before = answerRepository.findByQuestionAndDeletedFalse(AnswerTest.A1.getQuestion());

        User answerWriter = AnswerTest.A1.getWriter();
        User questionWriter = AnswerTest.A1.getQuestion().getWriter();

        AnswerTest.A1.setWriter(userRepository.save(answerWriter));
        AnswerTest.A1.getQuestion().writeBy(userRepository.save(questionWriter));
        AnswerTest.A1.toQuestion(questionRepository.save(AnswerTest.A1.getQuestion()));

        //User user2 = userRepository.save(AnswerTest.A2.getWriter());
        //Question question2 = questionRepository.save(AnswerTest.A2.getQuestion());


        answerRepository.save(AnswerTest.A1);
        // answerRepository.save(AnswerTest.A2);

        // when
        List<Answer> after = answerRepository.findByQuestionAndDeletedFalse(AnswerTest.A1.getQuestion());

        // then
//        assertThat(after.size()-before.size()).isEqualTo(1);
    }
}
