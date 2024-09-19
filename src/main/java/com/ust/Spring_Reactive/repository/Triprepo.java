package com.ust.Spring_Reactive.repository;

import com.ust.Spring_Reactive.dto.Tripdto;
import com.ust.Spring_Reactive.entity.Trip;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

import java.util.Optional;

public interface Triprepo extends ReactiveMongoRepository<Trip,String> {
    <T> Optional<T> findByStatus(String status);

    Flux<Tripdto> findAllByStatus(String status);



    Flux<Tripdto> findAllByPriceBetween(int min, int max);

    Flux<Tripdto> findAllByRating(int  rating);
}
