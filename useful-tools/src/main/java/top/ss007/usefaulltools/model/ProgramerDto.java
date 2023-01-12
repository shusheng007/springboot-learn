package top.ss007.usefaulltools.model;

import lombok.Data;

import java.util.Date;

@Data
public class ProgramerDto {
    private String name;
    private String proLang;
    private String height;
    private String beDate;
    private AddressDto address;
    private GirlFriendDto girlFriend;

}
