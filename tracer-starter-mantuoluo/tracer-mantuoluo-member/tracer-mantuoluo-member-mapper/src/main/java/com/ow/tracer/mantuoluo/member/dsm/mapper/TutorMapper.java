package com.ow.tracer.mantuoluo.member.dsm.mapper;

import com.ow.tracer.common.base.Mapper;
import com.ow.tracer.mantuoluo.member.dsm.dto.Tutor;

public interface TutorMapper extends Mapper<Tutor> {
    public  Tutor selectByGps(Double lat,Double lng);
}
