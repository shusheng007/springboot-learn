package top.shusheng007.architecturedemo.application.dto.web.request;

import lombok.Getter;
import lombok.Setter;
import top.shusheng007.architecturedemo.application.dto.ProductItemDto;

import java.util.List;

@Setter
@Getter
public class OrderCreateRequest {
    private List<ProductItemDto> products;
}
