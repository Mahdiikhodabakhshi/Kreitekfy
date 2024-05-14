package com.kreitek.Kreitekfy.userHistory.infrastructure.repository;

import com.kreitek.Kreitekfy.userHistory.domain.entity.UserHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserHistoryPersistenceJpa extends JpaRepository<UserHistory , Long> , JpaSpecificationExecutor<UserHistory> {
}
