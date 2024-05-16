package org.ays.admin_user.model.mapper;

import org.ays.admin_user.model.AdminRegistrationApplication;
import org.ays.admin_user.model.dto.response.AdminRegisterApplicationSummaryResponse;
import org.ays.common.model.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * AdminUserRegisterApplicationToAdminUserRegisterApplicationSummaryResponseMapper is an interface that defines the mapping between an {@link AdminRegistrationApplication} and an {@link AdminRegisterApplicationSummaryResponse}.
 * This interface uses the MapStruct annotation @Mapper to generate an implementation of this interface at compile-time.
 * <p>The class provides a static method {@code initialize()} that returns an instance of the generated mapper implementation.
 * <p>The interface extends the MapStruct interface {@link BaseMapper}, which defines basic mapping methods.
 * The interface adds no additional mapping methods, but simply defines the types to be used in the mapping process.
 */
@Mapper
public interface AdminRegisterApplicationToAdminRegisterApplicationSummaryResponseMapper extends BaseMapper<AdminRegistrationApplication, AdminRegisterApplicationSummaryResponse> {

    /**
     * Initializes the mapper.
     *
     * @return the initialized mapper object.
     */
    static AdminRegisterApplicationToAdminRegisterApplicationSummaryResponseMapper initialize() {
        return Mappers.getMapper(AdminRegisterApplicationToAdminRegisterApplicationSummaryResponseMapper.class);
    }

}
