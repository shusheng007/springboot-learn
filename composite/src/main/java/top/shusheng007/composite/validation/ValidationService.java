package top.shusheng007.composite.validation;

import jakarta.validation.constraints.Size;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Validated
@Service
public class ValidationService {

    public BoyFriend queryBoyFriendByName(@Size(min = 1, max = 3) String name) {
        return BoyFriend.builder().name(name).build();
    }
}
