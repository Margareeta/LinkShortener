package org.daisy.model;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.concurrent.atomic.AtomicLong;

@Converter
public class AtomicLongConverter implements AttributeConverter <AtomicLong, Long> {
    @Override
    public Long convertToDatabaseColumn(AtomicLong attribute) {
        return attribute.longValue();
    }

    @Override
    public AtomicLong convertToEntityAttribute(Long dbData) {
        return new AtomicLong(dbData);
    }
}
