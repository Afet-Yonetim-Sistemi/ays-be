package org.ays.util;

import java.util.regex.Pattern;

public class UUIDTestUtil {

    private static final Pattern UUID_REGEX = Pattern.compile("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$");

    public static boolean isValid(String uuid) {
        return UUID_REGEX.matcher(uuid).matches();
    }

}
