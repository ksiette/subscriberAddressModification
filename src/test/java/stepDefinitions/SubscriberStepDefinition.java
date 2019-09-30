package stepDefinitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import fr.testTechnique.subscriberAddressModification.application.MainController;
import fr.testTechnique.subscriberAddressModification.application.SubscriberService;
import fr.testTechnique.subscriberAddressModification.application.SubscriberTestRepository;
import fr.testTechnique.subscriberAddressModification.application.domain.Contract;
import fr.testTechnique.subscriberAddressModification.application.domain.Subscriber;
import fr.testTechnique.subscriberAddressModification.application.forms.AddSubscriberForm;
import fr.testTechnique.subscriberAddressModification.application.forms.ModifyAddressForm;
import fr.testTechnique.subscriberAddressModification.application.forms.SearchForm;
import fr.testTechnique.subscriberAddressModification.application.forms.SubscribersForm;
import org.mockito.Mockito;
import org.springframework.ui.Model;

import static org.assertj.core.api.Assertions.assertThat;


public class SubscriberStepDefinition {


    private final String newAddress = "21 rue de Silly, Boulogne-Billancourt, France";
    private SubscriberService subscriberService = new SubscriberService(new SubscriberTestRepository());
    private SearchForm searchForm = new SearchForm();
    private final SubscribersForm subscribersForm = new SubscribersForm();
    private AddSubscriberForm addSubscriberForm = new AddSubscriberForm();

    private final ModifyAddressForm modifyAddressForm = new ModifyAddressForm();
    private MainController mainController = new MainController(subscriberService, searchForm, subscribersForm, addSubscriberForm, modifyAddressForm);


    private Model model = Mockito.mock(Model.class);

    @Given("^un abonné avec une adresse principale active en France$")
    public void a_subscriber_with_address_in_France() throws Throwable {
        addSubscriberForm.setFirstName("thomas");
        addSubscriberForm.setLastName("dupond");
        String address = "14 rue de Silly, Boulogne-Billancourt, France";
        addSubscriberForm.setAddress(address);
        mainController.addSubscriber(model, addSubscriberForm);
        searchForm.setSearch(address);
        mainController.searchSubscriber(model, searchForm);
        Subscriber subscriber = subscribersForm.getSubscribers().get(0);
        subscriber.addContract(new Contract(address));
    }

    @When("^le conseiller connecté à (.*) modifie l'adresse de l'abonné sans date d'effet$")
    public void address_modification(String canal) throws Throwable {
        modifyAddressForm.setId(0L);
        modifyAddressForm.setAddress(newAddress);
        mainController.modifyAddress(model, modifyAddressForm);
    }

    @Then("^l'adresse de l'abonné est enregistrée sur l'ensemble des contrats de l'abonné$")
    public void subscriber_address_is_modified_on_all_subscriber_contracts() throws Throwable {
        searchForm.setSearch(newAddress);
        mainController.searchSubscriber(model, searchForm);
        Subscriber subscriber = subscribersForm.getSubscribers().get(0);
        assertThat(subscriber.getContracts().getSubscriberContractAddresses()).containsExactly(newAddress);
    }

    @And("^un mouvement de modification d'adresse est créé$")
    public void modification_movement() throws Throwable {
//
    }

}
