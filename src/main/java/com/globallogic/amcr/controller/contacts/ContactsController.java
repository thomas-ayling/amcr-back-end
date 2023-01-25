package com.globallogic.amcr.controller.contacts;

import com.globallogic.amcr.exception.NotFoundException;
import com.globallogic.amcr.model.contacts.Contacts;
import com.globallogic.amcr.service.contacts.ContactsService;
import com.globallogic.amcr.utils.Assert;
import org.apache.ibatis.annotations.Param;
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

    private final Logger Log = LoggerFactory.getLogger(ContactsController.class.getName());
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
            throw new NotFoundException(errors.toString());
        }
        Log.debug("Controller saving new contacts page data");
        Contacts createdContact = contactsService.save(contact);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdContact.getId()).toUri()).body(createdContact);
    }

    /**
     * @param id the ID of the contacts page object to be retrieved
     * @return returns a response entity either 200 or 500
     */
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Contacts> get(@PathVariable UUID id) {
        Log.debug("Controller requesting contacts page data with ID {}", id);
        return ResponseEntity.ok().body(contactsService.get(id));
    }

    /**
     * @return returns a response entity either 200 or 500
     */
    @GetMapping(value = "/", produces = "application/json")
    public ResponseEntity<List<Contacts>> getAll() {
        Log.debug("Controller requesting all contacts page data");
        return ResponseEntity.ok().body(contactsService.getAll());
    }

    /**
     * @return returns a response entity either 200 or 500
     */
    @GetMapping(value = "/spotlight", produces = "application/json")
    public ResponseEntity<List<Contacts>> getSpotlitContacts() {
        Log.debug("Controller requesting all spotlit contacts page data");
        return ResponseEntity.ok().body(contactsService.getSpotlitContacts());
    }

    @GetMapping(value = "/{id}/attachment/{attachmentId}", produces = "application/json")
    public ModelAndView getAttachment(@PathVariable UUID id, @PathVariable UUID attachmentId) {
        return new ModelAndView("forward:/attachment/"+ attachmentId);
    }

    @GetMapping(value = "/{id}/spotlight/attachment/{attachmentId}", produces = "application/json")
    public ModelAndView getSpotlitAttachment(@PathVariable UUID id, @PathVariable UUID attachmentId) {
        return new ModelAndView("forward:/attachment/"+ attachmentId);
    }

    /**
     * @param id      the id of the contacts page data to be updated
     * @param contact the contacts page object with the values that the database will be updated with
     * @return returns a response entity either 202 or 500
     */
    @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Contacts> update(@PathVariable UUID id, @RequestBody Contacts contact) {
        Log.debug("Controller updating contacts page data with ID {}", id);
        return ResponseEntity.accepted().body(contactsService.update(id, contact));
    }

    /**
     * @param id the id of the contacts page data to be deleted
     * @return returns a response entity with no content
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        Log.debug("Controller requesting deletion of contacts page data with ID {}", id);
        contactsService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
