package com.srtp.taxi.mapper;

import com.srtp.taxi.entity.Dispatch;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface DispatchMapper {
    @Insert("insert into t_dispatch(driverId,reservationId) values(#{driverId},#{reservationId})")
    long saveDispatch(long driverId,long reservationId);

    @Delete("delete from t_dispatch where reservationId=#{reservationId}")
    boolean deleteDispatchByReservationId(long reservationId);

    Dispatch findDispatchByDriverId(long driverId);

    @Select("select driverId from t_dispatch where reservationId=#{reservationId}")
    long findDriverIdByReservationId(long reservationId);
}
