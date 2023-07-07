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

    public AnswerRepositoryTest(final AnswerRepository answers) {
        this.answerRepository = answers;
    }

    @DisplayName("")
    @Test
    void findByIdAndDeletedFalse() {
        // given
        Answer expect = answerRepository.save(AnswerTest.A1);

        // when
        System.out.println("=========");
        Optional<Answer> actual = answerRepository.findByIdAndDeletedFalse(expect.getId());
        System.out.println("=========");

        // then
        assertThat(actual.get()).isEqualTo(expect);
    }

    @DisplayName("")
    @Test
    void findByQuestionIdAndDeletedFalse() {
        // given
        List<Answer> before = answerRepository.findByQuestionIdAndDeletedFalse(AnswerTest.A1.getQuestionId());
        answerRepository.save(AnswerTest.A1);
        answerRepository.save(AnswerTest.A2);

        // when
        List<Answer> after = answerRepository.findByQuestionIdAndDeletedFalse(AnswerTest.A1.getQuestionId());

        // then
        assertThat(after.size()-before.size()).isEqualTo(2);
    }
}
