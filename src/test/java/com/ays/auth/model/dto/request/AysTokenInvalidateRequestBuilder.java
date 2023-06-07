package com.ays.auth.model.dto.request;

public class AysTokenInvalidateRequestBuilder {

    public static final AysTokenInvalidateRequest VALID_FOR_ADMIN = AysTokenInvalidateRequest.builder()
            .refreshToken("eyJhbGciOiJSUzUxMiJ9.eyJqdGkiOiI3ZjljYTNmMy00N2FhLTRkNzQtOTAxYS0xNDdiYTJiNTQ3NGQiLCJpc3MiOiJBWVMiLCJpYXQiOjE2ODIwMTk0NTEsImV4cCI6MTY4MjEwNTg1MSwidHlwZSI6IkJlYXJlciIsInVzZXJuYW1lIjoiYXlzLWFkbWluIn0.XBTCsqoQCs7BRiHkKU8VofJtnpel2iEWM5AJrZkh1SEnDZHt9T7PjLT5znSHCl6UKrpsVDt9yPqOjrvnLYuP4CGrf9l23nTkKQcCEs_M-U0Q1j7Y_eH0AwFvf9uEd5PBaC_Sv9cuwELhFSVTKzL7xh_cG1BBCQhB4NldkC7_OsNCHlYfiz31lo-0wf4F3wou02Gx1jQprMGaWGmOxAueFZE1_Mk-YcnL1SKBFkKSBZU_op4P_sH1FrLJ5VLsnfLhDRQSLteLvZ0mk-wvV6CZsRpfKKov77vjBIQr9aaE0aNLkh15JG8KWTpmBZDFt8e6dbA8TuUTPDWzGBIgrSRnQA")
            .build();

    public static final AysTokenInvalidateRequest VALID_FOR_USER = AysTokenInvalidateRequest.builder()
            .refreshToken("eyJhbGciOiJSUzUxMiJ9.eyJqdGkiOiI5OGQ4MDBhMy0yMGYzLTQyOGQtYWNhNi0zNzE3NzBhNjZmMGEiLCJpc3MiOiJBWVMiLCJpYXQiOjE2ODIwMTk1MTksImV4cCI6MTY4MjEwNTkxOSwidHlwZSI6IkJlYXJlciIsInVzZXJuYW1lIjoiMjMyMTgwIn0.edhZL1P8mzG3HC6MxwL0PQ6ZYo6gKoRecV7AzE04KMNQ1JZayobtJFyrMyoeXzwVv6WgKR9Dw-j_SO13uxJxNKKw-ZMJvqgnSl_elZ31g4Hev05mR6KclE_yl22SG_rt54CzZl8ebQRY19m9Ryustmurr9jGaNT2vqPC2EJgrHSWB3meSwnfJm0fp6qtt0lBpsoFCiRrAWo2w8M_-BkMYjmcvmYSsitIEUk_dKrp5WaTaitZVp9Z6qRc8VGSE0ssVRXTlmSuDYBTP7KBJRCFdHvIvl2vpSBZQmYpmkLMhBjRJz803jBFK-CNJSMaW3-VCtRsRc1O_j66dJvW1iF-zQ")
            .build();

}
