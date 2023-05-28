package com.geekerstar.tdengine.module.tdengine.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author geekerstar
 * @date 2023/5/28 09:27
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "Meters", description = "")
public class Meters {

    private Date ts;

    private Float current;

    private Integer voltage;

    private Float phase;
}
