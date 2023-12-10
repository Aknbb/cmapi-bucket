package com.aknbb.cmapibucket.rest;

import com.aknbb.cmapibucket.initializer.DataInitializer;
import com.aknbb.cmapibucket.pojo.Cmapi;
import com.aknbb.cmapibucket.pojo.CmapiBucket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/messages")
public class MessageController {

    private static final Logger LOGGER = LogManager.getLogger(MessageController.class);

    /**
     * @api {get} /messages Get all cmapi messages as json
     * @apiVersion 0.0.1
     * @apiName GetAllMessages
     * @apiGroup Cmapi
     * @apiExample Example usage:
     * curl -i http://localhost/messages
     * @apiSuccess {Object}   messages       Messages data contains channels and cmapi payloads
     */
    @GetMapping()
    public CmapiBucket getAllMessages() {
        return DataInitializer.cmapiBucket;
    }

    /**
     * @api {get} /messages/:channel  Get all cmapi messages of given channel as json
     * @apiVersion 0.0.1
     * @apiName GetChannelMessages
     * @apiGroup Cmapi
     * @apiPermission admin
     * @apiExample Example usage:
     * curl -i http://localhost/messages/map.feature.plot
     * @apiSuccess {Object}   messages       Contains cmapi payloads of given channel
     */
    @GetMapping("/{channel}")
    public HashMap<String, CmapiBucket.Payload> getChannelMessages(@PathVariable String channel) {
        return DataInitializer.cmapiBucket.getMessages().get(channel);
    }

    /**
     * @api {delete} /messages/:channel/:title  Deletes given channel:title cmapi message
     * @apiVersion 0.0.1
     * @apiName DeleteMessage
     * @apiGroup Cmapi
     * @apiPermission admin
     * @apiExample Example usage:
     * curl -i http://localhost/messages/map.feature.plot/sample-message
     */
    @DeleteMapping("/{channel}/{title}")
    public void deleteMessage(@PathVariable String channel, @PathVariable String title) {
        CmapiBucket.Payload removedMessage = DataInitializer.cmapiBucket.removeMessage(channel, title);
        if (removedMessage == null) {
            LOGGER.info(channel + " " + title + " message not exist to delete.");
        } else {
            boolean deleteSuccess = DataInitializer.removeMessage(channel, title);
            if (deleteSuccess) {
                LOGGER.info("Message removed: " + channel + ": " + title);
                if (DataInitializer.cmapiBucket.getMessages().get(channel).size() == 0) {
                    LOGGER.info(channel + " channel is empty. Removing...");
                    DataInitializer.cmapiBucket.getMessages().remove(channel);
                    DataInitializer.removeDirectory(channel);
                }
            } else {
                LOGGER.error("Error occured while deleting " + channel + ": " + title);
            }
        }
    }

    /**
     * @api {post} /messages/addMessage  Adds cmapi message
     * @apiVersion 0.0.1
     * @apiName AddMessage
     * @apiGroup Cmapi
     * @apiPermission admin
     * @apiParam {Object} cmapi message
     */
    @PostMapping(path = "/addMessage")
    public void addMessage(@RequestBody Cmapi message) {
        DataInitializer.cmapiBucket.addMessage(message);
        DataInitializer.addMessage(message);
    }

}
