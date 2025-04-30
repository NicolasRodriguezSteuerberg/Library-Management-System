package com.nsteuerberg.library.authentication.service.implementation;

import com.nsteuerberg.library.authentication.persistance.entity.RefreshTokenEntity;
import com.nsteuerberg.library.authentication.persistance.repository.IRefreshRepository;
import com.nsteuerberg.library.authentication.persistance.repository.IUserRepository;
import com.nsteuerberg.library.authentication.presentation.dto.requests.SignInRequest;
import com.nsteuerberg.library.authentication.presentation.dto.requests.SignUpRequest;
import com.nsteuerberg.library.authentication.presentation.dto.responses.TokenAuthenticationResponse;
import com.nsteuerberg.library.authentication.service.interfaces.IAuthService;
import com.nsteuerberg.library.authentication.service.security.CustomUserDetails;
import com.nsteuerberg.library.authentication.util.date.ExpiredDate;
import com.nsteuerberg.library.authentication.util.token.JwtProvider;
import com.nsteuerberg.library.authentication.util.token.RefreshTokenProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements IAuthService {

    private final IUserRepository userRepository;
    private final IRefreshRepository refreshRepository;
    private final UserDetailServiceImpl userDetailService;
    private final JwtProvider jwtProvider;
    private final RefreshTokenProvider refreshProvider;
    private final ExpiredDate expiredDate;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(IUserRepository userRepository, IRefreshRepository refreshRepository, UserDetailServiceImpl userDetailService, JwtProvider jwtProvider, RefreshTokenProvider refreshProvider, ExpiredDate expiredDate, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.refreshRepository = refreshRepository;
        this.userDetailService = userDetailService;
        this.jwtProvider = jwtProvider;
        this.refreshProvider = refreshProvider;
        this.expiredDate = expiredDate;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public TokenAuthenticationResponse login(SignInRequest signInRequest, String deviceId) {
        CustomUserDetails userDetails = (CustomUserDetails) userDetailService.loadUserByUsername(signInRequest.username());
        if (!passwordEncoder.matches(signInRequest.password(), userDetails.getPassword())) {
            throw new BadCredentialsException("Contraseña incorrecta");
        }
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetails.getUsername(),
                userDetails.getPassword(),
                userDetails.getAuthorities()
        );

        TokenAuthenticationResponse tokens = generateTokens(authentication);
        saveOrUpdateToken(tokens, deviceId, userDetails.getId());

        return tokens;
    }

    @Override
    public TokenAuthenticationResponse register(SignUpRequest signUpRequest, String deviceId) {

        // userRepository.save();
        return null;
    }

    private TokenAuthenticationResponse generateTokens (Authentication authentication) {
        String accessToken = jwtProvider.createToken(authentication);
        String refreshToken = refreshProvider.generateToken();
        return new TokenAuthenticationResponse(refreshToken, accessToken);
    }

    private void saveOrUpdateToken(TokenAuthenticationResponse tokens, String deviceId, Long userId) {
        refreshRepository.findByUserIdAndDeviceId(userId, deviceId).ifPresentOrElse(
                token -> {
                    token.setToken(tokens.refreshToken());
                    token.setExpiredDate(expiredDate.getExpiredDate());
                    refreshRepository.save(token);
                },
                () -> {
                    RefreshTokenEntity refreshEntity = RefreshTokenEntity.builder()
                            .deviceId(deviceId)
                            .userId(userId)
                            .token(tokens.refreshToken())
                            .expiredDate(expiredDate.getExpiredDate())
                            .build();
                    refreshRepository.save(refreshEntity);
                }
        );
    }
}
