package org.wn.converter;

import java.time.LocalDate;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Date;

@Converter(autoApply = true)
public class LocalDatePersistenceConverter implements AttributeConverter<LocalDate, Date> {
    
	@Override
    public Date convertToDatabaseColumn(LocalDate entityValue) {
        
		//TODO: refactor
		if(entityValue == null){
        	return null;
        }
		return Date.valueOf(entityValue);
    }

    @Override
    public LocalDate convertToEntityAttribute(Date databaseValue) {
    	
    	//TODO: refactor
    	if(databaseValue == null){
        	return null;
        }
        return databaseValue.toLocalDate();
    }
}
