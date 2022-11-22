package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {

    private static final String message = "%s, Pica un diente de ajo y ponlo en un bol con la carne, el huevo, la miga de pan remojada en la leche, una cucharadita de perejil picado y un pellizco de sal. Mezcla hasta obtener una masa homogénea. Forma las albóndigas pasándolas por harina y resérvalas.\n" +
            "\n" +
            "En una sartén con aceite de oliva abundante, fríe las albóndigas hasta que se dore su exterior. Saca y reserva mientras elaboramos la salsa. Pica dos dientes de ajo, la zanahoria y las dos cebollas, pochándolos en una sartén a fuego lento. Añade una cucharada de harina y el vino y reduce mientras vas removiendo. Agrega el caldo de pollo o de carne y cocina durante 15 a 20 minutos.\n" +
            "\n" +
            "Tritura la salsa con la batidora y ponla en una cacerola. Mete también las albóndigas y deja que el conjunto cueza durante diez minutos, apaga el fuego y tapa, dejando reposar unos minutos antes de comer.";

    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value= "name", defaultValue = "Albóndigas") String name){
        return new Greeting(counter.incrementAndGet(), String.format(message, name));
    }
}
