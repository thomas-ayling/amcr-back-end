package com.globallogic.amcr.attachment_attempt_two.controller;

import com.globallogic.amcr.attachment_attempt_two.model.attachment_attempt_two.BinaryObject;
import com.globallogic.amcr.attachment_attempt_two.model.attachment_attempt_two.Metadata;
import com.globallogic.amcr.attachment_attempt_two.model.attachment_attempt_two.Response;
import com.globallogic.amcr.attachment_attempt_two.service.BinaryObjectService;
import com.globallogic.amcr.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/binary")
@CrossOrigin
public class BinaryObjectController {
    private final BinaryObjectService binaryObjectService;
    private final Logger LOG = LoggerFactory.getLogger(BinaryObject.class);

    public BinaryObjectController(BinaryObjectService binaryObjectService) {
        this.binaryObjectService = binaryObjectService;
    }

    /**
     * @param metadata
     * @param errors
     * @return the metadata + mediaId (the file id)
     */
    @PostMapping("/metadata")
    public ResponseEntity<Metadata> saveBinary(@RequestBody Metadata metadata, BindingResult errors) {
        if (errors.hasErrors()) {
            throw new NotFoundException(errors.toString());
        }
        LOG.debug("Controller requesting a new BinaryObject to be saved with id {}", metadata.getId());
        Metadata incomingMetadata = binaryObjectService.save(metadata);
        return ResponseEntity.accepted().body(incomingMetadata);
    }

    /**
     * @param id
     * @return - gets the binary object - HEEEEEEEEEEEEEEEEEEEEEEEERE
     */
    @GetMapping(value = "/{id}")
    public BinaryObject get(@PathVariable UUID id) {
        LOG.debug("Controller requesting BinaryObject with ID {}", id);
        return binaryObjectService.get(id);

//        BinaryObject bo = new BinaryObject();
//        byte[] b = bo.getMedia();
//        if (b != null && b.length > 0) {
//            LOG.debug("Controller requesting BinaryObject with ID {}", id);
//            return binaryObjectService.get(id);
//        } else {
//            throw new RuntimeException("There is no binary");
//        }

    }

    /**
     * @param id
     * @param media You put the file you want to attach
     */
    @PutMapping(value = "/media/{id}")
    public void get(@PathVariable UUID id, @RequestBody byte[] media) {
        LOG.debug("Controller requesting to update with ID {}", id);
        binaryObjectService.updateMedia(media, id);
    }

    /**
     * @param metadataId
     * @return returns the file you have attached
     */
    @GetMapping("/media/{metadataId}")
    public ResponseEntity<Resource> getMedia(@PathVariable UUID metadataId) {
        Response response = binaryObjectService.getMedia(metadataId);
        System.out.println(response);
        System.out.println("HELLO");
        return ResponseEntity.ok().contentType(MediaType.parseMediaType
                (response.getType())).header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + response.getName()
                        + "\"").body(new ByteArrayResource(response.getMedia()));
    }
}
