package com.agrieasy.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agrieasy.order.model.CommodityPricing;

/**
 * @author sumilon.mondal
 *
 */
public interface CommodityPricingRepository extends JpaRepository<CommodityPricing, Integer> {

}
