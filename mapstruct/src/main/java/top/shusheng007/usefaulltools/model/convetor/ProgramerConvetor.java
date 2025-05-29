package top.shusheng007.usefaulltools.model.convetor;

import org.mapstruct.*;
import top.shusheng007.usefaulltools.model.Address;
import top.shusheng007.usefaulltools.model.AddressDto;
import top.shusheng007.usefaulltools.model.Programer;
import top.shusheng007.usefaulltools.model.ProgramerDto;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {
            GirlFriendMapper.class
        },
        injectionStrategy = InjectionStrategy.FIELD
)
public interface ProgramerConvetor {

//    ProgramerConvetor INSTANCE = Mappers.getMapper(ProgramerConvetor.class);

    @Mapping(target = "height",numberFormat = "#.00")
    @Mapping(target = "beDate", dateFormat = "yyyy-MM-dd HH:mm:ss")
//    @Mapping(target = "name", expression = "java(nameToUp(programer))")
    @Mapping(target = "name", qualifiedByName ={"nameToUp"} )
    @Mapping(target = "girlFriend", source = "programer")
    ProgramerDto toProgramerDto(Programer programer);

    AddressDto toAddressDto(Address addr);

    List<AddressDto> toAddressList(List<Address> addrList);

    @Named("nameToUp")
    default String nameToUp(String name) {
        return name.toUpperCase();
    }

//    default String nameToUp(Programer programer){
//        return Optional.ofNullable(programer)
//                .filter(Objects::nonNull)
//                .map(p->p.getName())
//                .orElse(null)
//                .toUpperCase();
//    }



}
