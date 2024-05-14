package com.kreitek.Kreitekfy.userHistory.application.service.Impl;

import com.kreitek.Kreitekfy.userHistory.application.dto.UserHistoryDto;
import com.kreitek.Kreitekfy.userHistory.application.mapper.UserHistoryMapper;
import com.kreitek.Kreitekfy.userHistory.application.service.UserHistoryService;
import com.kreitek.Kreitekfy.userHistory.domain.entity.UserHistory;
import com.kreitek.Kreitekfy.userHistory.domain.persistence.UserHistoryPersistence;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserHistoryServiceImpl implements UserHistoryService {
    private final UserHistoryPersistence userHistoryPersistence;
    private final UserHistoryMapper userHistoryMapper;

    public UserHistoryServiceImpl(UserHistoryPersistence userHistoryPersistence, UserHistoryMapper userHistoryMapper) {
        this.userHistoryPersistence = userHistoryPersistence;
        this.userHistoryMapper = userHistoryMapper;
    }


    @Override
    public Page<UserHistoryDto> getUserHistory(Pageable pageable, String filter) {
        Sort sort = Sort.by(Sort.Direction.DESC, "historyDate");
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
        Page<UserHistory> userHistories = userHistoryPersistence.findAll(pageable, filter);
        return userHistories.map(userHistoryMapper::toDto);
    }

    @Override
    public UserHistoryDto saveUserHistory(UserHistoryDto userHistoryDto) {
        userHistoryDto.setHistoryDate(new Date());
        UserHistory userHistory = userHistoryMapper.toEntity(userHistoryDto);
        userHistory = userHistoryPersistence.saveUserHistory(userHistory);
        return userHistoryMapper.toDto(userHistory);
    }
}
