package com.ays.user.model.dto.request;

import com.ays.common.util.validation.EnumValidation;
import com.ays.user.model.enums.UserSupportStatus;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.ObjectUtils;

import java.util.EnumSet;

/**
 * Request object for updating the support status of a user.
 */
@Getter
@Setter
public class UserSupportStatusUpdateRequest {

    @NotNull
    private UserSupportStatus supportStatus;

    @AssertTrue(message = "IS NOT ACCEPTED")
    @SuppressWarnings("This method is unused by the application directly but Spring is using it in the background.")
    private boolean isSupportStatusAccepted() {

        if (ObjectUtils.isEmpty(this.supportStatus)) {
            return true;
        }

        EnumSet<UserSupportStatus> acceptedUserSupportStatuses = EnumSet.of(
                UserSupportStatus.IDLE,
                UserSupportStatus.READY,
                UserSupportStatus.BUSY,
                UserSupportStatus.MALFUNCTION,
                UserSupportStatus.ACCIDENT
        );

        return EnumValidation.anyOf(this.supportStatus, acceptedUserSupportStatuses);

    }

}
