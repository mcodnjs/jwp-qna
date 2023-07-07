package qna.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestConstructor;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.context.TestConstructor.AutowireMode.ALL;

@TestConstructor(autowireMode = ALL)
@DataJpaTest
class QuestionRepositoryTest {

    private final QuestionRepository questionRepository;

    public QuestionRepositoryTest(final QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Test
    void findByDeletedFalse() {
        // given
        Question expect = questionRepository.save(QuestionTest.Q1);

        // when
        List<Question> actuals = questionRepository.findByDeletedFalse();

        // then
        assertThat(actuals.size()).isEqualTo(1);
        assertThat(actuals.get(0)).isEqualTo(expect);
    }

    @Test
    void findByIdAndDeletedFalse() {
        // given
        Question expect = questionRepository.save(QuestionTest.Q1);

        // when
        Optional<Question> actual = questionRepository.findByIdAndDeletedFalse(expect.getId());

        // then
        assertThat(actual.get()).isEqualTo(expect);
    }
}
