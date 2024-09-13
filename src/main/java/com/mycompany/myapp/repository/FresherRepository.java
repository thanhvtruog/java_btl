package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Fresher;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Fresher entity.
 */
@Repository
public interface FresherRepository extends JpaRepository<Fresher, Long> {
    default Optional<Fresher> findOneWithEagerRelationships(Long id) {
        return this.findOneWithToOneRelationships(id);
    }

    default List<Fresher> findAllWithEagerRelationships() {
        return this.findAllWithToOneRelationships();
    }

    default Page<Fresher> findAllWithEagerRelationships(Pageable pageable) {
        return this.findAllWithToOneRelationships(pageable);
    }

    @Query(
        value = "select distinct fresher from Fresher fresher left join fetch fresher.center",
        countQuery = "select count(distinct fresher) from Fresher fresher"
    )
    Page<Fresher> findAllWithToOneRelationships(Pageable pageable);

    @Query("select distinct fresher from Fresher fresher left join fetch fresher.center")
    List<Fresher> findAllWithToOneRelationships();

    @Query("select fresher from Fresher fresher left join fetch fresher.center where fresher.id =:id")
    Optional<Fresher> findOneWithToOneRelationships(@Param("id") Long id);
}
