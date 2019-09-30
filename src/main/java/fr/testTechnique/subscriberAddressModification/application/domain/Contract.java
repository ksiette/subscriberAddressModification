package fr.testTechnique.subscriberAddressModification.application.domain;

import java.util.Objects;

public class Contract {
    private String address;

    public Contract(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    @Override
    public String toString() {
        return "Contract{" +
                "address='" + address + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contract contract = (Contract) o;
        return Objects.equals(address, contract.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address);
    }
}
