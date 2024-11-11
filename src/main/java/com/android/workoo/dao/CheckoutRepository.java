package com.android.workoo.dao;

import com.android.workoo.entity.Checkout;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckoutRepository extends JpaRepository<Checkout,Long> {
}
