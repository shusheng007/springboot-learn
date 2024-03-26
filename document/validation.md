```
public class ValidateController {

    @PatchMapping("/boy-friends")
    public ResponseEntity<BoyFriend> updateBoyFriend(@Validated(BoyFriendUpdate.class) @RequestBody BoyFriend boy) {
        log.info("update:{}", boy);
        return ResponseEntity.ok(boy);
    }
 }
```

throw

org.springframework.web.bind.MethodArgumentNotValidException

```
@Validated
public class ValidateController {

    @Validated(BoyFriendUpdate.class)
    @PatchMapping("/boy-friends")
    public ResponseEntity<BoyFriend> updateBoyFriend(@Valid @RequestBody BoyFriend boy) {
        log.info("update:{}", boy);
        return ResponseEntity.ok(boy);
    }
    
    // 需要class 上添加@Validated， 拋出ConstraintViolationException
    @GetMapping("/boy-friends")
    public ResponseEntity<BoyFriend> updateBoyFriend(@NotBlank @RequestParam("name") String name) {
        log.info("get:{}", name);
        return ResponseEntity.ok(BoyFriend.builder().name(name).build());
    }
 }
```

throw:

jakarta.validation.ConstraintViolationException



