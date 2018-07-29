package com.example.kapil.formtask1;

public class ApiUtils {

    public ApiUtils() {}

    public static final String BASE_URL = "http://api.shoocal.com/test/manager/democalltesting";

    public static APIService getAPIService() {

        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }
}
