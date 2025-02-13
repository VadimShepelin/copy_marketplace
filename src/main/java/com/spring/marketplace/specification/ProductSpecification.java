package com.spring.marketplace.specification;

import com.spring.marketplace.model.Product;
import org.springframework.data.jpa.domain.Specification;
import java.math.BigDecimal;
import java.math.BigInteger;

public class ProductSpecification {

    public static Specification<Product> byName(String name){
        return ((root, query, cb) -> name == null?cb.conjunction():cb.like(root.get("name"), "%"+name+"%"));
    }

    public static Specification<Product> byQuantity(BigInteger quantity){
        return ((root, query, cb) -> quantity == null?cb.conjunction():cb.greaterThan(root.get("quantity"), quantity));
    }

    public static Specification<Product> byPrice(BigDecimal price){
        return ((root, query, cb) -> price == null?cb.conjunction():cb.lessThan(root.get("price"), price));
    }

    public static Specification<Product> byAvailability(Boolean isAvailable){
        return ((root, query, cb) -> isAvailable == null?cb.conjunction():cb.equal(root.get("isAvailable"), isAvailable));
    }
}
