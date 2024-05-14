package com.kreitek.Kreitekfy.userHistory.infrastructure.repository.Impl;

import com.kreitek.Kreitekfy.shared.specs.SearchCriteriaHelper;
import com.kreitek.Kreitekfy.userHistory.domain.entity.UserHistory;
import com.kreitek.Kreitekfy.userHistory.domain.persistence.UserHistoryPersistence;
import com.kreitek.Kreitekfy.userHistory.infrastructure.repository.UserHistoryPersistenceJpa;
import com.kreitek.Kreitekfy.userHistory.infrastructure.specs.UserHistorySpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class UserHistoryPersistenceImpl implements UserHistoryPersistence {
    private final UserHistoryPersistenceJpa userHistoryPersistenceJpa;

    public UserHistoryPersistenceImpl(UserHistoryPersistenceJpa userHistoryPersistenceJpa) {
        this.userHistoryPersistenceJpa = userHistoryPersistenceJpa;
    }

    @Override
    public UserHistory saveUserHistory(UserHistory userHistory) {
        return userHistoryPersistenceJpa.save(userHistory);
    }

    @Override
    public Page<UserHistory> findAll(Pageable pageable, String filters) {
        UserHistorySpecification specification = new UserHistorySpecification(SearchCriteriaHelper.fromFilterString(filters));
        return userHistoryPersistenceJpa.findAll(specification , pageable);
    }


}
