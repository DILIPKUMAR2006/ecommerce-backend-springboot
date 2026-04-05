package com.manikandan.shopping.spec;

import com.manikandan.shopping.entity.Product;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecification
{
    public static Specification<Product> hasCategory(String category)
    {
        // Lambda that returns a Predicate (WHERE condition)
         return (root, query, criteriaBuilder) ->
         {
             // If category is null → do NOT add this condition to query
             if(category == null)
                 return null;
             else
                 //Creates a SQL predicate(condition) that maps the category field of the Product entity to a WHERE category = ? condition using Criteria API, with root.get() resolving the column path and cb.equal() generating the equality comparison with parameter binding.
                 return criteriaBuilder.equal(root.get("category"),category);
         };
    }

    public static Specification<Product> priceBetween(Double min, Double max)
    {
         return (root, query, criteriaBuilder) ->
         {
             if(min == null && max == null) return null;
             if(min == null) return criteriaBuilder.lessThanOrEqualTo(root.get("price"), max);
             if(max == null) return criteriaBuilder.greaterThanOrEqualTo(root.get("price"), min);
             return criteriaBuilder.between(root.get("price"), min, max);
         };
    }

    public static Specification<Product> hasNameOrDescription(String keyword)
    {
        return (root, query, criteriaBuilder) ->
        {
            if(keyword == null || keyword.isEmpty())    return null;
            return criteriaBuilder.or(
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("name")),"%"+keyword.toLowerCase()+"%"),
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("description")),"%"+keyword.toLowerCase()+"%")
            );
        };
    }

    public static Specification<Product> ratingGreaterThan(Double ratings)
    {
        return (root, query, criteriaBuilder) ->
        {
            if (ratings == null)    return null;
            return criteriaBuilder.greaterThanOrEqualTo(root.get("ratings"),ratings);
        };
    }
}
