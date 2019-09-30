package fr.testTechnique.subscriberAddressModification.application;

import fr.testTechnique.subscriberAddressModification.application.domain.Subscriber;
import fr.testTechnique.subscriberAddressModification.application.forms.AddSubscriberForm;
import fr.testTechnique.subscriberAddressModification.application.forms.ModifyAddressForm;
import fr.testTechnique.subscriberAddressModification.application.forms.SearchForm;
import fr.testTechnique.subscriberAddressModification.application.forms.SubscribersForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class MainController {

    private final SubscriberService subscriberService;

    private final SearchForm searchForm;

    private final SubscribersForm subscribersForm;

    private final AddSubscriberForm addSubscriberForm;
    private final ModifyAddressForm modifyAddressForm;

    @Autowired
    public MainController(SubscriberService subscriberService, SearchForm searchForm, SubscribersForm subscribersForm, AddSubscriberForm addSubscriberForm, ModifyAddressForm modifyAddressForm) {
        this.subscriberService = subscriberService;
        this.searchForm = searchForm;
        this.subscribersForm = subscribersForm;
        this.addSubscriberForm = addSubscriberForm;
        this.modifyAddressForm = modifyAddressForm;
    }

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("searchForm", searchForm);
        model.addAttribute("subscribersForm", subscribersForm);
        model.addAttribute("addSubscriberForm", addSubscriberForm);
        return "home";
    }

    @RequestMapping(value = {"/addSubscriber"}, method = RequestMethod.POST)
    public String addSubscriber(Model model, @ModelAttribute("addSubscriberForm") AddSubscriberForm addSubscriberForm) {

        String firstName = addSubscriberForm.getFirstName();
        String lastName = addSubscriberForm.getLastName();
        String address = addSubscriberForm.getAddress();

        if (!StringUtils.isEmpty(firstName) && !StringUtils.isEmpty(lastName)) {
            subscriberService.createSubscriber(firstName, lastName, address);
        }
        model.addAttribute("searchForm", searchForm);
        model.addAttribute("subscribersForm", subscribersForm);


        return "home";
    }


    @RequestMapping(value = {"/searchSubscriber"}, method = RequestMethod.POST)
    public String searchSubscriber(Model model, @ModelAttribute("searchForm") SearchForm searchForm) {

        String search = searchForm.getSearch();
        subscribersForm.clear();

        if (!StringUtils.isEmpty(search)) {
            List<Subscriber> subscribers = subscriberService.getSubscribers(search);
            subscribersForm.setSubscribers(subscribers);
        }
        model.addAttribute("subscribersForm", subscribersForm);
        model.addAttribute("addSubscriberForm", addSubscriberForm);

        return "home";
    }

    @RequestMapping(value = {"/modifyAddress"}, method = RequestMethod.POST)
    public String modifyAddress(Model model, @ModelAttribute("modifyAddressForm") ModifyAddressForm modifyAddressForm) {

        String address = modifyAddressForm.getAddress();
        if (!StringUtils.isEmpty(address) ) {
            Subscriber subscriber = subscriberService.getSubscriberById(modifyAddressForm.getId());
            subscriberService.modifyAddress(subscriber, address);
        }

        model.addAttribute("searchForm", searchForm);
        model.addAttribute("subscribersForm", subscribersForm);
        model.addAttribute("addSubscriberForm", addSubscriberForm);

        return "home";
    }
}