package com.srtp.taxi.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Dispatch {
    private long driverId;
    private List<ReservationDispatched> reservationList;
}
