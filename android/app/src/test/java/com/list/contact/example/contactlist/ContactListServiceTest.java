package com.list.contact.example.contactlist;

import android.util.Log;

import com.list.contact.example.contactlist.api.ContactListService;
import com.list.contact.example.contactlist.mock.ContactListMock;
import com.list.contact.example.contactlist.model.Contact;
import com.list.contact.example.contactlist.model.SendMoneyForm;
import com.list.contact.example.contactlist.model.Transfer;
import com.list.contact.example.contactlist.model.User;

import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import rx.Scheduler;
import rx.android.plugins.RxAndroidPlugins;
import rx.android.plugins.RxAndroidSchedulersHook;
import rx.observers.TestSubscriber;
import rx.schedulers.Schedulers;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by wesley on 9/1/16.
 */
public class ContactListServiceTest {
    private static final String TAG = "ContactListServiceTest";
    private static String token = null;
    private List<Contact> contacts;

    private ContactListService contactListService;

    @Before
    public void configuration() {
        contacts = ContactListMock.getContactList();
        contactListService = new ContactListService();

        RxAndroidPlugins.getInstance().registerSchedulersHook(new RxAndroidSchedulersHook() {
            @Override
            public Scheduler getMainThreadScheduler() {
                return Schedulers.newThread();
            }
        });
    }

    @After
    public void tearDown() {
        RxAndroidPlugins.getInstance().reset();
    }

    @Test
    public void getNewTokenFromUser() {
        TestSubscriber<String> stringTestSubscriber = new TestSubscriber<>();
        contactListService.generateToken(new User("Bruce Wayne", "bruce@batman.com"), stringTestSubscriber);
        stringTestSubscriber.awaitTerminalEvent();
        stringTestSubscriber.assertNoErrors();
        stringTestSubscriber.assertCompleted();
        token = stringTestSubscriber.getOnNextEvents().get(0);
        Log.d(TAG, token);
        assertNotNull(token);
    }

    @Test
    public void sendMoneyToClient01Value33() {
        TestSubscriber<Boolean> booleanTestSubscriber = new TestSubscriber<>();
        SendMoneyForm form = new SendMoneyForm("001", token, 33.0);
        contactListService.sendMoney(form, booleanTestSubscriber);
        booleanTestSubscriber.awaitTerminalEvent();
        booleanTestSubscriber.assertNoErrors();
        booleanTestSubscriber.assertCompleted();
        assertTrue(booleanTestSubscriber.getOnNextEvents().get(0));
    }

    @Test
    public void sendMoneyToClient01EmptyToken() {
        TestSubscriber<Boolean> booleanTestSubscriber = new TestSubscriber<>();
        SendMoneyForm form = new SendMoneyForm("001", "", 33.0);
        contactListService.sendMoney(form, booleanTestSubscriber);
        booleanTestSubscriber.awaitTerminalEvent();
        booleanTestSubscriber.assertNoErrors();
        booleanTestSubscriber.assertCompleted();
        assertTrue(booleanTestSubscriber.getOnNextEvents().get(0));
    }

    @Test
    public void sendMoneyToClient01NullToken() {
        TestSubscriber<Boolean> booleanTestSubscriber = new TestSubscriber<>();
        SendMoneyForm form = new SendMoneyForm("001", null, 33.0);
        contactListService.sendMoney(form, booleanTestSubscriber);
        booleanTestSubscriber.awaitTerminalEvent();
        booleanTestSubscriber.assertNoErrors();
        booleanTestSubscriber.assertCompleted();
        assertTrue(booleanTestSubscriber.getOnNextEvents().get(0));
    }

    @Test
    public void getTransferFirstValueFromClient01() {
        TestSubscriber<List<Transfer>> transfersTestSubscriber = new TestSubscriber<>();
        contactListService.getTransfers(token, transfersTestSubscriber);
        transfersTestSubscriber.awaitTerminalEvent();
        transfersTestSubscriber.assertNoErrors();
        transfersTestSubscriber.assertCompleted();
        List<Transfer> transferList = transfersTestSubscriber.getOnNextEvents().get(0);
        Transfer firstTransfer = transferList.get(0);
        assertEquals(firstTransfer.getValue(), 33.0, 1.0);
        MatcherAssert.assertThat(transferList.size(), is(greaterThan(0)));
    }
}