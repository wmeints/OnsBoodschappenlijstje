package boodschappenlijst.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersionController {
    @RequestMapping(value = "/api/version", method = RequestMethod.GET)
    public String getVersion() {
        return "1.0";
    }
}
