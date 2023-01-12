package top.ss007.usefaulltools.model.convetor;

import org.springframework.stereotype.Component;
import top.ss007.usefaulltools.model.GirlFriendDto;
import top.ss007.usefaulltools.model.Programer;

@Component
public class GirlFriendMapper {

    public GirlFriendDto toGirlFriendDto(Programer programer) {
        GirlFriendDto girlFriendDto = new GirlFriendDto();
        girlFriendDto.setName(programer.getName());
        girlFriendDto.setDescription(programer.getGirlDes());
        return girlFriendDto;
    }
}
