package com.ow.tracer.mantuoluo.member.dsm.service.impl;

import com.ow.tracer.common.base.BaseServiceImpl;
import com.ow.tracer.mantuoluo.member.dsm.dto.Tutor;
import com.ow.tracer.mantuoluo.member.dsm.mapper.TutorMapper;
import com.ow.tracer.mantuoluo.member.dsm.service.ITutorService;
import org.springframework.stereotype.Service;

/**
 * 类描述:     [业务接口实现类]
 * 创建人:     [becky]
 * 创建时间:   [2019-05-11 15:36:36]
 * 版本:       [v1.0]
 */
@Service
public class TutorServiceImpl extends BaseServiceImpl<TutorMapper, Tutor> implements ITutorService {
    public  Tutor selectByGps(Double lat,Double lng){
       return this.baseMapper.selectByGps(lat,lng);
    }


}
