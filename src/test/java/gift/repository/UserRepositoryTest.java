package gift.repository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import gift.domain.User;
import gift.repository.user.UserRepository;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    void save() {
        // given
        User user = new User("kakao", "kakao@google.com", "password", "BE");
        // when
        User savedUser = userRepository.save(user);
        // then
        Assertions.assertAll(
            () -> assertThat(savedUser.getId()).isNotNull(),
            () -> assertThat(savedUser.getEmail()).isEqualTo(user.getEmail())
        );
    }

    @Test
    void findbyid() {
        // given
        Long id = 2L;
        User user1 = new User("kakao", "kakao@google.com", "password", "BE");
        User user2 = new User("name", "name@google.com", "password", "BE");
        userRepository.save(user1);
        userRepository.save(user2);
        // when
        Optional<User> findUser = userRepository.findById(id);
        Long findId = findUser.get().getId();;
        // then
        assertThat(findId).isEqualTo(id);
    }


}
