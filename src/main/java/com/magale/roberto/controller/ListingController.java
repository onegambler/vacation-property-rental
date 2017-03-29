package com.magale.roberto.controller;

import com.magale.roberto.exceptions.InternalServerException;
import com.magale.roberto.exceptions.ListingNotFoundException;
import com.magale.roberto.model.PropertyListing;
import com.magale.roberto.service.ListingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@RestController
@RequestMapping(value = "/listings", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
public class ListingController {

    private static Logger LOGGER = LoggerFactory.getLogger(ListingController.class);

    private final ListingService listingService;

    @Autowired
    public ListingController(ListingService listingService) {
        this.listingService = listingService;
    }

    @ResponseBody
    @ResponseStatus(value = NO_CONTENT)
    @RequestMapping(value = "/{id}", method = GET)
    public PropertyListing getListing(@PathVariable("id") String id) {
        try {
            return listingService.getListing(id);
        } catch (ListingNotFoundException e) {
            throw e;
        } catch (Exception e) {
            String errorMessage = String.format("Failed to get listing with id %s", id);
            LOGGER.error(errorMessage, e);
            throw new InternalServerException(errorMessage);
        }
    }

    @ResponseStatus(CREATED)
    @RequestMapping(method = POST)
    public PropertyListing createListing(@RequestBody PropertyListing propertyListing) {
        try {
            return listingService.addListing(propertyListing);
        } catch (Exception e) {
            String errorMessage = "Failed to add new listing";
            LOGGER.error(errorMessage, e);
            throw new InternalServerException(errorMessage);
        }
    }

    @ResponseStatus(OK)
    @RequestMapping(value = "/{id}", method = PUT)
    public PropertyListing updateListing(@PathVariable("id") String id, @RequestBody PropertyListing propertyListing) {
        try {
            return listingService.updateListing(id, propertyListing);
        } catch (ListingNotFoundException e) {
            throw e;
        } catch (Exception e) {
            String errorMessage = String.format("Failed to update listing with id %s", id);
            LOGGER.error(errorMessage, e);
            throw new InternalServerException(errorMessage);
        }
    }

    @ResponseStatus(NO_CONTENT)
    @RequestMapping(value = "/{id}", method = DELETE)
    public void deleteListing(@PathVariable("id") String id) {
        try {
            listingService.deleteListing(id);
        } catch (ListingNotFoundException e) {
            throw e;
        } catch (Exception e) {
            String errorMessage = String.format("Failed to delete listing with id %s", id);
            LOGGER.error(errorMessage, e);
            throw new InternalServerException(errorMessage);
        }
    }
}
