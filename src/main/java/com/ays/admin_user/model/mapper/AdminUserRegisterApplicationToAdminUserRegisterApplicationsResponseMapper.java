package com.ays.admin_user.model.mapper;

import com.ays.admin_user.model.AdminUserRegisterApplication;
import com.ays.admin_user.model.dto.response.AdminUserRegisterApplicationsResponse;
import com.ays.common.model.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


/**
 * AdminUserRegisterApplicationToAdminUserRegisterApplicationsResponse is an interface that defines the mapping between an {@link AdminUserRegisterApplication} and an {@link AdminUserRegisterApplicationsResponse}.
 * This interface uses the MapStruct annotation @Mapper to generate an implementation of this interface at compile-time.
 * <p>The class provides a static method {@code initialize()} that returns an instance of the generated mapper implementation.
 * <p>The interface extends the MapStruct interface {@link BaseMapper}, which defines basic mapping methods.
 * The interface adds no additional mapping methods, but simply defines the types to be used in the mapping process.
 */
@Mapper
public interface AdminUserRegisterApplicationToAdminUserRegisterApplicationsResponseMapper extends BaseMapper<AdminUserRegisterApplication, AdminUserRegisterApplicationsResponse> {

    @Override
    @Mapping(target = "user.id", source = "adminUser.id")
    @Mapping(target = "user.firstName", source = "adminUser.firstName")
    @Mapping(target = "user.lastName", source = "adminUser.lastName")
    AdminUserRegisterApplicationsResponse map(AdminUserRegisterApplication source);

    /**
     * Initializes the mapper.
     *
     * @return the initialized mapper object.
     */
    static AdminUserRegisterApplicationToAdminUserRegisterApplicationsResponseMapper initialize() {
        return Mappers.getMapper(AdminUserRegisterApplicationToAdminUserRegisterApplicationsResponseMapper.class);
    }
}
