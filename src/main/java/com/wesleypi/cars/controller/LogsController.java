package com.wesleypi.cars.controller;

import com.wesleypi.cars.domain.helper.HelperConverter;
import com.wesleypi.cars.domain.dto.LogResponse;
import com.wesleypi.cars.domain.dto.LogWithPagination;
import com.wesleypi.cars.service.LogsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Validated
@Tag(name = "Logs API")
public class LogsController {

    @Autowired
    LogsService logsService;
    @Operation(summary = "Lista os logs gerados pela criação de carro")
    @ApiResponses(
            value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = LogResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "Query params inválidos",
                    content = @Content)
            })
    @GetMapping("/logs")
    @ResponseStatus(HttpStatus.OK)
    public LogWithPagination getLogs(@Min(value = 1, message = "O valor de page não pode ser menor que 1") @RequestParam(defaultValue = "1") int page,
                                     @Min(value = 1, message = "O valor de size não pode ser menor que 1") @RequestParam(defaultValue = "5") int size,
                                     @Pattern(regexp = "creationDateHour|processingDateHour|carId",
                                             message = "O valor deve ser uma das seguintes opções: creationDateHour, processingDateHour, carId")
                                         @RequestParam(defaultValue = "creationDateHour") String sortBy) {
        Page<LogResponse> pageLogs = logsService.getLog(page, size, sortBy);

        return LogWithPagination.builder()
                .logs(pageLogs.getContent())
                .paginationModel(HelperConverter.pageToResponse(pageLogs))
                .build();
    }
}
