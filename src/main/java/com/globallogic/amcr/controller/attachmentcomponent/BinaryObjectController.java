package com.globallogic.amcr.controller.attachmentcomponent;

import com.globallogic.amcr.exception.NotFoundException;
import com.globallogic.amcr.model.attachmentcomponent.BinaryObject;
import com.globallogic.amcr.model.attachmentcomponent.Metadata;
import com.globallogic.amcr.model.attachmentcomponent.Response;
import com.globallogic.amcr.service.attachmentcomponent.BinaryObjectService;

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

    @PostMapping("/metadata")
    public ResponseEntity<Metadata> saveBinary(@RequestBody Metadata metadata, BindingResult errors) {
        if (errors.hasErrors()) {
            throw new NotFoundException(errors.toString());
        }
        LOG.debug("Controller requesting a new BinaryObject to be saved with id {}", metadata.getId());
        Metadata incomingMetadata = binaryObjectService.save(metadata);
        return ResponseEntity.accepted().body(incomingMetadata);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<byte[]> getMediaInBytes(@PathVariable UUID id) {
        BinaryObject bo = binaryObjectService.get(id);
        if (bo == null) {
            LOG.debug("Controller requesting BinaryObject with ID that does not exist {}", id);
            return ResponseEntity.notFound().build();
        }
        byte[] bytes = bo.getMedia();
        if (bytes != null && bytes.length > 0) {
            LOG.debug("Controller requesting BinaryObject with ID {} that has no media", id);
            return ResponseEntity.ok().body(bytes);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(value = "/media/{id}")
    public void delete(@PathVariable UUID id) {
        LOG.debug("Controller requesting to delete metadata with ID {}", id);
        binaryObjectService.delete(id);
    }

    @PutMapping(value = "/media/{id}")
    public void get(@PathVariable UUID id, @RequestBody byte[] media) {
        LOG.debug("Controller requesting to update with ID {}", id);
        binaryObjectService.updateMedia(media, id);
    }

    @GetMapping("/media/{metadataId}")
    public ResponseEntity<Resource> getMedia(@PathVariable UUID metadataId) {
        Response response = binaryObjectService.getMedia(metadataId);
        return ResponseEntity.ok().contentType(MediaType.parseMediaType
                (response.getType())).header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + response.getName()
                        + "\"").body(new ByteArrayResource(response.getMedia()));
    }
}