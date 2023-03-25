package top.shusheng007.composite.validation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.shusheng007.composite.validation.custom.TargetMan;

import javax.validation.Valid;
import javax.validation.constraints.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoyFriend {

    @NotBlank
    @TargetMan({"马化腾", "王思聪", "王二狗"})
    private String name;

    @Min(18)
    private Integer age;

    @Min(185)
    private Integer height;

    @Max(groups = BoyFriendCreate.class, value = 85)
    @Max(groups = BoyFriendUpdate.class, value = 60)
    private Integer weight;

    @Email
    private String email;

    @Valid
    @NotNull(groups = BoyFriendCreate.class)
    private House house;

}
