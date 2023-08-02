package me.timur.touroperatorbpa.domain.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import me.timur.touroperatorbpa.domain.entity.ApplicationAccommodation;
import me.timur.touroperatorbpa.domain.entity.Group;
import me.timur.touroperatorbpa.domain.repository.FilteredFetchRepository;
import me.timur.touroperatorbpa.application.model.accommodation.AccommodationApplicationFilter;
import me.timur.touroperatorbpa.group.model.GroupFilter;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Temurbek Ismoilov on 29/07/23.
 */

@Repository
public class ApplicationAccommodationFilteredFetchRepository implements FilteredFetchRepository<ApplicationAccommodation, AccommodationApplicationFilter> {

    @PersistenceContext
    public EntityManager em;

    @Override
    public Pair<List<ApplicationAccommodation>, Long> findAllFiltered(AccommodationApplicationFilter filter) {
        return null;
    }

    private Predicate[] buildPredicate(GroupFilter filter, CriteriaBuilder cb, Root<Group> root) {
       return null;
    }
}
