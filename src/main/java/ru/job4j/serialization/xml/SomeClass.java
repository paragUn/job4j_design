package ru.job4j.serialization.xml;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.Arrays;

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
    }
}
