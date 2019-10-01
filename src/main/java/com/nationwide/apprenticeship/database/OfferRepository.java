package com.nationwide.apprenticeship.database;
import com.nationwide.apprenticeship.entity.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfferRepository extends JpaRepository<Offer, Long>{
}