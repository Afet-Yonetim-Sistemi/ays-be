package com.ays.assignment.model.dto.response;

import com.ays.common.model.dto.response.BaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class AssignmentsResponse extends BaseResponse {

    private String id;
    private String description;
    private String firstName;
    private String lastName;
    private Location location;
    private User user;

    @Getter
    @Setter
    public static class Location {
        private Double longitude;
        private Double latitude;
    }

    @Getter
    @Setter
    public static class User {
        private String id;
        private String firstName;
        private String lastName;
    }
}
