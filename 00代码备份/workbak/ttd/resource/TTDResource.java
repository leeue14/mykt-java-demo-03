package com.carelinker.data.sync.ttd.resource;

import com.carelinker.config.ResourcePath;
import com.carelinker.core.resource.DataPublicResponseParams;
import com.carelinker.core.resource.PRPFactory;
import com.carelinker.core.resource.PublicResponseParams;
import com.carelinker.data.sync.ttd.model.ArticlesVO;
import com.carelinker.data.sync.ttd.service.ITTDService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author liyue
 * @date 2019-08-21 16:35
 */
@RestController
@RequestMapping(ResourcePath.TTD)
public class TTDResource {

    @Resource
    private ITTDService ttdService;

    @ApiOperation(value = "用户登录时候创建")
    @PostMapping(path = "/addUser")
    public PublicResponseParams addTTDUser(@ApiParam(value = "患者id", defaultValue = "7937")
                                           @RequestParam(value = "userId", required = true) String userId,
                                           @ApiParam(value = "身份证号码", defaultValue = "3214587995142361112")
                                           @RequestParam(value = "idCard", required = true) String idCard,
                                           @ApiParam(value = "保单号", defaultValue = "3214587995142361112")
                                           @RequestParam(value = "policyNumber", required = true) String policyNumber,
                                           @ApiParam(value = "手机号码", defaultValue = "18201544154")
                                           @RequestParam(value = "phone", required = true) String phone) {

        if (!ttdService.addTTDUser(userId, idCard, policyNumber, phone)) {
            return PRPFactory.getError();
        }
        return PRPFactory.getSuccess();

    }


    @ApiOperation(value = "用户首页的文章推荐")
    @PostMapping(path = "/getRecommendArticles")
    public DataPublicResponseParams<List<ArticlesVO>> addTTDUser(@ApiParam(value = "患者id", defaultValue = "7937")
                                                                 @RequestParam(value = "userId", required = true) String userId) {


        return PRPFactory.getPrpData(ttdService.getRecommendArticles(userId));

    }


}
