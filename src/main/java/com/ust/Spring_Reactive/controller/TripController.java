package com.ust.Spring_Reactive.controller;

import com.ust.Spring_Reactive.dto.Tripdto;
import com.ust.Spring_Reactive.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/trip")

public class TripController {
    @Autowired
    private TripService tripService;

    @PostMapping("/addtrip")
    public Mono<Tripdto> addTrip(@RequestBody Mono<Tripdto> tripdto){
        return tripService.addTrip(tripdto);
    }
    @GetMapping("/gettrips")
    public Flux<Tripdto> getAllTrips(){
        return tripService.getAllTrips();
    }

    @GetMapping("/gettrip/{id}")
    public Mono<Tripdto> getTripById(@PathVariable String id) {
        return tripService.getTripById(id);
    }

    @PutMapping("/updatetrip/{id}")
    public Mono<Tripdto> updateTrip(@PathVariable String id, @RequestBody Mono<Tripdto> updatedTrip) {
        return tripService.updateTrip(id, updatedTrip);
    }
    @DeleteMapping("/deletetrip/{id}")
    public Mono<Void> deleteTrip(@PathVariable String id){
        return tripService.deleteTrip(id);
    }
    @GetMapping("/getstatus/{status}")
    public Flux<Tripdto> getTripByStatus(@PathVariable String status){
        return tripService.getTripByStatus(status);
    }
    @GetMapping("/getrating/{rating}")
    public Flux<Tripdto> getTripByRating(@PathVariable int rating){
        return tripService.getTripByRating(rating);
    }
    @GetMapping("/getbudget/{min}/{max}")
    public Flux<Tripdto>getTripByBudget(@PathVariable int min, @PathVariable int max){
        return tripService.getTripByBudget(min, max);
    }



}
