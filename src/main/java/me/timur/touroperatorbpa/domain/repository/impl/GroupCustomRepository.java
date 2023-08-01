package me.timur.touroperatorbpa.domain.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import me.timur.touroperatorbpa.domain.entity.Group;
import me.timur.touroperatorbpa.domain.repository.CustomRepository;
import me.timur.touroperatorbpa.model.BaseFilter;
import me.timur.touroperatorbpa.model.group.GroupFilter;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Temurbek Ismoilov on 29/07/23.
 */

@Repository
public class GroupCustomRepository implements CustomRepository<Group> {

    @PersistenceContext
    public EntityManager em;

    @Override
    public <R extends BaseFilter> Pair<List<Group>, Long> findAllFiltered(R filter) {
        try {
            GroupFilter guideFilterDto = (GroupFilter) filter;
            var cb = em.getCriteriaBuilder();

            // count query
            var countQuery = cb.createQuery(Long.class);
            var countRoot = countQuery.from(Group.class);
            countQuery.select(cb.count(countRoot))
                    .where(buildPredicate(guideFilterDto, cb, countRoot));
            var countResult = em.createQuery(countQuery).getSingleResult();

            // result set query
            var resultSetQuery = cb.createQuery(Group.class);
            var resultSetRoot = resultSetQuery.from(Group.class);
            resultSetQuery.select(resultSetRoot)
                    .where(buildPredicate(guideFilterDto, cb, resultSetRoot));
            var resultSet = em.createQuery(resultSetQuery)
                    .setFirstResult(guideFilterDto.getPageNumber() * guideFilterDto.getPageSize())
                    .setMaxResults(guideFilterDto.getPageSize())
                    .getResultList();

            return Pair.of(resultSet, countResult);
        } finally {
            em.close();
        }
    }

    private Predicate[] buildPredicate(GroupFilter filter, CriteriaBuilder cb, Root<Group> root) {
        var predicates = new ArrayList<Predicate>();

        if (filter.getTourOperatorId() != null) {
            predicates.add(cb.equal(root.get("tourOperator").get("id"), filter.getTourOperatorId()));
        }
        if (filter.getCountry() != null) {
            predicates.add(cb.equal(root.get("country"), filter.getCountry()));
        }
        if (filter.getCompanyId() != null) {
            predicates.add(cb.equal(root.get("company").get("id"), filter.getCompanyId()));
        }
        if (filter.getArrivalFrom() != null) {
            predicates.add(cb.greaterThanOrEqualTo(root.get("arrival"), filter.getArrivalFrom()));
        }
        if (filter.getArrivalTo() != null) {
            predicates.add(cb.lessThanOrEqualTo(root.get("arrival"), filter.getArrivalTo()));
        }
        if (filter.getStatus() != null) {
            predicates.add(cb.equal(root.get("status"), filter.getStatus()));
        }

        return predicates.toArray(new Predicate[0]);
    }
}
