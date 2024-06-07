package org.ays.emergency_application.service.impl;

import org.ays.AbstractUnitTest;
import org.ays.common.model.AysPage;
import org.ays.common.model.AysPageBuilder;
import org.ays.emergency_application.model.EmergencyEvacuationApplication;
import org.ays.emergency_application.model.dto.request.EmergencyEvacuationApplicationListRequest;
import org.ays.emergency_application.model.dto.request.EmergencyEvacuationApplicationRequest;
import org.ays.emergency_application.model.dto.request.EmergencyEvacuationRequestBuilder;
import org.ays.emergency_application.model.entity.EmergencyEvacuationApplicationEntity;
import org.ays.emergency_application.model.entity.EmergencyEvacuationApplicationEntityBuilder;
import org.ays.emergency_application.model.entity.EmergencyEvacuationApplicationStatus;
import org.ays.emergency_application.model.mapper.EmergencyEvacuationApplicationEntityToEmergencyEvacuationApplicationMapper;
import org.ays.emergency_application.model.mapper.EmergencyEvacuationApplicationRequestToEntityMapper;
import org.ays.emergency_application.repository.EmergencyEvacuationApplicationRepository;
import org.ays.user.model.dto.request.EmergencyEvacuationApplicationListRequestBuilder;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.times;

class EmergencyEvacuationApplicationServiceImplTest extends AbstractUnitTest {

    @InjectMocks
    private EmergencyEvacuationApplicationServiceImpl emergencyEvacuationApplicationService;

    @Mock
    private EmergencyEvacuationApplicationRepository emergencyEvacuationApplicationRepository;


    private final EmergencyEvacuationApplicationRequestToEntityMapper emergencyEvacuationApplicationRequestToEntityMapper = EmergencyEvacuationApplicationRequestToEntityMapper.initialize();
    private final EmergencyEvacuationApplicationEntityToEmergencyEvacuationApplicationMapper entityToEmergencyEvacuationApplicationMapper = EmergencyEvacuationApplicationEntityToEmergencyEvacuationApplicationMapper.initialize();

    @Test
    @SuppressWarnings("unchecked")
    void givenEmergencyEvacuationApplicationListRequest_whenFilterNotGiven_thenReturnAysPageEmergencyEvacuationApplicationsResponse() {

        // Given
        EmergencyEvacuationApplicationListRequest mockListRequest = new EmergencyEvacuationApplicationListRequestBuilder()
                .withValidValues()
                .withFilter(null)
                .build();

        List<EmergencyEvacuationApplicationEntity> mockEntities = List.of(
                new EmergencyEvacuationApplicationEntityBuilder().withValidFields().withoutApplicant().build()
        );
        Page<EmergencyEvacuationApplicationEntity> mockPageEntities = new PageImpl<>(mockEntities);

        List<EmergencyEvacuationApplication> mockList = entityToEmergencyEvacuationApplicationMapper.map(mockEntities);
        AysPage<EmergencyEvacuationApplication> mockAysPage = AysPage.of(mockListRequest.getFilter(), mockPageEntities, mockList);

        // When
        Mockito.when(emergencyEvacuationApplicationRepository.findAll(Mockito.any(Specification.class), Mockito.any(Pageable.class)))
                .thenReturn(mockPageEntities);

        // Then
        AysPage<EmergencyEvacuationApplication> aysPage = emergencyEvacuationApplicationService
                .findAll(mockListRequest);

        AysPageBuilder.assertEquals(mockAysPage, aysPage);

        // Verify
        Mockito.verify(emergencyEvacuationApplicationRepository, Mockito.times(1))
                .findAll(Mockito.any(Specification.class), Mockito.any(Pageable.class));
    }

    @Test
    @SuppressWarnings("unchecked")
    void givenEmergencyEvacuationApplicationListRequest_whenEmergencyEvacuationApplicationStatusIsAvailable_thenReturnAysPageEmergencyEvacuationApplicationsResponse() {

        // Given
        EmergencyEvacuationApplicationListRequest mockListRequest = new EmergencyEvacuationApplicationListRequestBuilder()
                .withValidValues()
                .withStatuses(Set.of(EmergencyEvacuationApplicationStatus.PENDING))
                .build();

        List<EmergencyEvacuationApplicationEntity> mockEntities = List.of(
                new EmergencyEvacuationApplicationEntityBuilder().withValidFields().withoutApplicant().withStatus(EmergencyEvacuationApplicationStatus.PENDING).build()
        );
        Page<EmergencyEvacuationApplicationEntity> mockPageEntities = new PageImpl<>(mockEntities);

        List<EmergencyEvacuationApplication> mockList = entityToEmergencyEvacuationApplicationMapper.map(mockEntities);
        AysPage<EmergencyEvacuationApplication> mockAysPage = AysPage.of(mockListRequest.getFilter(), mockPageEntities, mockList);

        // When
        Mockito.when(emergencyEvacuationApplicationRepository.findAll(Mockito.any(Specification.class), Mockito.any(Pageable.class)))
                .thenReturn(mockPageEntities);

        // Then
        AysPage<EmergencyEvacuationApplication> aysPage = emergencyEvacuationApplicationService
                .findAll(mockListRequest);

        AysPageBuilder.assertEquals(mockAysPage, aysPage);

        Mockito.verify(emergencyEvacuationApplicationRepository, Mockito.times(1))
                .findAll(Mockito.any(Specification.class), Mockito.any(Pageable.class));
    }


    @Test
    void givenValidEmergencyEvacuationRequest_ShouldCreateEmergencyEvacuationApplicationCorrectly() {
        // Given
        EmergencyEvacuationApplicationRequest mockApplicationRequest = new EmergencyEvacuationRequestBuilder()
                .withValidFields()
                .build();

        // When
        EmergencyEvacuationApplicationEntity mockApplicationEntity = emergencyEvacuationApplicationRequestToEntityMapper
                .map(mockApplicationRequest);
        mockApplicationEntity.pending();
        Mockito
                .when(emergencyEvacuationApplicationRepository.save(Mockito.any(EmergencyEvacuationApplicationEntity.class)))
                .thenReturn(mockApplicationEntity);

        // When
        emergencyEvacuationApplicationService.create(mockApplicationRequest);

        // Then
        Mockito.verify(emergencyEvacuationApplicationRepository, times(1))
                .save(Mockito.any(EmergencyEvacuationApplicationEntity.class));
    }

}
