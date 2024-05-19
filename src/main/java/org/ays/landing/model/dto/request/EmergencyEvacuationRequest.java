package org.ays.landing.model.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.ays.common.model.dto.request.AysPhoneNumberRequest;
import org.ays.common.util.validation.Name;


/**
 * Represents a request to complete emergency evacuation request. The request includes fields for the required user
 * information, such as the user's phone number, as well as their first and last name.
 */

@Getter
@Setter
public class EmergencyEvacuationRequest {

    @NotBlank
    @Name
    private String firstName;

    @NotBlank
    @Name
    private String lastName;

    @Valid
    @NotNull
    private AysPhoneNumberRequest phoneNumber;

    @NotBlank
    private String address;

    @NotNull
    @Min(1)
    private int personCount;

    @NotNull
    private boolean hasObstaclePersonExist;

    @NotBlank
    private String targetCity;

    @NotBlank
    private String targetDistrict;
}
