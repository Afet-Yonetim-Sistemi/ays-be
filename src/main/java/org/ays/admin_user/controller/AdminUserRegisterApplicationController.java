package org.ays.admin_user.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.ays.admin_user.model.AdminRegistrationApplication;
import org.ays.admin_user.model.dto.request.AdminRegisterApplicationCompleteRequest;
import org.ays.admin_user.model.dto.request.AdminRegisterApplicationCreateRequest;
import org.ays.admin_user.model.dto.request.AdminRegisterApplicationListRequest;
import org.ays.admin_user.model.dto.request.AdminRegisterApplicationRejectRequest;
import org.ays.admin_user.model.dto.response.AdminRegisterApplicationCreateResponse;
import org.ays.admin_user.model.dto.response.AdminRegisterApplicationResponse;
import org.ays.admin_user.model.dto.response.AdminRegisterApplicationSummaryResponse;
import org.ays.admin_user.model.dto.response.AdminUserRegisterApplicationsResponse;
import org.ays.admin_user.model.mapper.AdminRegisterApplicationToAdminRegisterApplicationCreateResponseMapper;
import org.ays.admin_user.model.mapper.AdminRegisterApplicationToAdminRegisterApplicationResponseMapper;
import org.ays.admin_user.model.mapper.AdminRegisterApplicationToAdminRegisterApplicationSummaryResponseMapper;
import org.ays.admin_user.model.mapper.AdminRegisterApplicationToAdminRegisterApplicationsResponseMapper;
import org.ays.admin_user.service.AdminRegisterApplicationService;
import org.ays.admin_user.service.AdminRegistrationCompleteService;
import org.ays.common.model.AysPage;
import org.ays.common.model.dto.response.AysPageResponse;
import org.ays.common.model.dto.response.AysResponse;
import org.hibernate.validator.constraints.UUID;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
class AdminUserRegisterApplicationController {

    private final AdminRegisterApplicationService adminRegisterApplicationService;
    private final AdminRegistrationCompleteService adminRegistrationCompleteService;

    private final AdminRegisterApplicationToAdminRegisterApplicationsResponseMapper adminRegisterApplicationToAdminRegisterApplicationsResponseMapper = AdminRegisterApplicationToAdminRegisterApplicationsResponseMapper.initialize();
    private final AdminRegisterApplicationToAdminRegisterApplicationResponseMapper adminRegisterApplicationToAdminRegisterApplicationResponseMapper = AdminRegisterApplicationToAdminRegisterApplicationResponseMapper.initialize();
    private final AdminRegisterApplicationToAdminRegisterApplicationSummaryResponseMapper adminRegisterApplicationToAdminRegisterApplicationSummaryResponseMapper = AdminRegisterApplicationToAdminRegisterApplicationSummaryResponseMapper.initialize();
    private final AdminRegisterApplicationToAdminRegisterApplicationCreateResponseMapper adminRegisterApplicationToAdminRegisterApplicationCreateResponseMapper = AdminRegisterApplicationToAdminRegisterApplicationCreateResponseMapper.initialize();


    @PostMapping("/admin-registration-applications")
    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN', 'application:registration:list')")
    public AysResponse<AysPageResponse<AdminUserRegisterApplicationsResponse>> findAll(
            @RequestBody @Valid AdminRegisterApplicationListRequest request) {

        final AysPage<AdminRegistrationApplication> pageOfRegisterApplications = adminRegisterApplicationService.findAll(request);
        final AysPageResponse<AdminUserRegisterApplicationsResponse> pageResponseOfRegisterApplication = AysPageResponse
                .<AdminUserRegisterApplicationsResponse>builder()
                .of(pageOfRegisterApplications)
                .content(
                        adminRegisterApplicationToAdminRegisterApplicationsResponseMapper
                                .map(pageOfRegisterApplications.getContent())
                )
                .filteredBy(request.getFilter())
                .build();

        return AysResponse.successOf(pageResponseOfRegisterApplication);
    }

    @GetMapping("/admin-registration-application/{id}")
    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN', 'application:registration:detail')")
    public AysResponse<AdminRegisterApplicationResponse> findById(@PathVariable @UUID String id) {

        final AdminRegistrationApplication registerApplication = adminRegisterApplicationService.findById(id);

        return AysResponse.successOf(
                adminRegisterApplicationToAdminRegisterApplicationResponseMapper.map(registerApplication)
        );
    }

    /**
     * Gets an admin register application summary in the system.
     * Requires no authority.
     *
     * @param id The id of the register application.
     * @return A response with the register application summary.
     */
    @GetMapping("/admin-registration-application/{id}/summary")
    public AysResponse<AdminRegisterApplicationSummaryResponse> findSummaryById(@PathVariable @UUID String id) {

        final AdminRegistrationApplication registerApplication = adminRegisterApplicationService
                .findAllSummaryById(id);

        return AysResponse.successOf(
                adminRegisterApplicationToAdminRegisterApplicationSummaryResponseMapper.map(registerApplication)
        );
    }

    @PostMapping("/admin-registration-application")
    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN', 'application:registration:create')")
    public AysResponse<AdminRegisterApplicationCreateResponse> create(
            @RequestBody @Valid AdminRegisterApplicationCreateRequest createRequest) {

        AdminRegistrationApplication registerApplication = adminRegisterApplicationService
                .create(createRequest);
        return AysResponse.successOf(
                adminRegisterApplicationToAdminRegisterApplicationCreateResponseMapper
                        .map(registerApplication)
        );
    }

    @PostMapping("/admin-registration-application/{id}/approve")
    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN', 'application:registration:conclude')")
    public AysResponse<Void> approve(@PathVariable @UUID String id) {

        adminRegisterApplicationService.approve(id);
        return AysResponse.SUCCESS;
    }

    @PostMapping("/admin-registration-application/{id}/reject")
    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN', 'application:registration:conclude')")
    public AysResponse<Void> reject(@PathVariable @UUID String id,
                                    @RequestBody @Valid AdminRegisterApplicationRejectRequest request) {

        adminRegisterApplicationService.reject(id, request);
        return AysResponse.SUCCESS;
    }

    @PostMapping("/admin-registration-application/{id}/complete")
    public AysResponse<Void> complete(@PathVariable @UUID String id,
                                      @RequestBody @Valid AdminRegisterApplicationCompleteRequest registerRequest) {

        adminRegistrationCompleteService.complete(id, registerRequest);
        return AysResponse.SUCCESS;
    }

}
