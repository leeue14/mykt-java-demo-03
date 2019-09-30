package com.carelinker.ttd.service;

import com.carelinker.core.pojo.carelinker.TTDUser;

/**
 * @author liyue
 * @date 2019-08-21 16:22
 */
public interface ITTDUserService {


    /**
     * 用户登录时候创建
     * @param patientId
     * @param idCard
     * @param policyNumber
     * @param phone
     * @return
     */
    TTDUser addTTDUser(String patientId, String idCard, String policyNumber, String phone);
}
