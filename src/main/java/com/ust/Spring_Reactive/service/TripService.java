package com.ust.Spring_Reactive.service;

import com.ust.Spring_Reactive.dto.Tripdto;
import com.ust.Spring_Reactive.repository.Triprepo;
import com.ust.Spring_Reactive.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
public class TripService {
    @Autowired
    private Triprepo repo;
    public Mono<Tripdto> addTrip(Mono <Tripdto> tripdtomono) {
        return tripdtomono.map(AppUtils::dtoToEntity)
                .flatMap(repo::insert).map(AppUtils::entityToDto);

    }

    public Flux<Tripdto> getAllTrips() {
        return repo.findAll().map(AppUtils::entityToDto);
    }

    public Mono<Tripdto> getTripById(String id) {
        return repo.findById(id).map(AppUtils::entityToDto);
    }

    public Mono<Tripdto> updateTrip(String id, Mono<Tripdto> trip) {
        return repo.findById(id).flatMap(p->trip.map(AppUtils::dtoToEntity))
                .doOnNext(e->e.setId(id))
                .flatMap(repo::save)
                .map(AppUtils::entityToDto);
    }

    public Mono<Void> deleteTrip(String id) {
        return repo.deleteById(id);
        // or return repo.findById(id).flatMap(trip -> repo.delete(trip));
    }


    public Flux<Tripdto> getTripByStatus(String status) {
        return repo.findAllByStatus(status);
    }

    public Flux<Tripdto> getTripByBudget(int min,int max) {
        return repo.findAllByPriceBetween(min,max);
    }

    public Flux<Tripdto> getTripByRating(int rating) {
        return repo.findAllByRating(rating);
    }
}
