package fr.testTechnique.subscriberAddressModification.application;

import fr.testTechnique.subscriberAddressModification.application.domain.Subscriber;
import fr.testTechnique.subscriberAddressModification.application.infra.SubscriberRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class SubscriberTestRepository implements SubscriberRepository {
    private Map<Long, Subscriber> subscribers = new HashMap<>();

    @Override
    public List<Subscriber> getSubscribers(String search) {
        return subscribers.values().stream()
                .filter(searchPredicate(search))
                .collect(Collectors.toList());
    }

    private Predicate<Subscriber> searchPredicate(String search) {
        return subscriber -> (subscriber.getFirstName().equals(search)
                || subscriber.getLastName().equals(search) || subscriber.getAddress().equals(search));
    }

    @Override
    public void update(Subscriber subscriber) {
        subscribers.put(subscriber.getId(), subscriber);
    }

    @Override
    public void create(Subscriber subscriber) {
        subscribers.put(subscriber.getId(), subscriber);
    }

    @Override
    public Subscriber getSubscriberById(Long id) {
        return subscribers.get(id);
    }
}
