package com.kreitek.Kreitekfy.userHistory.application.service;

import com.kreitek.Kreitekfy.userHistory.application.dto.UserHistoryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserHistoryService {
    Page<UserHistoryDto> getUserHistory(Pageable pageable , String filter);
    UserHistoryDto saveUserHistory(UserHistoryDto userHistoryDto);
}
