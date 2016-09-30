package com.list.contact.example.contactlist.mock;

import com.list.contact.example.contactlist.R;
import com.list.contact.example.contactlist.model.Contact;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by wesley on 9/1/16.
 */
public class ContactListMock {
    private static List<Contact> contactList;

    static {
        contactList = new LinkedList<>();
        contactList.add(new Contact("001", "Matilde Filipa", "(11) 987-254-855", R.drawable.userpic1));
        contactList.add(new Contact("002", "Isabel Talita", "(11) 855-871-542", null));
        contactList.add(new Contact("003", "Saturnino Graça", "(11) 871-542-823", R.drawable.userpic2));
        contactList.add(new Contact("004", "Jerónimo Brás", "(11) 542-823-132", R.drawable.userpic3));
        contactList.add(new Contact("005", "Cahir Virgie", "(11) 823-132-988", null));
        contactList.add(new Contact("006", "Florinda Tereza", "(11) 132-988-166", R.drawable.userpic4));
        contactList.add(new Contact("007", "Priscila Gaspar", "(11) 988-166-856", R.drawable.userpic5));
        contactList.add(new Contact("008", "Noemí Régulo", "(11) 166-856-421", R.drawable.userpic11));
        contactList.add(new Contact("009", "Rúben Celio", "(11) 856-421-155", null));
        contactList.add(new Contact("010", "Dionisio Amador", "(11) 421-155-111", R.drawable.userpic6));
        contactList.add(new Contact("011", "Juliana Remigio", "(11) 155-111-827", null));
        contactList.add(new Contact("012", "Guilherme Elisabete", "(11) 111-827-461", R.drawable.userpic7));
        contactList.add(new Contact("013", "Renato Cândido", "(11) 827-461-414", R.drawable.userpic8));
        contactList.add(new Contact("014", "Eva Brunilda", "(11) 461-414-143", R.drawable.userpic9));
        contactList.add(new Contact("015", "Guilherme Domingos", "(11) 461-143-112", R.drawable.userpic10));
    }

    public static List<Contact> getContactList() {
        return contactList;
    }
}
