package top.ss007.composite.validation;


import jakarta.validation.ConstraintViolation;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import jakarta.validation.constraints.NotBlank;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Validated
@Slf4j
@RestController
@RequestMapping("/validation")
public class ValidateController {

    @Autowired
    private ValidationService validationService;

    @Autowired
    private Validator validator;

    @PostMapping("/boy-friends")
    public ResponseEntity<BoyFriend> createBoyFriend(@Valid @RequestBody BoyFriend boy) {
        log.info("create:{}", boy);
        return ResponseEntity.ok(boy);
    }

    @Validated(BoyFriendUpdate.class)
    @PatchMapping("/boy-friends")
    public ResponseEntity<BoyFriend> updateBoyFriend(@Valid @RequestBody BoyFriend boy) {
        log.info("update:{}", boy);
        if (Objects.nonNull(boy.getHouse())) {
            Set<ConstraintViolation<House>> validateResults = validator.validate(boy.getHouse());
            String errMsg = validateResults.stream().map(e -> e.getPropertyPath() + ":" + e.getMessage()).collect(Collectors.joining("| "));

            throw new BusinessException(errMsg, 10002);
        }

        return ResponseEntity.ok(boy);
    }

    @GetMapping("/boy-friends")
    public ResponseEntity<BoyFriend> updateBoyFriend(@NotBlank @RequestParam("name") String name) {
        log.info("get:{}", name);
        return ResponseEntity.ok(validationService.queryBoyFriendByName(name));
    }


}
