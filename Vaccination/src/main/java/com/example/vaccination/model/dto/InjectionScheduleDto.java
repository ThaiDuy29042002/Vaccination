package com.example.vaccination.model.dto;

import com.example.vaccination.model.entity.Vaccine;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InjectionScheduleDto implements Serializable {

    private int injectionScheduleID;
    private String endDate;
    private String place;
    private int status; //1 = Not yet //2 = Open //3 = Over
    private String note;

    private String startDate;
    private Vaccine vaccine_s;
}
