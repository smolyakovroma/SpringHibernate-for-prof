package ch8;

import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class SpringJPASample {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("spring-config-jpa.xml");
        ctx.refresh();

        ContactService contactService = ctx.getBean("jpaContactService", ContactService.class);

//        Contact contact = new Contact();
//        contact.setFirstName("Michael");
//        contact.setLastName("Jackson");
//        contact.setBirthDate(new Date());
//        ContactTelDetail contactTelDetail=new ContactTelDetail("Home", "1111111111");
//        contact.addContactTelDetail(contactTelDetail);
//        contactTelDetail = new ContactTelDetail("Mo‹ile", "2222222222");
//        contact.addContactTelDetail(contactTelDetail);
//        contactService.save(contact);
//        listContact(contactService.findAllWithDetail());

//        Contact contact = contactService.findById(2l);
//        System.out.println(contact.toString());
//        listContact(contactService.findAllWithDetail());

//        ContactSummaryUntypeImpl contactSummaryUntype = ctx.getBean("contactSummaryUntype",ContactSummaryUntypeImpl.class);
//        contactSummaryUntype.displayAllContactSummary();

//        ContactSummaryService contactSummaryService = ctx.getBean("contactSummaryService", ContactSummaryService.class);
//        List<ContactSummary> contacts = contactSummaryService.findAll();
//        for (ContactSummary contact : contacts) {
//            System.out.println(contact);
//        }

//        listContact(contactService.findAllByNativeQuery());

//        Contact contact = contactService.findById(1l);
//        System.out.println(contact);
//        contact.setFirstName("Roman");
//        Set<ContactTelDetail> contactTelDetails = contact.getContactTelDetails();
//        Iterator<ContactTelDetail> iterator = contactTelDetails.iterator();
//        while (iterator.hasNext()){
//            ContactTelDetail telDetail = iterator.next();
//            if(telDetail.getTelType().equals("Home")){
//                iterator.remove();
//            }
//        }
//        contactService.save(contact);
//
//        contact = contactService.findById(2l);
//        contactService.delete(contact);
//        listContact(contactService.findAllWithDetail());

        List<Contact> contacts = contactService.findByCriteriaQuery("Chris", null);
        listContact(contacts);
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
