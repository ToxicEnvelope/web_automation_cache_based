package com.amwell.auto.yahav.sample.pom.core.utils;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

public class RestManager {

    private static final RestAssured _ra = new RestAssured();

    /**
     *
     * @param uri
     * @param param
     * @param expects
     * @return
     */
    public static ValidatableResponse validateResponseOfValueInJSONBodyEqualTo(String uri, String param, int expects) {
        try {
            return _ra.get(uri)
                    .then()
                    .assertThat()
                        .body(param, equalTo(expects));
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     *
     * @param uri
     * @param param
     * @param items
     * @return
     */
    public static ValidatableResponse validateResponseOfJSONBodyHasItems(String uri, String param, int items[]) {
        try {
            return _ra.get(uri)
                    .then()
                    .assertThat()
                        .body(param, hasItems(items));
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     *
     * @param uri
     * @param map
     * @param expected
     * @return
     */
    public static List<ValidatableResponse> validateResponseOfParametersInJSONBodyWhenPOST(String uri, Map<String, String> map, String expected) {
        try {
            List<ValidatableResponse> vr = new ArrayList<>();
            for(Map.Entry<String, String> entry : map.entrySet()) {
                vr.add(
                    _ra.given().
                        param(entry.getKey(), entry.getValue()).
                    when().
                        post(uri).
                    then().
                        body(containsString(expected)));
            }
            return vr;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }






}
