package fr.testTechnique.subscriberAddressModification.application.domain;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SubscriberTest {

    private final String firstName = "thomas";
    private final String lastName = "dupont";
    private final String address = "14 rue de Silly, Boulogne-Billancourt, France";
    private Subscriber subscriber = new Subscriber(firstName, lastName, address);

    @Test
    public void should_get_first_name() {
        assertThat(subscriber.getFirstName()).isEqualTo(firstName);
    }

    @Test
    public void should_get_last_name() {
        assertThat(subscriber.getLastName()).isEqualTo(lastName);
    }

    @Test
    public void should_get_address() {
        assertThat(subscriber.getAddress()).isEqualTo(address);
    }


    @Test
    public void should_add_contract() {
        assertThat(subscriber.getContracts().numberOfContracts()).isEqualTo(0);

        subscriber.addContract(new Contract(address));

        assertThat(subscriber.getContracts().numberOfContracts()).isEqualTo(1);
    }

    @Test
    public void should_modify_address() {
        subscriber.addContract(new Contract(address));

        String newAddress = "51 rue de Silly, Boulogne-Billancourt, France";
        subscriber.modifyAddress(newAddress);
        assertThat(subscriber.getContracts().getSubscriberContractAddresses()).containsExactly(newAddress);
    }

}