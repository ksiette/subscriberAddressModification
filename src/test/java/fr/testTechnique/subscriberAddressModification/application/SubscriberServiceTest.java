package fr.testTechnique.subscriberAddressModification.application;

import fr.testTechnique.subscriberAddressModification.application.domain.Contract;
import fr.testTechnique.subscriberAddressModification.application.domain.Contracts;
import fr.testTechnique.subscriberAddressModification.application.domain.Subscriber;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class SubscriberServiceTest {

    private SubscriberService subscriberService;
    private final String firstName = "thomas";
    private final String lastName = "dupont";
    private final String address = "14 rue de Silly, Boulogne-Billancourt, France";

    @Before
    public void setUp() throws Exception {
        SubscriberTestRepository subscriberRepository = new SubscriberTestRepository();
        subscriberService = new SubscriberService(subscriberRepository);
    }

    @Test
    public void should_create_a_subscriber() {
        Subscriber subscriber = subscriberService.createSubscriber(firstName, lastName, address);

        assertThat(subscriber).hasFieldOrPropertyWithValue("firstName", firstName)
                .hasFieldOrPropertyWithValue("lastName", lastName)
                .hasFieldOrPropertyWithValue("address", address);
        assertThat(subscriber.getContracts()).isEqualToComparingFieldByField(new Contracts(new ArrayList<>()));
    }

    @Test
    public void should_get_subscriber_by_Id() {
        Subscriber subscriber = subscriberService.createSubscriber(firstName, lastName, address);

        Subscriber subscriberById = subscriberService.getSubscriberById(subscriber.getId());
        assertThat(subscriberById).isEqualTo(new Subscriber(subscriber.getId(), firstName, lastName, address));
    }

    @Test
    public void should_search_subscribers() {
        Subscriber subscriber = subscriberService.createSubscriber(firstName, lastName, address);

        List<Subscriber> subscribers = subscriberService.getSubscribers(address);

        assertThat(subscribers).hasSize(1);
        assertThat(subscribers).contains(new Subscriber(subscriber.getId(), firstName, lastName, address));

        subscribers = subscriberService.getSubscribers("pierre");
        assertThat(subscribers).isEmpty();
    }

    @Test
    public void should_modifyAddress() {
        Subscriber subscriber = subscriberService.createSubscriber(firstName, lastName, address);
        subscriber.addContract(new Contract(address));
        subscriber.addContract(new Contract(address));

        String newAddress = "21 rue de Silly, Boulogne-Billancourt, France";
        subscriberService.modifyAddress(subscriber, newAddress);

        subscriber = subscriberService.getSubscriberById(subscriber.getId());

        assertThat(subscriber.getAddress()).isEqualTo(newAddress);
        assertThat(subscriber.getContracts().getSubscriberContractAddresses()).containsExactly(newAddress, newAddress);

    }

}