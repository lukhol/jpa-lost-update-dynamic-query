package com.lukhol.hibernate.lostupdate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WithDynamicUpdateRepository extends JpaRepository<WithDynamicUpdate, Long> {
}
