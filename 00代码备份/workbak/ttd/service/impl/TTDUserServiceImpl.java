package com.carelinker.ttd.service.impl;

import com.carelinker.core.pojo.carelinker.TTDUser;
import com.carelinker.ttd.dao.ITTDUserDao;
import com.carelinker.ttd.service.ITTDUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author liyue
 * @date 2019-08-21 16:22
 */
@Service
public class TTDUserServiceImpl implements ITTDUserService {
    @Resource
    ITTDUserDao ttdUserDao;

    @Override
    public TTDUser addTTDUser(String patientId, String idCard, String policyNumber, String phone) {
        TTDUser ttdUser = new TTDUser(patientId, idCard, policyNumber, phone, 1);
        return ttdUserDao.merge(ttdUser);
    }
}
