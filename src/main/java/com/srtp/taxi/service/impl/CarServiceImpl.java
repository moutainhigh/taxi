package com.srtp.taxi.service.impl;


import com.srtp.taxi.entity.Car;
import com.srtp.taxi.mapper.CarMapper;
import com.srtp.taxi.mapper.DriverMapper;
import com.srtp.taxi.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by david.w on 2020/4/14.
 */

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    CarMapper carMapper;

    DriverMapper driverMapper;

    @Override
    public Car registerCar(long driverId,Car car) {
        Car saveCar = carMapper.saveCar(car);
        driverMapper.bindCar(driverId,saveCar.getId());
        return saveCar;
    }

    @Override
    public boolean existsPlateNumber(String plateNumber) {
        if(carMapper.queryCarByPlateNumber(plateNumber)==null){
            return false;
        }
        return true;
    }
}
