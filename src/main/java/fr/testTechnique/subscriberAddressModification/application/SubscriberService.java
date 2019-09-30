package fr.testTechnique.subscriberAddressModification.application;

import fr.testTechnique.subscriberAddressModification.application.domain.Subscriber;
import fr.testTechnique.subscriberAddressModification.application.infra.SubscriberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriberService {

    private SubscriberRepository subscriberRepository;

    @Autowired
    public SubscriberService(SubscriberRepository subscriberRepository) {
        this.subscriberRepository = subscriberRepository;
    }

    public List<Subscriber> getSubscribers(String search) {
        return subscriberRepository.getSubscribers(search);
    }

    public void modifyAddress(Subscriber subscriber, String newAddress) {
        subscriber.modifyAddress(newAddress);
        subscriberRepository.update(subscriber);
    }

    public Subscriber createSubscriber(String firstName, String lastName, String address) {
        Subscriber subscriber = new Subscriber(firstName, lastName, address);
        subscriberRepository.create(subscriber);
        return subscriber;
    }

    public Subscriber getSubscriberById(Long id) {
        return subscriberRepository.getSubscriberById(id);
    }
}
