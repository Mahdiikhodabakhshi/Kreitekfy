package com.kreitek.Kreitekfy.userHistory.domain.persistence;

import com.kreitek.Kreitekfy.userHistory.domain.entity.UserHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserHistoryPersistence {
    UserHistory saveUserHistory(UserHistory userHistory);
    Page<UserHistory> findAll(Pageable pageable, String filters);
}
