package com.ays.auth.model;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import java.util.HashMap;
import java.util.Map;

public class AysTokenBuilder {

    public static final AysToken VALID_FOR_ADMIN = AysToken.builder()
            .accessToken("eyJhbGciOiJSUzUxMiJ9.eyJqdGkiOiI2NTYzZmMyMi03NGU1LTRmYmEtYTVmYy01MTBlMjg5NDIzOWUiLCJpc3MiOiJBWVMiLCJpYXQiOjE2ODIwMTk0NTEsImV4cCI6MTY4MjAyNjY1MSwidHlwZSI6IkJlYXJlciIsInVzZXJMYXN0TmFtZSI6IlNpc3RlbWkiLCJ1c2VyVHlwZSI6IkFETUlOIiwidXNlckZpcnN0TmFtZSI6IkFmZXQgWcO2bmV0aW0iLCJ1c2VybmFtZSI6ImF5cy1hZG1pbiJ9.FrJmW11zNJfzeYWhHINqcDTVNfwSCgKP9lmrRc9t4B3qfTSIJeMOQmEz4GfUTFwv26OGcgc7-OCEds9I5zCXP35OlSZ3rT4Wq-z0FgyPAGMmOgqg3J0s1gPn2joMt4ejhPdwKC0882Xq3K0d6baB4cRknB3vBtQcOgLwMeFn9jeWHisfO5cxe4SRc22cHSrrMPIW5KDpZXbUtpQag2BALcszpr62lCd5m1skrkBKr4siprys-XOId9zHTf8byn8mZJrDs3bX8dRNnPyNK8ol2rh1Ic81M_zS1sHd3hUMNTcW97kGZsj58Xc_DhBZZ0s_qXl0IvH4tD9Kv1ho7rYIDQ")
            .accessTokenExpiresAt(1682026651L)
            .refreshToken("eyJhbGciOiJSUzUxMiJ9.eyJqdGkiOiI3ZjljYTNmMy00N2FhLTRkNzQtOTAxYS0xNDdiYTJiNTQ3NGQiLCJpc3MiOiJBWVMiLCJpYXQiOjE2ODIwMTk0NTEsImV4cCI6MTY4MjEwNTg1MSwidHlwZSI6IkJlYXJlciIsInVzZXJuYW1lIjoiYXlzLWFkbWluIn0.XBTCsqoQCs7BRiHkKU8VofJtnpel2iEWM5AJrZkh1SEnDZHt9T7PjLT5znSHCl6UKrpsVDt9yPqOjrvnLYuP4CGrf9l23nTkKQcCEs_M-U0Q1j7Y_eH0AwFvf9uEd5PBaC_Sv9cuwELhFSVTKzL7xh_cG1BBCQhB4NldkC7_OsNCHlYfiz31lo-0wf4F3wou02Gx1jQprMGaWGmOxAueFZE1_Mk-YcnL1SKBFkKSBZU_op4P_sH1FrLJ5VLsnfLhDRQSLteLvZ0mk-wvV6CZsRpfKKov77vjBIQr9aaE0aNLkh15JG8KWTpmBZDFt8e6dbA8TuUTPDWzGBIgrSRnQA")
            .build();

    public static final AysToken.AysTokenBuilder VALID_BUILDER_FOR_ADMIN = AysToken.builder()
            .accessToken("eyJhbGciOiJSUzUxMiJ9.eyJqdGkiOiI2NTYzZmMyMi03NGU1LTRmYmEtYTVmYy01MTBlMjg5NDIzOWUiLCJpc3MiOiJBWVMiLCJpYXQiOjE2ODIwMTk0NTEsImV4cCI6MTY4MjAyNjY1MSwidHlwZSI6IkJlYXJlciIsInVzZXJMYXN0TmFtZSI6IlNpc3RlbWkiLCJ1c2VyVHlwZSI6IkFETUlOIiwidXNlckZpcnN0TmFtZSI6IkFmZXQgWcO2bmV0aW0iLCJ1c2VybmFtZSI6ImF5cy1hZG1pbiJ9.FrJmW11zNJfzeYWhHINqcDTVNfwSCgKP9lmrRc9t4B3qfTSIJeMOQmEz4GfUTFwv26OGcgc7-OCEds9I5zCXP35OlSZ3rT4Wq-z0FgyPAGMmOgqg3J0s1gPn2joMt4ejhPdwKC0882Xq3K0d6baB4cRknB3vBtQcOgLwMeFn9jeWHisfO5cxe4SRc22cHSrrMPIW5KDpZXbUtpQag2BALcszpr62lCd5m1skrkBKr4siprys-XOId9zHTf8byn8mZJrDs3bX8dRNnPyNK8ol2rh1Ic81M_zS1sHd3hUMNTcW97kGZsj58Xc_DhBZZ0s_qXl0IvH4tD9Kv1ho7rYIDQ")
            .accessTokenExpiresAt(1682026651L)
            .refreshToken("eyJhbGciOiJSUzUxMiJ9.eyJqdGkiOiI3ZjljYTNmMy00N2FhLTRkNzQtOTAxYS0xNDdiYTJiNTQ3NGQiLCJpc3MiOiJBWVMiLCJpYXQiOjE2ODIwMTk0NTEsImV4cCI6MTY4MjEwNTg1MSwidHlwZSI6IkJlYXJlciIsInVzZXJuYW1lIjoiYXlzLWFkbWluIn0.XBTCsqoQCs7BRiHkKU8VofJtnpel2iEWM5AJrZkh1SEnDZHt9T7PjLT5znSHCl6UKrpsVDt9yPqOjrvnLYuP4CGrf9l23nTkKQcCEs_M-U0Q1j7Y_eH0AwFvf9uEd5PBaC_Sv9cuwELhFSVTKzL7xh_cG1BBCQhB4NldkC7_OsNCHlYfiz31lo-0wf4F3wou02Gx1jQprMGaWGmOxAueFZE1_Mk-YcnL1SKBFkKSBZU_op4P_sH1FrLJ5VLsnfLhDRQSLteLvZ0mk-wvV6CZsRpfKKov77vjBIQr9aaE0aNLkh15JG8KWTpmBZDFt8e6dbA8TuUTPDWzGBIgrSRnQA");

