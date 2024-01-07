package com.ays.admin_user.util.exception;

import com.ays.common.util.exception.AysAuthException;

import java.io.Serial;

/**
 * Exception indicating that an admin user register application does not exist with the specified ID.
 * This exception is a subclass of AysAuthException, which is typically used to indicate that an entity or
 * resource does not exist.
 * Typically, this exception is thrown when an operation or query is performed on an admin user register application
 * entity using an ID that does not correspond to an existing admin user register application.
 */
public class AysAdminUserRegisterApplicationSummaryNotExistByIdException extends AysAuthException {

    /**
     * Unique identifier for serialization.
     */
    @Serial
    private static final long serialVersionUID = 8416712253227498925L;

    /**
     * Constructs a new AysAdminUserRegisterApplicationSummaryNotExistByIdException with the specified ID.
     *
     * @param id The ID of the admin user register application that does not exist.
     */
    public AysAdminUserRegisterApplicationSummaryNotExistByIdException(String id) {
        super("ADMIN USER REGISTER APPLICATION NOT EXIST! id:" + id);
    }

}
