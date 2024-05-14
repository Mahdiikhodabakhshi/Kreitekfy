package com.kreitek.Kreitekfy.userHistory.infrastructure.rest;


import com.kreitek.Kreitekfy.userHistory.application.dto.UserHistoryDto;
import com.kreitek.Kreitekfy.userHistory.application.service.UserHistoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api")
public class UserHistoryRestController {

    private final UserHistoryService userHistoryService;

    public UserHistoryRestController(UserHistoryService userHistoryService) {
        this.userHistoryService = userHistoryService;
    }

    @GetMapping(value = "/user-history" , produces = "application/json")
    public ResponseEntity<Page<UserHistoryDto>> getUserHistory(@RequestParam(value = "filter") String filter, Pageable pageable) {
        Page<UserHistoryDto> userHistoryDtos  = userHistoryService.getUserHistory(pageable, filter);
        return new ResponseEntity<>(userHistoryDtos,HttpStatus.OK);
    }


    @PostMapping(value = "/user-history" , produces = "application/json" , consumes = "application/json")
    public ResponseEntity<UserHistoryDto> addUserMusic(@RequestBody UserHistoryDto userHistoryDto) {
        userHistoryDto = userHistoryService.saveUserHistory(userHistoryDto);
        return ResponseEntity.ok(userHistoryDto);
    }
}
