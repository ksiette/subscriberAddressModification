package fr.testTechnique.subscriberAddressModification.application.infra;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "SUBSCRIBER")
public class SubscriberTable {

    @Id
    private Long id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String address;

    @Column
    @OneToMany
    private List<ContractTable> contracts;

    public SubscriberTable() {
    }

    public SubscriberTable(Long id, String firstName, String lastName, String address) {
        this.id = id;

        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        contracts = new ArrayList<>();
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public List<ContractTable> getContracts() {
        return contracts;
    }

    public void setContracts(List<ContractTable> contracts) {
        this.contracts = contracts;
    }

    public Long getId() {
        return id;
    }
}
