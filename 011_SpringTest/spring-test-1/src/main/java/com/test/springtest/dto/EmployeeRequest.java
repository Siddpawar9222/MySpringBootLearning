package com.test.springtest.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmployeeRequest {
    @NotNull(message = "Name cant be null")
    private String name ;
    @Email(message ="Invalid Email Address" )
    private String email;

    @Pattern(regexp = "^\\d{10}$", message = "Mobile Number must be 10 digit number")
    private String mobileNo;


    private String gender;

    @Min(18)
    @Max(60)
    private  int age ;

    @NotBlank
    private  String nationality ;
}
