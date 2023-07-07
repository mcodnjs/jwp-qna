package qna.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestConstructor;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.context.TestConstructor.AutowireMode.ALL;

@TestConstructor(autowireMode = ALL)
@DataJpaTest
class UserRepositoryTest {

    private final UserRepository userRepository;

    public UserRepositoryTest(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Test
    void findByUserId() {
        // given
        User expect = userRepository.save(UserTest.JAVAJIGI);// DB 저장되고 1차 캐시에 저장됨

        // when
        Optional<User> actual = userRepository.findByUserId(expect.getUserId());

        // then
        assertThat(actual.get()).isEqualTo(expect);
    }
}
