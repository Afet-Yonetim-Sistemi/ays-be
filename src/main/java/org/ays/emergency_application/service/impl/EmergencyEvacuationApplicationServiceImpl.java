package org.ays.emergency_application.service.impl;

import lombok.RequiredArgsConstructor;
import org.ays.common.model.AysPage;
import org.ays.emergency_application.model.EmergencyEvacuationApplication;
import org.ays.emergency_application.model.dto.request.EmergencyEvacuationApplicationListRequest;
import org.ays.emergency_application.model.dto.request.EmergencyEvacuationApplicationRequest;
import org.ays.emergency_application.model.entity.EmergencyEvacuationApplicationEntity;
import org.ays.emergency_application.model.mapper.EmergencyEvacuationApplicationEntityToEmergencyEvacuationApplicationMapper;
import org.ays.emergency_application.model.mapper.EmergencyEvacuationApplicationRequestToEntityMapper;
import org.ays.emergency_application.repository.EmergencyEvacuationApplicationRepository;
import org.ays.emergency_application.service.EmergencyEvacuationApplicationService;
import org.ays.emergency_application.util.exception.AysEmergencyEvacuationNotExistException;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * This class implements the interface {@link EmergencyEvacuationApplicationService}
 * It is annotated with {@code @Service} to indicate that it is a service component in the application.
 * The class is also annotated with {@code @RequiredArgsConstructor} to automatically generate a constructor based on the declared final fields.
 * The {@code @Transactional} annotation ensures that all the methods in this class are executed within a transactional context.
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
class EmergencyEvacuationApplicationServiceImpl implements EmergencyEvacuationApplicationService {

    private final EmergencyEvacuationApplicationRepository emergencyEvacuationApplicationRepository;


    private final EmergencyEvacuationApplicationRequestToEntityMapper emergencyEvacuationApplicationRequestToEntityMapper = EmergencyEvacuationApplicationRequestToEntityMapper.initialize();
    private final EmergencyEvacuationApplicationEntityToEmergencyEvacuationApplicationMapper entityToEmergencyEvacuationApplicationMapper = EmergencyEvacuationApplicationEntityToEmergencyEvacuationApplicationMapper.initialize();

    /**
     * Retrieves a page of emergency evacuation applications based on the provided request parameters.
     *
     * @param listRequest The request parameters for retrieving the emergency evacuation applications. This includes pagination and filtering parameters.
     * @return A page of emergency evacuation applications. Each application includes details such as the ID, status, and other related information.
     */
    @Override
    public AysPage<EmergencyEvacuationApplication> findAll(EmergencyEvacuationApplicationListRequest listRequest) {

        final Specification<EmergencyEvacuationApplicationEntity> requestedSpecifications = listRequest
                .toSpecification(EmergencyEvacuationApplicationEntity.class);

        final Page<EmergencyEvacuationApplicationEntity> emergencyEvacuationApplicationEntities = emergencyEvacuationApplicationRepository
                .findAll(requestedSpecifications, listRequest.toPageable());

        final List<EmergencyEvacuationApplication> emergencyEvacuationApplications = entityToEmergencyEvacuationApplicationMapper
                .map(emergencyEvacuationApplicationEntities.getContent());

        return AysPage.of(
                listRequest.getFilter(),
                emergencyEvacuationApplicationEntities,
                emergencyEvacuationApplications
        );
    }

    /**
     * Retrieves an emergency evacuation application by its ID.
     * @param id The ID of the emergency evacuation application.
     * @return the emergency evacuation application corresponding to the given ID.
     */
    @Override
    public EmergencyEvacuationApplication findById(String id) {
        EmergencyEvacuationApplicationEntity entity = emergencyEvacuationApplicationRepository.findById(id)
                .orElseThrow(() -> new AysEmergencyEvacuationNotExistException(id));
        return entityToEmergencyEvacuationApplicationMapper.map(entity);
    }

    /**
     * Create an emergency evacuation application to the database
     *
     * @param emergencyEvacuationApplicationRequest The emergency evacuation request containing application information
     */
    @Override
    @Transactional
    public void create(EmergencyEvacuationApplicationRequest emergencyEvacuationApplicationRequest) {
        EmergencyEvacuationApplicationEntity applicationEntity = emergencyEvacuationApplicationRequestToEntityMapper
                .map(emergencyEvacuationApplicationRequest);
        applicationEntity.pending();

        emergencyEvacuationApplicationRepository.save(applicationEntity);
    }

}
