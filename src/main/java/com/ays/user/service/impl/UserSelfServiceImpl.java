package com.ays.user.service.impl;

import com.ays.assignment.repository.AssignmentRepository;
import com.ays.auth.model.AysIdentity;
import com.ays.common.util.validation.EnumValidation;
import com.ays.user.model.dto.request.UserSupportStatusUpdateRequest;
import com.ays.user.model.entity.UserEntity;
import com.ays.user.model.enums.UserSupportStatus;
import com.ays.user.repository.UserRepository;
import com.ays.user.service.UserSelfService;
import com.ays.user.util.exception.AysUserAlreadyHasAssignmentException;
import com.ays.user.util.exception.AysUserNotExistByIdException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.EnumSet;

/**
 * UserSelfServiceImpl is an implementation of the UserSelfService interface.
 * It manages a user's own operations.
 */
@Service
@RequiredArgsConstructor
class UserSelfServiceImpl implements UserSelfService {

    private final UserRepository userRepository;

    private final AssignmentRepository assignmentRepository;

    private final AysIdentity identity;

    /**
     * Updates the support status of a user.
     *
     * @param updateRequest the request object containing the updated support status
     */
    @Override
    public void updateUserSupportStatus(UserSupportStatusUpdateRequest updateRequest) {

        String userId = identity.getUserId();

        final UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new AysUserNotExistByIdException(userId));

        EnumSet<UserSupportStatus> supportStatusesToCheck = EnumSet.of(
                UserSupportStatus.IDLE,
                UserSupportStatus.READY,
                UserSupportStatus.BUSY,
                UserSupportStatus.OFFLINE
        );
        boolean isSupportStatusChecked = EnumValidation.anyOf(
                updateRequest.getSupportStatus(),
                supportStatusesToCheck
        );

        if (isSupportStatusChecked) {
            assignmentRepository
                    .findByUserId(userId)
                    .ifPresent(assignmentEntity -> {
                        throw new AysUserAlreadyHasAssignmentException(userId, assignmentEntity.getId());
                    });
        }

        userEntity.updateSupportStatus(updateRequest.getSupportStatus());
        userRepository.save(userEntity);

    }
}
