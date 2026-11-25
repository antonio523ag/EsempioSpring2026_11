package dev.antoniogrillo.primo.actuator;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Endpoint(id = "custom")
public class CustomEndpointActuator {

    @ReadOperation
    public Map<String,String> getTesto(){
        Map<String,String> map = new HashMap<>();
        map.put("testo1","valore1");
        map.put("testo2","valore2");
        return map;
    }

    @ReadOperation
    public Map<String,String> getTesto2(@Selector String selector){
        Map<String,String> map = new HashMap<>();
        map.put("testo2",selector);
        map.put("testo3",selector);
        return map;
    }
}
