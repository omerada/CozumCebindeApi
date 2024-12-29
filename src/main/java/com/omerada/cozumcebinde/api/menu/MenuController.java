package com.omerada.cozumcebinde.api.menu;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/menu")
@CrossOrigin
public class MenuController {

    @PostMapping("/getMenu")
    public ResponseEntity getMenu(){
        return new ResponseEntity(HttpStatus.OK);
    }
}
