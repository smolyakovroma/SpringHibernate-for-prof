package ch8;

import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.List;

public class SpringJPASample {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("spring-config-jpa.xml");
        ctx.refresh();

        ContactService contactService = ctx.getBean("springJpaContactService", ContactService.class);
        List<Contact> contacts = contactService.findAll();
        listContacts(contacts);
        System.out.println("----------------");
        listContacts(contactService.findByFirstName("Chris"));
        System.out.println("----------------");
        listContacts(contactService.findByFirstNameAndLastName("Chris", "Schaefer"));
    }

    private static void listContacts(List<Contact> contacts) {
        for (Contact contact : contacts) {
            System.out.println(contact);
        }
    }
}
