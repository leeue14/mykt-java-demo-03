package com.carelinker.ttd.controller;

import com.carelinker.config.ResourcePath;
import com.carelinker.core.pojo.carelinker.TTDUser;
import com.carelinker.core.resource.DataPublicResponseParams;
import com.carelinker.core.resource.PRPFactory;
import com.carelinker.ttd.service.ITTDUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author liyue
 * @date 2019-08-21 15:58
 */
@Api(tags = "营养方案", description = "营养方案")
@RestController
@RequestMapping(ResourcePath.NES_2)
public class TTDUserResource {

    @Resource
    ITTDUserService ttdUserService;

    @ApiOperation(value = "TTD用户登录时候创建")
    @RequestMapping(path = "/addTTDUser", method = RequestMethod.POST)
    public DataPublicResponseParams<TTDUser> addTTDUser(@ApiParam(value = "患者id", defaultValue = "7937")
                                                        @RequestParam(value = "patientId", required = false) String patientId,
                                                        @ApiParam(value = "身份证号码", defaultValue = "3214587995142361112")
                                                        @RequestParam(value = "idCard", required = false) String idCard,
                                                        @ApiParam(value = "保单号", defaultValue = "3214587995142361112")
                                                        @RequestParam(value = "policyNumber", required = false) String policyNumber,
                                                        @ApiParam(value = "手机号码", defaultValue = "18201544154")
                                                        @RequestParam(value = "phone", required = false) String phone) {
        return PRPFactory.getPrpData(ttdUserService.addTTDUser(patientId, idCard, policyNumber, phone));
    }
}
