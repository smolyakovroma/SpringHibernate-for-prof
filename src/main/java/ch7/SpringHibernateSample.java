package ch7;

import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.Date;
import java.util.List;

public class SpringHibernateSample {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("spring-config-hibernate.xml");
        ctx.refresh();

        ContactDao contactDao = ctx.getBean("contactDao", ContactDao.class);
//            listContact(contactDao.findAll());
//        listContact(contactDao.findAllWithDetail());
//        System.out.println(contactDao.findById(1l).toString());

//        Contact contact = new Contact () ;
//        contact.setFirstName("Michael");
//        contact.setLastName("Jackson");
//        contact.setBirthDate(new Date());
//        ContactTelDetail contactTelDetail =
//                new ContactTelDetail("Home", "1111111111");
//        contact.addContactTelDetail(contactTelDetail);
//        contactTelDetail = new ContactTelDetail("Mobile", "2222222222");
//        contact.addContactTelDetail(contactTelDetail);
//        contactDao.save(contact);
        Contact contact = contactDao.findById(2l);
        contactDao.delete(contact);
        listContact(contactDao.findAllWithDetail());
    }

    private static void listContact(List<Contact> contacts) {
        for (Contact contact : contacts) {
            System.out.println(contact);
            if(contact.getContactTelDetails()!=null){
                for (ContactTelDetail contactTelDetail : contact.getContactTelDetails()) {
                    System.out.println("- "+contactTelDetail);
                }
            }
            if (contact.getHobbies() != null) {
                for (Hobby hobby : contact.getHobbies())
                    System.out.println(hobby);
            }

        }
    }
}
