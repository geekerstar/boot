package com.geekerstar.function.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @author geekerstar
 * @date 2023/4/9 13:36
 * https://mp.weixin.qq.com/s/saClo4819fv_Vbrv93qR2w
 */
public class JacksonTest {

    ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules();

    @Test
    public void pojoToJsonString() throws IOException {
        Person person = new Person();
        person.setName("aLng");
        person.setAge(27);
        person.setSkillList(Arrays.asList("java", "c++"));

        String json = objectMapper.writeValueAsString(person);
        System.out.println(json);
        String expectedJson = "{\"name\":\"aLng\",\"age\":27,\"skillList\":[\"java\",\"c++\"]}";
        Assertions.assertEquals(json, expectedJson);

        objectMapper.writeValue(new File("src/json/result.json"), json);
        // 或者
        byte[] jsonBytes = objectMapper.writeValueAsBytes(json);
        // 或者
        String jsonString = objectMapper.writeValueAsString(json);
    }

    @Test
    public void jsonStringToPojo() throws JsonProcessingException {
        String expectedJson = "{\"name\":\"aLang\",\"age\":27,\"skillList\":[\"java\",\"c++\"]}";
        Person person = objectMapper.readValue(expectedJson, Person.class);
        System.out.println(person);
        Assertions.assertEquals(person.getName(), "aLang");
        Assertions.assertEquals(person.getSkillList().toString(), "[java, c++]");
    }

    @Test
    public void testJsonFilePojo() throws IOException {
        File file = new File("src/json/Person.json");
        Person person = objectMapper.readValue(file, Person.class);
        // 或者
        // person = mapper.readValue(new URL("http://some.com/api/entry.json"), MyValue.class);
        System.out.println(person);
        Assertions.assertEquals(person.getName(), "aLang");
        Assertions.assertEquals(person.getSkillList().toString(), "[java, c++]");
    }

    @Test
    public void fileToPojoList() throws IOException {
        File file = new File("src/json/PersonList.json");
        List<Person> personList = objectMapper.readValue(file, new TypeReference<List<Person>>() {});
        for (Person person : personList) {
            System.out.println(person);
        }
        Assertions.assertEquals(personList.size(), 2);
        Assertions.assertEquals(personList.get(0).getName(), "aLang");
        Assertions.assertEquals(personList.get(1).getName(), "darcy");
    }

    @Test
    public void jsonStringToMap() throws IOException {
        String expectedJson = "{\"name\":\"aLang\",\"age\":27,\"skillList\":[\"java\",\"c++\"]}";
        Map<String, Object> employeeMap = objectMapper.readValue(expectedJson, new TypeReference<Map>() {});
        System.out.println(employeeMap.getClass());
        for (Map.Entry<String, Object> entry : employeeMap.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
        Assertions.assertEquals(employeeMap.get("name"), "aLang");
    }

    @Test
    public void jsonStringToPojoIgnoreProperties() throws IOException {
        // UnrecognizedPropertyException
        String json = "{\"yyy\":\"xxx\",\"name\":\"aLang\",\"age\":27,\"skillList\":[\"java\",\"c++\"]}";
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Person person = objectMapper.readValue(json, Person.class);
        System.out.printf(person.toString());
        Assertions.assertEquals(person.getName(), "aLang");
        Assertions.assertEquals(person.getSkillList().toString(), "[java, c++]");
    }

    @Test
    public void testPojoToJson0() throws JsonProcessingException {
        Order order = new Order(1, new Date(), null);
        String json = objectMapper.writeValueAsString(order);
        System.out.println(json);

        order = objectMapper.readValue(json, Order.class);
        System.out.println(order.toString());

        Assertions.assertEquals(order.getId(), 1);
    }

    /**
     * <dependency>
     *     <groupId>com.fasterxml.jackson.datatype</groupId>
     *     <artifactId>jackson-datatype-jsr310</artifactId>
     *     <version>2.13.3</version>
     * </dependency>
     * @throws JsonProcessingException
     */
    @Test
    public void testPojoToJson() throws JsonProcessingException {
        Order order = new Order(1, new Date(), LocalDateTime.now());
        String json = objectMapper.writeValueAsString(order);
        System.out.println(json);

        order = objectMapper.readValue(json, Order.class);
        System.out.println(order.toString());

        Assertions.assertEquals(order.getId(), 1);
    }

    @Test
    public void testPojoToJson1() throws JsonProcessingException {
        Cat cat = new Cat();
        cat.setName("Tom");
        cat.setAge(2);
        String json = objectMapper.writeValueAsString(cat);
        System.out.println(json);

        Assertions.assertEquals(json, "{\"name\":\"Tom\"}");

        cat = objectMapper.readValue(json, Cat.class);
        Assertions.assertEquals(cat.getName(), "Tom");
        Assertions.assertEquals(cat.getAge(), null);
    }

    @Test
    public void testPojoToJson2() throws JsonProcessingException {
        Cat cat = new Cat();
        cat.setName("Tom");
        cat.setAge(2);
        String json = objectMapper.writeValueAsString(cat);
        System.out.println(json);
        Assertions.assertEquals(json, "{\"age\":2,\"catName\":\"Tom\"}");
    }

    @Test
    public void testPojoToJson3() throws JsonProcessingException {
        String json = "{\"age\":2,\"catName\":\"Tom\"}";
        Cat cat = objectMapper.readValue(json, Cat.class);
        System.out.println(cat.toString());
        Assertions.assertEquals(cat.getName(), "Tom");
    }

    @Test
    public void testJsonToPojo() throws JsonProcessingException {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "aLang");
        map.put("age", 18);
        map.put("skill", "java");

        String json = objectMapper.writeValueAsString(map);
        System.out.println(json);

        Student student = objectMapper.readValue(json, Student.class);
        System.out.println(student);

        Assertions.assertEquals(student.getDiyMap().get("skill"), "java");
    }

    @Test
    public void testPojoToJsonTest() throws JsonProcessingException {
        Student2 student = new Student2();
        student.setName("aLang");
        student.setAge(20);
        String json = objectMapper.writeValueAsString(student);
        System.out.println(json);

        Assertions.assertEquals(json,"{\"name\":\"aLang\",\"age\":20,\"a\":111,\"b\":222,\"c\":333}");
    }
}
