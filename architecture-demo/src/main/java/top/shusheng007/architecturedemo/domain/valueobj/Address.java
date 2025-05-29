package top.shusheng007.architecturedemo.domain.valueobj;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Address {
    private final String street;
    private final String city;
    private final String zipCode;
}
