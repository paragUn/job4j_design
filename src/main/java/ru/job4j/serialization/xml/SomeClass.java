package ru.job4j.serialization.xml;

import javax.xml.bind.annotation.*;
import java.util.Arrays;

@XmlRootElement(name = "someClass")
public class SomeClass {
    @XmlAttribute
    private boolean someBoolean;
    @XmlAttribute
    private int someInt;
    @XmlAttribute
    private String someString;
    private Contact someContact;
    @XmlElementWrapper(name = "SomeInts")
    @XmlElement(name = "someInt")
    private int[] someIntArray;

    public SomeClass() { }

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
}
