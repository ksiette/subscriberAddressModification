package fr.testTechnique.subscriberAddressModification.application.forms;

import fr.testTechnique.subscriberAddressModification.application.domain.Subscriber;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SubscribersForm {
    private List<Subscriber> subscribers = new ArrayList<>();

    public List<Subscriber> getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(List<Subscriber> subscribers) {
        this.subscribers = subscribers;
    }

    public void clear() {
        subscribers.clear();
    }

}
