package fr.testTechnique.subscriberAddressModification.application.infra;

import javax.persistence.*;

@Entity
@Table(name = "CONTRACT")
public class ContractTable {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String address;

    public ContractTable() {
    }

    public ContractTable(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
