package com.globallogic.amcr.controller.contacts;

import com.globallogic.amcr.controller.WebUtil;
import com.globallogic.amcr.model.contacts.Contacts;
import com.globallogic.amcr.service.contacts.ContactsService;
import com.globallogic.amcr.utils.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/contacts")
@CrossOrigin
public class ContactsController {

    private final Logger LOG = LoggerFactory.getLogger(ContactsController.class);
    private final ContactsService contactsService;

    public ContactsController(ContactsService contactsService) {
        this.contactsService = Assert.assertNotNull(contactsService, "Contacts service cannot be null");
    }

    /**
     * @param contact the contacts page json object
     * @param errors  the error that will be thrown if an object is not found
     * @return returns a response entity either 201 or 500
     */
    @PostMapping(value = "/", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Contacts> saveContacts(@RequestBody @Validated Contacts contact, BindingResult errors) {
        if (errors.hasErrors()) {
            throw new IllegalArgumentException(errors.toString());
        }
        LOG.debug("Controller saving new contacts page data");
        Contacts createdContact = contactsService.save(contact);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdContact.getId()).toUri()).body(createdContact);
    }

    /**
     * @param id the ID of the contacts page object to be retrieved
     * @return returns a response entity either 200 or 500
     */
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Contacts> get(@PathVariable UUID id) {
        LOG.debug("Controller requesting contacts page data with ID {}", id);
        Contacts createdContact = contactsService.get(id);
        createdContact.setImageLink(WebUtil.generateUri("/attachment/{id}", createdContact.getImageId()).toString());
        return ResponseEntity.ok().body(createdContact);
    }

    /**
     * @return returns a response entity either 200 or 500
     */
    @GetMapping(value = "/", produces = "application/json")
    public ResponseEntity<List<Contacts>> getAll() {
        LOG.debug("Controller requesting all contacts page data");
        List<Contacts> allContacts = contactsService.getAll();
        for (Contacts contact : allContacts) {
            contact.setImageLink(WebUtil.generateUri("/attachment/{id}", contact.getImageId()).toString());
        }
        return ResponseEntity.ok().body(allContacts);
    }

    /**
     * @return returns a response entity either 200 or 500
     */
    @GetMapping(value = "/spotlight", produces = "application/json")
    public ResponseEntity<List<Contacts>> getSpotlitContacts() {
        LOG.debug("Controller requesting all spotlit contacts page data");
        List<Contacts> spotlitContacts = contactsService.getSpotlitContacts();
        for (Contacts contact : spotlitContacts) {
            contact.setImageLink(WebUtil.generateUri("/attachment/{id}", contact.getImageId()).toString());
        }
        return ResponseEntity.ok().body(spotlitContacts);
    }

    @GetMapping(value = "/{id}/attachment/{attachmentId}", produces = "application/json")
    public ModelAndView getAttachment(@PathVariable UUID id, @PathVariable UUID attachmentId) {
        return new ModelAndView("forward:/attachment/"+ attachmentId);
    }

    @GetMapping(value = "/spotlight/attachment/{attachmentId}", produces = "application/json")
    public ModelAndView getSpotlitAttachment(@PathVariable UUID attachmentId) {
        return new ModelAndView("forward:/attachment/"+ attachmentId);
    }

    /**
     * @param id      the id of the contacts page data to be updated
     * @param contact the contacts page object with the values that the database will be updated with
     * @return returns a response entity either 202 or 500
     */
    @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Contacts> update(@PathVariable UUID id, @RequestBody Contacts contact) {
        LOG.debug("Controller updating contacts page data with ID {}", id);
        return ResponseEntity.accepted().body(contactsService.update(id, contact));
    }

    /**
     * @param id the id of the contacts page data to be deleted
     * @return returns a response entity with no content
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        LOG.debug("Controller requesting deletion of contacts page data with ID {}", id);
        contactsService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
