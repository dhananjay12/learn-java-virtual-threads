package com.djcodes.test.containers.crudservice.dto;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Calendar;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EmployeeDTO {


    private Integer id;

    @NotBlank(message = "First name can not be null!")
    private String fname;

    @NotBlank(message = "Last name can not be null!")
    private String lname;

    @NotBlank(message = "Email can not be null!")
    @Email(message = "Invalid Email")
    private String email;

    @JsonFormat(pattern="dd-MM-yyyy")
    private Calendar dob;

}
