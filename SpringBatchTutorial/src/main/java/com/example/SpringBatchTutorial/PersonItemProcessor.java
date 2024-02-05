package com.example.SpringBatchTutorial;

import com.example.SpringBatchTutorial.entity.SourcePerson;
import com.example.SpringBatchTutorial.entity.TargetPerson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import java.util.Date;

public class PersonItemProcessor implements ItemProcessor<SourcePerson, TargetPerson> {

    private static final Logger log = LoggerFactory.getLogger(PersonItemProcessor.class);

    @Override
    public TargetPerson process(final SourcePerson sourcePerson) throws Exception {
        final int id = sourcePerson.getId();
        final String firstName = sourcePerson.getFirstName().toUpperCase();
        final String lastName = sourcePerson.getLastName().toUpperCase();
        final Date birthDate = (Date) sourcePerson.getBirthDate();

        final TargetPerson transformedSourcePerson = new TargetPerson(firstName, lastName, birthDate);

        log.info("Converting (" + sourcePerson + ") into (" + transformedSourcePerson + ")");

        return transformedSourcePerson;
    }

}