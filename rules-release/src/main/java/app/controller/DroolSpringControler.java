package app.controller;

import model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import app.service.RuleSimpleService;

@RestController
@RequestMapping("/api")
public class DroolSpringControler {

    @Autowired
    private RuleSimpleService ruleSimpleService;

    @PostMapping
    public ResponseEntity execute(@RequestBody Customer c){
        ruleSimpleService.advise(c);
        return new ResponseEntity(c, HttpStatus.OK);
    }
}
