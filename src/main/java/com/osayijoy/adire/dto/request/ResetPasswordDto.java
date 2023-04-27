package com.osayijoy.adire.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResetPasswordDto {
    @NotBlank
    private String token;
    @NotBlank
    private String password;
    @NotBlank
    private String confirmPassword;
}
