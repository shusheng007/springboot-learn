package top.shusheng007.usefaulltools.model.convetor;

import org.mapstruct.*;
import top.shusheng007.usefaulltools.model.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class HumanConvertor {

    @BeforeMapping
    protected void humanDtoWithGender(Human human, @MappingTarget HumanDto humanDto) {
        if (human instanceof Man) {
            humanDto.setGenderType(GenderType.MAN);
        } else if (human instanceof Woman) {
            humanDto.setGenderType(GenderType.WOMAN);
        }
    }

    @AfterMapping
    protected void decorateName(@MappingTarget HumanDto humanDto) {
        humanDto.setName(String.format("【%s】", humanDto.getName()));
    }

    public abstract HumanDto toHumanDto(Human human);
}
