package com.ebooking.payload;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeatDto {
    private long id;
    private int rowNo;
    private int colNo;
    private boolean isAvailable;
}
