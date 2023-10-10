package com.example.vaccination.handler;

import com.example.vaccination.model.dto.InjectionScheduleDto;
import com.example.vaccination.model.entity.InjectionSchedule;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-10-07T18:19:36+0700",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 20.0.1 (Oracle Corporation)"
)
public class ScheduleMapImpl implements ScheduleMap {

    @Override
    public InjectionSchedule stringToDate(InjectionScheduleDto injectionScheduleDto) {
        if ( injectionScheduleDto == null ) {
            return null;
        }

        InjectionSchedule injectionSchedule = new InjectionSchedule();

        injectionSchedule.setStartDate( stringToDate( injectionScheduleDto.getStartDate() ) );
        injectionSchedule.setEndDate( stringToDate( injectionScheduleDto.getEndDate() ) );
        injectionSchedule.setInjectionScheduleID( injectionScheduleDto.getInjectionScheduleID() );
        injectionSchedule.setPlace( injectionScheduleDto.getPlace() );
        injectionSchedule.setStatus( injectionScheduleDto.getStatus() );
        injectionSchedule.setNote( injectionScheduleDto.getNote() );
        injectionSchedule.setVaccine_s( injectionScheduleDto.getVaccine_s() );

        return injectionSchedule;
    }
}
