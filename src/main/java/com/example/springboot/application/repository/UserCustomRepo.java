package com.example.springboot.application.repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.springboot.application.entitiy.User;

@Component
public class UserCustomRepo {
    @Autowired
    private EntityManager entityManager;

    public void getAllData(int pageNumber, int pageSize) throws ParseException {
        String name = "app";
        String phoneNumber = null;
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        String dateInString = "7-Jun-2013";
        // Date startDate=formatter.parse(dateInString);
        Date startDate=null;
        System.out.println("the date is "+startDate);
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        criteriaQuery.select(root).where(criteriaBuilder.and(getPredicates(root, criteriaBuilder, name, phoneNumber,startDate)));
        final TypedQuery<User> query = entityManager.createQuery(criteriaQuery);
        query.setFirstResult(pageNumber);
        query.setMaxResults(pageSize);
        String listQuery= query.unwrap(org.hibernate.query.Query.class).getQueryString();
        System.out.println("the listQuery is "+listQuery);
        List<User> value = query.getResultList();

        System.out.println("the user count is " + value.size());
        System.out.println("the value is " + value);

        CriteriaBuilder criteriaCountBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> countQuery = criteriaCountBuilder.createQuery(Long.class);
        Root<User> rootCount = countQuery.from(User.class);
        countQuery.select(criteriaCountBuilder.count(rootCount));
        countQuery.where(getPredicates(rootCount, criteriaCountBuilder, name, phoneNumber,startDate));
        String rawQuery = entityManager.createQuery(countQuery).unwrap(org.hibernate.query.Query.class).getQueryString();
        System.out.println("the raw query is "+rawQuery);;
        Long totalCount = entityManager.createQuery(countQuery).getSingleResult();
        System.out.println("the total count is " + totalCount);

    }

    private Predicate[] getPredicates(Root<User> root, CriteriaBuilder criteriaBuilder, String name,
            String phoneNumber,Date startDate) {

        List<Predicate> conditionList = new ArrayList<>();

        if (name != null) {
            Predicate namePredicate = criteriaBuilder.like(root.get("name"), "%" + name + "%");
            conditionList.add(namePredicate);
        }

        if (phoneNumber != null) {
            Predicate phoneNumberPredicate = criteriaBuilder.like(root.get("userPhoneNumber"), "%" + phoneNumber + "%");
            conditionList.add(phoneNumberPredicate);
        }

if(startDate!=null){
    Predicate startDatePredicate = criteriaBuilder.equal(root.get("startDate"), startDate);
    conditionList.add(startDatePredicate);



}

        Predicate[] allCondtionsPredicate = conditionList.toArray(new Predicate[conditionList.size()]);
        return allCondtionsPredicate;
    }

}
