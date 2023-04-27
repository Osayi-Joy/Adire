package com.osayijoy.adire.service;

import com.osayijoy.adire.dto.request.ForgotPasswordDto;
import com.osayijoy.adire.dto.request.ResetPasswordRequest;
import com.osayijoy.adire.dto.response.MessageResponse;

public interface AuthService {
    MessageResponse resetUserSecurityDetails(ResetPasswordRequest resetPasswordRequest);

    String passwordRequest(ForgotPasswordDto forgotPasswordDto);
}
