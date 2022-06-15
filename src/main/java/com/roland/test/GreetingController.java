package com.roland.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private static Map<Integer, Greeting> greetingList = new HashMap<>();

    @Autowired
    private GreetingService service;


    @GetMapping("/greeting")
    public Map<String, Greeting> greetings(@RequestParam(value = "param1", required = false) String param1,
                                            @RequestParam(value = "param2", required = false) String param2) {
        System.out.println(param1 + ", " + param2);
        return service.retrieveGreetings();//greetingList; //new GreetingsDao(greetingList);
    }

    @GetMapping("/greeting/{id}")
    public Greeting greetings(@PathVariable(value = "id") int id) {

        return greetingList.get(id);
    }

    @PostMapping("/greeting")
    public void greetingAdd(@RequestBody Greeting greeting) {
        greetingList.put(greeting.getId(), greeting);
        System.out.println(greetingList.size());
        try {
            service.addGreeting(greeting);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PutMapping("/greeting")
    public void greetingUpdate(@RequestBody Greeting greeting) {
        greetingList.put(greeting.getId(), greeting);
        service.updateGreeting(greeting);
    }

    @DeleteMapping("/greeting/{id}")
    public void greetingDelete(@PathVariable(value = "id") String id) {
        service.deleteGreeting(id);
    }
}
