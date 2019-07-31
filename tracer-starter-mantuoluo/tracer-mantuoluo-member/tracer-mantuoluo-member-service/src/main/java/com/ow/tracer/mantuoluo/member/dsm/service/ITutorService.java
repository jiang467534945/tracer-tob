package com.ow.tracer.mantuoluo.member.dsm.service;

import com.ow.tracer.common.base.BaseService;
import com.ow.tracer.mantuoluo.member.dsm.dto.Tutor;

/**
 * 类描述:     [业务接口]
 * 创建人:     [becky]
 * 创建时间:   [2019-05-11 15:36:36]
 * 版本:       [v1.0]
 */
public interface ITutorService extends BaseService<Tutor> {
      Tutor selectByGps(Double lat,Double lng);



}
