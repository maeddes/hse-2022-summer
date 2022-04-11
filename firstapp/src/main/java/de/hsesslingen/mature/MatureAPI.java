package de.hsesslingen.mature;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/v2")
public class MatureAPI {

    final Logger logger = LoggerFactory.getLogger(de.hsesslingen.mature.MatureAPI.class);

    @Value("${HOSTNAME:not_found}")
    String hostname;

    @Operation(summary = "Only a hack, please ignore")
    @PostMapping(path = "/hack")
    void createShoppingItemByName(@PathVariable String name) {

        System.out.println("Hack value: "+name);

    }

    /**
     * 
     *
     * @param item
     * @return
     */

    @Operation(summary = "Create a new shopping item")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Item created", content = @Content) })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = "application/json", produces = "application/json")
    MatureItem createShoppingItem(@RequestBody MatureItem item) {

        logger.info("Received POST on instance " + hostname + " Item: " + item);
        // TODO create the item
        return item;

    }

    @Operation(summary = "Find a shopping item by its itemId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the item", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = MatureItem.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid itemId supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Item not found", content = @Content) })
    @GetMapping(produces = "application/json", path = "/{itemId}")
    Optional<MatureItem> getShoppingItem(@PathVariable long itemId) {

        logger.info("Received GET on instance "+hostname);
        return null;

    }

    @Operation(summary = "Returns a list of shopping items")
    @GetMapping(produces = "application/json")
    List<MatureItem> getShoppingItems(@RequestParam(required = false) String itemName) {

        logger.info("Received GET on instance "+hostname);
        List<MatureItem> shoppingItems = null;

        if (itemName != null) {

            //shoppingItems = shoppingItemRepository.findByName(itemName);
        } else {

            //shoppingItems = shoppingItemRepository.findAll();
        }

        return shoppingItems;

    }
}