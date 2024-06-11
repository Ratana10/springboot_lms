package com.kongsun.leanring.system.schedule;

import com.kongsun.leanring.system.exception.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/v1/schedules")
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;
    private final ScheduleMapper scheduleMapper;


    @GetMapping
    public ResponseEntity<ApiResponse> getAll() {
        List<Schedule> schedules = scheduleService.getAll();

        return ResponseEntity
                .ok()
                .body(ApiResponse.builder()
                        .data(schedules.stream()
                                .map(scheduleMapper::toScheduleDTO)
                                .toList())
                        .message("get schedules successfully")
                        .httpStatus(OK.value())
                        .build()
                );

    }

    @PostMapping
    public ResponseEntity<ApiResponse> create(@RequestBody @Valid ScheduleDTO dto) {
        Schedule schedule = scheduleMapper.toSchedule(dto);
        schedule = scheduleService.create(schedule);
        return ResponseEntity
                .status(CREATED)
                .body(ApiResponse.builder()
                        .data(scheduleMapper.toScheduleDTO(schedule))
                        .message("create schedule successfully")
                        .httpStatus(CREATED.value())
                        .build()
                );

    }

    @GetMapping("{id}")
    public ResponseEntity<ApiResponse> getById(@PathVariable Long id) {
        Schedule schedule = scheduleService.getById(id);
        return ResponseEntity
                .status(FOUND)
                .body(ApiResponse.builder()
                        .data(scheduleMapper.toScheduleDTO(schedule))
                        .message("get schedule successfully")
                        .httpStatus(FOUND.value())
                        .build()
                );

    }

    @PutMapping("{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable Long id, @RequestBody @Valid ScheduleDTO dto) {
        Schedule schedule = scheduleMapper.toSchedule(dto);
        schedule = scheduleService.update(id, schedule);
        return ResponseEntity
                .ok()
                .body(ApiResponse.builder()
                        .data(scheduleMapper.toScheduleDTO(schedule))
                        .message("update schedule successfully")
                        .httpStatus(OK.value())
                        .build()
                );

    }

    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse> deleteById(@PathVariable Long id) {
        scheduleService.deleteById(id);
        return ResponseEntity
                .status(FOUND)
                .body(ApiResponse.builder()
                        .data(null)
                        .message("delete schedule successfully")
                        .httpStatus(OK.value())
                        .build()
                );

    }

}
