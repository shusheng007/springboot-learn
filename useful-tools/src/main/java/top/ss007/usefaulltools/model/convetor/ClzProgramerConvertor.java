package top.ss007.usefaulltools.model.convetor;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.springframework.beans.factory.annotation.Autowired;
import top.ss007.usefaulltools.model.Programer;
import top.ss007.usefaulltools.model.ProgramerDto;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class ClzProgramerConvertor {
    @Autowired
    protected GirlFriendMapper girlFriendMapper;

    @Mapping(target = "height",numberFormat = "#.00")
    @Mapping(target = "beDate", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(target = "name", expression = "java(programer.getName().toUpperCase())")
    @Mapping(target = "girlFriend", expression = "java(girlFriendMapper.toGirlFriendDto(programer))")
    public abstract ProgramerDto toProgramerDto(Programer programer);
}
