package top.shusheng007.usefaulltools.model.convetor;

import org.springframework.stereotype.Component;
import top.shusheng007.usefaulltools.model.GirlFriendDto;
import top.shusheng007.usefaulltools.model.Programer;

@Component
public class GirlFriendMapper {

    public GirlFriendDto toGirlFriendDto(Programer programer) {
        GirlFriendDto girlFriendDto = new GirlFriendDto();
        girlFriendDto.setName(programer.getName());
        girlFriendDto.setDescription(programer.getGirlDes());
        return girlFriendDto;
    }
}
