package org.ays.emergency_application.model.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.Valid;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;
import org.ays.common.model.AysFiltering;
import org.ays.common.model.dto.request.AysFilteringRequest;
import org.ays.common.model.dto.request.AysPagingRequest;
import org.ays.emergency_application.model.entity.EmergencyEvacuationApplicationStatus;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Set;

// TODO AYS-222 : Add Javadoc
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class EmergencyEvacuationApplicationListRequest extends AysPagingRequest implements AysFilteringRequest {

    @Valid
    private Filter filter;

    /**
     * Represents a filtering configuration for admin registration applications based on the class fields.
     */
    @Getter
    @Setter
    public static class Filter implements AysFiltering {

        // TODO AYS-222 : Add Javadoc
        @Size(min = 1, max = 10)
        private String referenceNumber;

        // TODO AYS-222 : Add Javadoc
        @Range(min = 1, max = 999)
        private Integer personCount;

        // TODO AYS-222 : Add Javadoc
        @Size(min = 2, max = 100)
        private String targetCity;

        // TODO AYS-222 : Add Javadoc
        @Size(min = 2, max = 100)
        private String targetDistrict;

        /**
         * List of admin registration application's statuses used for filtering.
         */
        private List<EmergencyEvacuationApplicationStatus> statuses;

    }

    /**
     * Overrides the {@link AysPagingRequest#isSortPropertyAccepted()} method to validate sorting options
     * and ensures that no unsupported sorting property is used in the request.
     *
     * @return true if the sorting property is accepted, false otherwise.
     */
    @JsonIgnore
    @AssertTrue
    @Override
    public boolean isSortPropertyAccepted() {
        final Set<String> acceptedFilterFields = Set.of("createdAt");
        return this.isPropertyAccepted(acceptedFilterFields);
    }

    // TODO AYS-222 : Add Javadoc
    @Override
    public <C> Specification<C> toSpecification(Class<C> clazz) {

        if (this.filter == null) {
            return Specification.allOf();
        }

        Specification<C> specification = Specification.where(null);

        if (!CollectionUtils.isEmpty(this.filter.statuses)) {
            Specification<C> statusSpecification = this.filter.statuses.stream()
                    .map(status -> (Specification<C>) (root, query, criteriaBuilder) ->
                            criteriaBuilder.equal(root.get("status"), status))
                    .reduce(Specification::or).orElse(null);

            specification = specification.and(statusSpecification);
        }

        if (this.filter.referenceNumber != null) {
            specification = specification.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.like(root.get("referenceNumber"), "%" + this.filter.referenceNumber + "%"));
        }

        if (this.filter.personCount != null) {
            specification = specification.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("personCount"), this.filter.personCount));
        }

        if (this.filter.targetCity != null) {
            specification = specification.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.like(root.get("targetCity"), "%" + this.filter.targetCity + "%"));
        }

        if (this.filter.targetDistrict != null) {
            specification = specification.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.like(root.get("targetDistrict"), "%" + this.filter.targetDistrict + "%"));
        }

        return specification;
    }

}
