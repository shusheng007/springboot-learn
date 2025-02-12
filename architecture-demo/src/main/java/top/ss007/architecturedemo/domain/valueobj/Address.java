package top.ss007.architecturedemo.domain.valueobj;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
public class Address {
    private final String street;
    private final String city;
    private final String zipCode;
}
