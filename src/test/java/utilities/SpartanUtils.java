package utilities;

import com.github.javafaker.Faker;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

public class SpartanUtils {

    public static Map<String,Object> getMap(){

        Faker faker = new Faker();
        Map<String, Object> requestJsonMap = new LinkedHashMap<>();

        requestJsonMap.put("name", faker.name().firstName());
        requestJsonMap.put("gender", faker.demographic().sex());
        requestJsonMap.put("phone",  Long.parseLong(faker.numerify("##########")));

        return requestJsonMap;
    }

}
