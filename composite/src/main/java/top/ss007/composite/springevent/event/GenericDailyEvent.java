package top.ss007.composite.springevent.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.core.ResolvableType;
import org.springframework.core.ResolvableTypeProvider;

/**
 * Copyright (C) 2023 ShuSheng007
 * <p>
 * Author ShuSheng007
 * Time 2024/1/28 23:18
 * Description
 */
public class GenericDailyEvent<T> extends ApplicationEvent implements ResolvableTypeProvider {
    private T myEvent;

    public GenericDailyEvent(Object source,T myEvent) {
        super(source);
        this.myEvent = myEvent;
    }

    @Override
    public ResolvableType getResolvableType() {
        return ResolvableType.forClassWithGenerics(getClass(),ResolvableType.forInstance(myEvent));
    }

    public T getMyEvent() {
        return myEvent;
    }
}
