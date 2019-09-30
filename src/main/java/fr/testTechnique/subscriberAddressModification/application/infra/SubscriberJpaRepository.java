package fr.testTechnique.subscriberAddressModification.application.infra;

import fr.testTechnique.subscriberAddressModification.application.domain.Subscriber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Transactional
public class SubscriberJpaRepository implements SubscriberRepository{

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Subscriber> getSubscribers(String search) {
        List<SubscriberTable> resultList = getSubscriberTables(search);

        return resultList.stream()
                .map(subscriberTable ->
                        new Subscriber(subscriberTable.getId(), subscriberTable.getFirstName(), subscriberTable.getLastName(), subscriberTable.getAddress()))
                .collect(Collectors.toList());
    }

    @Override
    public void update(Subscriber subscriber) {
        SubscriberTable subscriberTable = getSubscriberTableById(subscriber.getId());
        if (subscriberTable == null) {
            throw new IllegalStateException("subscriber doesn't exist");
        }

        subscriberTable.setContracts(subscriber.getContracts().mapContract(contract -> new ContractTable(contract.getAddress())));
        subscriberTable.setAddress(subscriber.getAddress());
        entityManager.merge(subscriberTable);
    }

    private List<SubscriberTable> getSubscriberTables(String search) {
        Query query = entityManager.createQuery(
                "Select subscriber From SubscriberTable subscriber where subscriber.firstName = :search OR subscriber.lastName = :search OR subscriber.address = :search");
        query.setParameter("search", search);
        return (List<SubscriberTable>) query.getResultList();
    }



    @Override
    public void create(Subscriber subscriber) {
        entityManager.persist(new SubscriberTable(subscriber.getId(), subscriber.getFirstName(), subscriber.getLastName(), subscriber.getAddress()));
    }


    public SubscriberTable getSubscriberTableById(Long id) {
        return entityManager.find(SubscriberTable.class, id);
    }

    @Override
    public Subscriber getSubscriberById(Long id) {
        SubscriberTable subscriberTable = getSubscriberTableById(id);
        return new Subscriber(subscriberTable.getId(), subscriberTable.getFirstName(), subscriberTable.getLastName(), subscriberTable.getAddress());
    }


}