    public static final AysToken VALID_FOR_USER = AysToken.builder()
            .accessToken("eyJhbGciOiJSUzUxMiJ9.eyJqdGkiOiJkYjhlNmZiYy1hMDJmLTQwMDQtOTU4ZC02M2U5MmM4ZTllM2QiLCJpc3MiOiJBWVMiLCJpYXQiOjE2ODIwMTk1MTksImV4cCI6MTY4MjAyNjcxOSwidHlwZSI6IkJlYXJlciIsInVzZXJMYXN0TmFtZSI6IlNpc3RlbWkiLCJyb2xlcyI6WyJWT0xVTlRFRVIiXSwidXNlclR5cGUiOiJVU0VSIiwidXNlckZpcnN0TmFtZSI6IkFmZXQgWcO2bmV0aW0iLCJ1c2VybmFtZSI6IjIzMjE4MCJ9.JsbCDRMy2nqMb5tjcY5IZSf0jtEp4PdJQMMZdn8lYvrSkspHGxcTNMyr8P6r8JC3t1qInAuGbybuKG6COr9LTzPB9TO15x_58zxFAqRrq9VYDb-nQhjPs9auNjfL1W1HjUd29zqA4E9ZsviVA3bbHv29Uu-PYuUMXU6Oqh4ahnrngeip4mufSfEr46voLi_QUoAmzX34cgLn526FaBA3QBqtc8GJVL5fCCTC1WXy-nTSgyUMow8gRSQowYMtm9qqlMxml-NEOKU2xyJXzpyiaf7GDErvgKwMW6CxkEFI91rt_KcM88xc3uXcz8yy6m8ZZZzff54VZ9Cp9FujD6ubtg")
            .accessTokenExpiresAt(1682026719L)
            .refreshToken("eyJhbGciOiJSUzUxMiJ9.eyJqdGkiOiI5OGQ4MDBhMy0yMGYzLTQyOGQtYWNhNi0zNzE3NzBhNjZmMGEiLCJpc3MiOiJBWVMiLCJpYXQiOjE2ODIwMTk1MTksImV4cCI6MTY4MjEwNTkxOSwidHlwZSI6IkJlYXJlciIsInVzZXJuYW1lIjoiMjMyMTgwIn0.edhZL1P8mzG3HC6MxwL0PQ6ZYo6gKoRecV7AzE04KMNQ1JZayobtJFyrMyoeXzwVv6WgKR9Dw-j_SO13uxJxNKKw-ZMJvqgnSl_elZ31g4Hev05mR6KclE_yl22SG_rt54CzZl8ebQRY19m9Ryustmurr9jGaNT2vqPC2EJgrHSWB3meSwnfJm0fp6qtt0lBpsoFCiRrAWo2w8M_-BkMYjmcvmYSsitIEUk_dKrp5WaTaitZVp9Z6qRc8VGSE0ssVRXTlmSuDYBTP7KBJRCFdHvIvl2vpSBZQmYpmkLMhBjRJz803jBFK-CNJSMaW3-VCtRsRc1O_j66dJvW1iF-zQ")
            .build();

    public static Claims getValidClaims(String username) {
        Map<String, Object> mockClaimsMap = new HashMap<>();
        mockClaimsMap.put("username", username);
        return Jwts.claims(mockClaimsMap);
    }

}