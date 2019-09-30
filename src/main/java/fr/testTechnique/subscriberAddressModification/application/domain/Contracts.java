package fr.testTechnique.subscriberAddressModification.application.domain;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Contracts {
    private List<Contract> contracts;

    public Contracts(List<Contract> contracts) {
        this.contracts = contracts;
    }


    public void modifyAddress(String newAddress) {
        contracts.forEach(contract -> contract.setAddress(newAddress));

    }

    public void add(Contract contract) {
        contracts.add(contract);
    }


    public <T> List<T> mapContract(Function<Contract, T> contractFunction) {
        return contracts
                .stream()
                .map(contractFunction)
                .collect(Collectors.toList());
    }

    public List<String> getSubscriberContractAddresses(){
        return mapContract(Contract::getAddress);
    }

    public int numberOfContracts(){
        return contracts.size();
    }

    @Override
    public String toString() {
        return "{" +
                "contracts=" + contracts +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contracts contracts1 = (Contracts) o;
        return Objects.equals(contracts, contracts1.contracts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contracts);
    }
}
