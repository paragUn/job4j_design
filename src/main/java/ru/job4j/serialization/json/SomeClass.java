package ru.job4j.serialization.json;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SomeClass {
    private final boolean someBoolean;
    private final int someInt;
    private final String someString;
    private final Contact someContact;
    private final int[] someIntArray;

    public SomeClass(boolean someBoolean, int someInt, String someString, Contact someContact, int[] someIntArray) {
        this.someBoolean = someBoolean;
        this.someInt = someInt;
        this.someString = someString;
        this.someContact = someContact;
        this.someIntArray = someIntArray;
    }

    public boolean isSomeBoolean() {
        return someBoolean;
    }

    public int getSomeInt() {
        return someInt;
    }

    public String getSomeString() {
        return someString;
    }

    public Contact getSomeContact() {
        return someContact;
    }

    public int[] getSomeIntArray() {
        return someIntArray;
    }

    @Override
    public String toString() {
        return "SomeClass{"
                + "someBoolean=" + someBoolean
                + ", someInt=" + someInt
                + ", someString='" + someString + '\''
                + ", someContact=" + someContact
                + ", someIntArray=" + Arrays.toString(someIntArray) + '}';
    }

    public static void main(String[] args) {
        final SomeClass someClass = new SomeClass(
                true,
                10,
                "someString",
                new Contact("+7(900)000-00-00"),
                new int[] {1, 2, 3, 4}
        );



        JSONObject jsonObject = new JSONObject();
        jsonObject.put("true", someClass.isSomeBoolean());
        jsonObject.put("10", someClass.getSomeInt());
        jsonObject.put("someString", someClass.getSomeString());
        JSONObject jsonContact = new JSONObject("{\"phone\":\"+7(900)000-00-00\"}");
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        JSONArray jsonInts = new JSONArray(list);
        jsonObject.put("contact", jsonContact);
        jsonObject.put("someInts", jsonInts);

        /* Выведем результат в консоль */
        System.out.println(jsonObject.toString());

        /* Преобразуем объект person в json-строку */
        System.out.println(new JSONObject(someClass).toString());


    }
}
