package zhiwenyan.cmccaifu.com.android2017.Jackson;

import android.os.Build;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Description:
 * Data：2018/9/23
 * Author:Steven
 */
public class JacksonTest {
    public static void main(String[] args) throws IOException {
        System.out.println("********object***************");

        ObjectMapper objectMapper = new ObjectMapper();
        String carJson =
                "{ \"brand\" : \"Mercedes\", \"doors\" : 5 }";

        Car car = objectMapper.readValue(carJson, Car.class);
        System.out.println(car.getBrand());
        System.out.println(car.getDoors());

        System.out.println("******数组**********");
        String jsonArray = "[{\"brand\":\"ford\"}, {\"brand\":\"Fiat\"}]";
        Car[] cars2 = objectMapper.readValue(jsonArray, Car[].class);
        System.out.println(cars2[0].toString());
        Gson gson = new Gson();
        List<Car> carList = gson.fromJson(jsonArray, new TypeToken<List<Car>>() {
        }.getType());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            carList.forEach(new Consumer<Car>() {
                @Override
                public void accept(Car car) {
                    System.out.println("===");
                    System.out.println(car.toString());
                }
            });
        }
        for (int i = 0; i < carList.size(); i++) {
            System.out.println(carList.get(i).toString());
        }
        System.out.println("******集合***********");
        List<Car> cars1 = objectMapper.readValue(jsonArray, new TypeReference<List<Car>>() {
        });
        System.out.println(cars1.get(0).toString());
        System.out.println("******map***********");

        String jsonObject = "{\"brand\":\"ford\", \"doors\":5}";

        Map<String, Object> jsonMap = objectMapper.readValue(jsonObject,
                new TypeReference<Map<String, Object>>() {
                });

        // System.out.println(jsonMap.entrySet().iterator().next().getValue());

        //ObjectMapper objectMapper = new ObjectMapper();
        System.out.println("**********null**************");

        //忽略为null的属性 不解析
        objectMapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);


        String carJson1 = "{ \"brand\":\"Toyota\", \"doors\":null }";

        Car car1 = objectMapper.readValue(carJson1, Car.class);
        System.out.println(car1.toString());

        System.out.println("******write  to  json**********");
        //write to json
        Car car6 = new Car();
        car.setBrand("BMW");
        car.setDoors(4);

        String json = objectMapper.writeValueAsString(car6);
        System.out.println(json);

        String carJson2 =
                "{ \"brand\" : \"Mercedes\", \"doors\" : 5 }";

        JsonFactory factory = new JsonFactory();
        JsonParser parser = factory.createParser(carJson2);
        while (!parser.isClosed()) {
            JsonToken jsonToken = parser.nextToken();

            System.out.println("jsonToken = " + jsonToken);
        }


        String carJson3 =
                "{ \"brand\" : \"Mercedes\", \"doors\" : 5 }";

        JsonFactory factory3 = new JsonFactory();
        JsonParser parser3 = factory3.createParser(carJson3);

        Car car3 = new Car();
        while (!parser.isClosed()) {
            JsonToken jsonToken = parser3.nextToken();

            if (JsonToken.FIELD_NAME.equals(jsonToken)) {
                String fieldName = parser3.getCurrentName();
                System.out.println(fieldName);

                jsonToken = parser3.nextToken();

                if ("brand".equals(fieldName)) {
                    car3.setBrand(parser3.getValueAsString());
                } else if ("doors".equals(fieldName)) {
                    car3.setDoors(parser3.getValueAsInt());
                }
            }
        }

        System.out.println("car.brand = " + car3.getBrand());
        System.out.println("car.doors = " + car3.getDoors());
    }
}
