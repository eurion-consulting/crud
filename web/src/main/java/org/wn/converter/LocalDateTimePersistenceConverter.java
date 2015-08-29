package org.wn.converter;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class LocalDateTimePersistenceConverter implements AttributeConverter<LocalDateTime, java.sql.Timestamp> {
    @Override
    public Timestamp convertToDatabaseColumn(LocalDateTime entityValue) {
    	//TODO: refactor
    	if(entityValue == null){
        	return null;
        }
    	return Timestamp.valueOf(entityValue);
    }

    @Override
    public LocalDateTime convertToEntityAttribute(Timestamp databaseValue) {
    	//TODO: refactor
    	if(databaseValue == null){
        	return null;
        }
    	return databaseValue.toLocalDateTime();
    }

}