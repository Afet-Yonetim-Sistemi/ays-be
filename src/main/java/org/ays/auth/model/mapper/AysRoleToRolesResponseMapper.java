package org.ays.auth.model.mapper;

import org.ays.auth.model.AysRole;
import org.ays.auth.model.response.AysRolesResponse;
import org.ays.common.model.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * {@link AysRoleToRolesResponseMapper} is an interface that defines the mapping between an {@link AysRole} and an {@link AysRolesResponse}.
 * This interface uses the MapStruct annotation @Mapper to generate an implementation of this interface at compile-time.
 * <p>The class provides a static method {@code initialize()} that returns an instance of the generated mapper implementation.
 * <p>The interface extends the MapStruct interface {@link BaseMapper}, which defines basic mapping methods.
 * The interface adds no additional mapping methods, but simply defines the types to be used in the mapping process.
 */
@Mapper
public interface AysRoleToRolesResponseMapper extends BaseMapper<AysRole, AysRolesResponse> {

    /**
     * Initializes the mapper.
     *
     * @return the initialized mapper object.
     */
    static AysRoleToRolesResponseMapper initialize() {
        return Mappers.getMapper(AysRoleToRolesResponseMapper.class);
    }

}
