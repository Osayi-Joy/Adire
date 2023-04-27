package com.osayijoy.adire.dto.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DesignerDTO {
    @NotBlank
    private String firstName;
    @NotBlank
    private  String lastName;
    @Email
    private String email;

    private String url;
    @NotBlank
    private  String phoneNumber;
    @NotBlank
    private  String password;
    @NotBlank
    private  String confirmPassword;

}
