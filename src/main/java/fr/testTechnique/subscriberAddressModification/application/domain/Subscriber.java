package fr.testTechnique.subscriberAddressModification.application.domain;


import java.util.ArrayList;
import java.util.Objects;

public class Subscriber {

    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private Contracts contracts;
    private static Long numberOfSubscribers = 0L;

    public Subscriber(String firstName, String lastName, String address) {
        this.id = numberOfSubscribers++;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        contracts = new Contracts(new ArrayList<>());
    }

    public Subscriber(Long id, String firstName, String lastName, String address) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        contracts = new Contracts(new ArrayList<>());

    }

    public void modifyAddress(String newAddress) {
        address = newAddress;
        contracts.modifyAddress(newAddress);
    }

    public String getAddress() {
        return address;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void addContract(Contract contract) {
        contracts.add(contract);
    }

    public Contracts getContracts() {
        return contracts;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Subscriber{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", contracts=" + contracts +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subscriber that = (Subscriber) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(address, that.address) &&
                Objects.equals(contracts, that.contracts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, address, contracts);
    }
}
