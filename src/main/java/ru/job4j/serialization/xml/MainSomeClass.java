package ru.job4j.serialization.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

public class MainSomeClass {
    public static void main(String[] args) throws Exception {
        final SomeClass someClass = new SomeClass(
                true,
                10,
                "someString",
                new Contact("+7(900)000-00-00"),
                new int[] {1, 2, 3, 4}
        );

        JAXBContext context = JAXBContext.newInstance(SomeClass.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(someClass, writer);
            String result = writer.getBuffer().toString();
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            SomeClass result = (SomeClass) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
    }
}
