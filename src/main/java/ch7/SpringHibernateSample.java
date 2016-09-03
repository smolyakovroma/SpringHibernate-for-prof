package ch7;

import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.List;

public class SpringHibernateSample {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("spring-config-hibernate.xml");
        ctx.refresh();

        ContactDao contactDao = ctx.getBean("contactDao", ContactDao.class);
        System.out.println(contactDao.findAll());
//        listContact(contactDao.findAll());

    }

    private static void listContact(List<Contact> contacts) {
        for (Contact contact : contacts) {
            System.out.println(contact);
            if(contact.getContactTelDetails()!=null){
                for (ContactTelDetail contactTelDetail : contact.getContactTelDetails()) {
                    System.out.println("- "+contactTelDetail);
                }
            }
        }
    }
}
