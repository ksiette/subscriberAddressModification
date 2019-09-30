package fr.testTechnique.subscriberAddressModification.application.infra;


import fr.testTechnique.subscriberAddressModification.application.domain.Subscriber;

import java.util.List;

public interface SubscriberRepository{
    List<Subscriber> getSubscribers(String search);

    void update(Subscriber subscriber);

    void create(Subscriber subscriber);

    Subscriber getSubscriberById(Long id);
}
