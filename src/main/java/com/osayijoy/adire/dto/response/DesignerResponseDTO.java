package com.osayijoy.adire.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DesignerResponseDTO {

    private String firstName;

    private  String lastName;

    private String email;

    private  String phoneNumber;
    private String url;
    
}
