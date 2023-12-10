package com.aknbb.cmapibucket.rest;

import com.aknbb.cmapibucket.pojo.SystemInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @api {get} /systeminfo  Returns application, monitoring and build infos.
 * @apiVersion 0.0.1
 * @apiName GetSystemInfo
 * @apiGroup Cmapi
 * @apiPermission admin
 * @apiExample Example usage:
 * curl -i http://localhost/systeminfo
 * @apiSuccess {Object}   systeminfo       Contains application, monitoring and build infos.
 */
@RestController
@RequestMapping("/systeminfo")
public class SystemInfoController {
    @Value("${build.version}")
    String version;

    @Value("${build.timestamp}")
    String timestamp;

    @Value("${logging.level.root}")
    String debugLevel;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public SystemInfo getSystemInfo() {
        return new SystemInfo(version, timestamp, debugLevel);
    }
}
