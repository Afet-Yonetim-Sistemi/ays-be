package org.ays.user.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.ays.common.util.validation.NoSpecialCharacters;
import org.hibernate.validator.constraints.UUID;

import java.util.Set;

@Getter
@Setter
public class RoleCreateRequest {

    @NotBlank
    @Size(min = 2, max = 255)
    @NoSpecialCharacters
    private String name;

    @NotEmpty
    private Set<@UUID String> permissionIds;

}
