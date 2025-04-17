package com.nsteuerberg.library.authentication.presentation.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignInResponse {
    private String refreshToken;
    private String accessToken;
}
