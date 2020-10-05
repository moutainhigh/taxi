package com.srtp.taxi.restController;

import com.srtp.taxi.entity.Car;
import com.srtp.taxi.entity.Driver;
import com.srtp.taxi.entity.User;
import com.srtp.taxi.service.CarService;
import com.srtp.taxi.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/driver")
public class DriverController {
    final
    DriverService driverService;

    @Autowired
    CarService carService;

    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @PostMapping("/login")
    public Driver login(Driver driver){
        Driver loginDriver = driverService.login(driver);
        if (loginDriver==null){
            System.out.println("登录失败");
            return null;
        }else{
            System.out.println("登录成功");
            return loginDriver;
        }
    }

    @PostMapping("/register")
    public Object register(Driver driver){
        if(driverService.existsUsername(driver.getUsername())){
            return "用户名已存在";
        }else if(driverService.existsPhone(driver.getPhone())){
            return "该号码已被注册过";
        }else{
            System.out.println("保存的司机信息"+driver);
            return driverService.register(driver);
        }
    }

    @PostMapping("/modify")
    public Object modify(Driver driver,String newPassword,String newPhone){
        Driver loginDriver = driverService.login(driver);
        if (loginDriver==null){
            System.out.println("修改失败");
            return "修改失败:用户名或密码错误";
        }else{
            if(newPhone!=null){
                if(driverService.existsPhone(newPhone)){
                    return "该号码已被注册过";
                }
                loginDriver.setPhone(newPhone);
            }
            if(newPassword!=null){
                loginDriver.setPassword(newPassword);
            }
            Driver modifiedDriver= driverService.modify(loginDriver);
            if(modifiedDriver==null){
                return "修改失败";
            }else{
                return modifiedDriver;
            }
        }
    }

    @PostMapping("/{driverId}/registerCar")
    public Object registerCar(@PathVariable long driverId, Car car){
        if(carService.existsPlateNumber(car.getPlateNumber())){
            return "该车牌号已被注册过";
        }
        Car registerCar = carService.register(driverId, car);
        if(registerCar==null){
            return "注册失败";
        }else{
            return registerCar;
        }
    }
}
