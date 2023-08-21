package com.ays.assignment.model.mapper;

import com.ays.assignment.model.Assignment;
import com.ays.assignment.model.entity.AssignmentEntity;
import com.ays.common.model.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * AssignmentEntityToAssignmentMapper is an interface that defines the mapping between an {@link AssignmentEntity} and an {@link Assignment}.
 * This interface uses the MapStruct annotation @Mapper to generate an implementation of this interface at compile-time.
 * <p>The class provides a static method {@code initialize()} that returns an instance of the generated mapper implementation.
 * <p>The interface extends the MapStruct interface {@link BaseMapper}, which defines basic mapping methods.
 * The interface adds no additional mapping methods, but simply defines the types to be used in the mapping process.
 */
@Mapper
public interface AssignmentEntityToAssignmentMapper extends BaseMapper<AssignmentEntity, Assignment> {

    @Override
    @Mapping(target = "phoneNumber.countryCode", source = "source.countryCode")
    @Mapping(target = "phoneNumber.lineNumber", source = "source.lineNumber")
    Assignment map(AssignmentEntity source);

    /**
     * Initializes the mapper.
     *
     * @return the initialized mapper object.
     */
    static AssignmentEntityToAssignmentMapper initialize() {
        return Mappers.getMapper(AssignmentEntityToAssignmentMapper.class);
    }

}
