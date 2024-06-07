package tn.esprit.spring.repository;

import tn.esprit.spring.entities.Entreprise;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository

public interface EntrepriseRepository extends JpaRepository<Entreprise, Long> {

}