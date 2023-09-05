package com.ays.location.service.impl;

import com.ays.assignment.model.enums.AssignmentStatus;
import com.ays.assignment.repository.AssignmentRepository;
import com.ays.auth.model.AysIdentity;
import com.ays.location.model.dto.request.UserLocationSaveRequest;
import com.ays.location.model.entity.UserLocationEntity;
import com.ays.location.model.mapper.UserLocationSaveRequestToUserLocationEntityMapper;
import com.ays.location.repository.UserLocationRepository;
import com.ays.location.service.UserLocationService;
import com.ays.location.util.exception.AysUserLocationCannotBeUpdatedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Implementation of the UserLocationService interface that manages and stores user location data.
 * This service utilizes a repository to interact with the database for saving user location information.
 */
@Service
@RequiredArgsConstructor
class UserLocationServiceImpl implements UserLocationService {

    private final UserLocationRepository userLocationRepository;
    private final AssignmentRepository assignmentRepository;

    private final AysIdentity identity;


    private final UserLocationSaveRequestToUserLocationEntityMapper userLocationSaveRequestToUserLocationEntityMapper = UserLocationSaveRequestToUserLocationEntityMapper.initialize();

    /**
     * Saves the user's location based on the provided UserLocationSaveRequest.
     * If the user's location already exists in the database, updates the location; otherwise, creates a new entry.
     *
     * @param saveRequest The request containing the user's location information to be saved.
     */
    @Override
    public void saveUserLocation(final UserLocationSaveRequest saveRequest) {

        final boolean isAssignmentInProgress = assignmentRepository
                .existsByUserIdAndStatus(identity.getUserId(), AssignmentStatus.IN_PROGRESS);
        if (!isAssignmentInProgress) {
            throw new AysUserLocationCannotBeUpdatedException();
        }

        userLocationRepository.findByUserId(identity.getUserId())
                .ifPresentOrElse(
                        userLocationEntityFromDatabase -> {
                            userLocationEntityFromDatabase.setPoint(saveRequest.getLongitude(), saveRequest.getLatitude());
                            userLocationRepository.save(userLocationEntityFromDatabase);
                        },
                        () -> {
                            final UserLocationEntity userLocationEntity = userLocationSaveRequestToUserLocationEntityMapper
                                    .mapForSaving(saveRequest, identity.getUserId());
                            userLocationRepository.save(userLocationEntity);
                        }
                );
    }

}
