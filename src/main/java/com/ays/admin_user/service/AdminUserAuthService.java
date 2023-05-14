package com.ays.admin_user.service;

import com.ays.auth.model.AysToken;
import com.ays.auth.model.dto.request.AysLoginRequest;

/**
 * Admin Auth service to perform admin user related authentication operations.
 */
public interface AdminUserAuthService {

    /**
     * Login to platform.
     *
     * @param loginRequest the AysLoginRequest entity
     * @return AysToken
     */
    AysToken authenticate(AysLoginRequest loginRequest);

    /**
     * Refresh a Token
     *
     * @param refreshToken the refreshToken text
     * @return AysToken
     */
    AysToken refreshAccessToken(String refreshToken);

}
