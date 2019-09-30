package fr.testTechnique.subscriberAddressModification.application.forms;

import org.springframework.stereotype.Component;

@Component
public class ModifyAddressForm {
    private Long id;
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
