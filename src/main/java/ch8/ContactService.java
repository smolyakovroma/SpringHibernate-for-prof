package ch8;

import java.util.List;

public interface ContactService {
//    List<Contact> findAll();
//    List<Contact> findAllWithDetail();
//    Contact findById(Long id);
//    Contact save(Contact contact);
//    void delete(Contact contact);
//    List<Contact> findAllByNativeQuery();
//    List<Contact> findByCriteriaQuery(String firstName, String lastName);

    List<Contact> findAll();
    List<Contact> findByFirstName(String firstName);
    List<Contact> findByFirstNameAndLastName(String firstName, String lastName);
}
