package com.spring.marketplace.utils.converter;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.pattern.CompositeConverter;
import java.util.Arrays;
import java.util.stream.Collectors;

public class MaskingPatternConverter extends CompositeConverter<ILoggingEvent> {

    @Override
    protected String transform(ILoggingEvent iLoggingEvent, String s) {
        if (s.contains("Confidential")) {
            return Arrays.stream(s.split(","))
                    .map((element) -> {
                        if (element.contains("Confidential")) {
                            String string = element.substring(0, element.indexOf("Confidential"));
                            return string + "=****";
                        }

                        return element;
                    }).collect(Collectors.joining(",", "", ")" + System.lineSeparator()));
        }

        return s;
    }
}
