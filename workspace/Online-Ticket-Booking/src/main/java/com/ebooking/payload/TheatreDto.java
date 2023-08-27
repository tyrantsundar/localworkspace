package com.ebooking.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TheatreDto {
    private long id;

    private String name;

    private boolean isHoliday;
}