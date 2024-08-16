package org.panda.systems.kakeipon.domain.repository.common;

import org.panda.systems.kakeipon.domain.model.common.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {

  @SuppressWarnings("NullableProblems")
  @Override
  List<Shop> findAll();

  @SuppressWarnings("NullableProblems")
  @Override
  Optional<Shop> findById(Long id);

  @SuppressWarnings({"NullableProblems", "unchecked"})
  Shop saveAndFlush(@SuppressWarnings("NullableProblems") Shop entity);
}
