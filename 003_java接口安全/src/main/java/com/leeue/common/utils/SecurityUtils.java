// package com.leeue.common.utils;
//
//
// import io.netty.util.CharsetUtil;
// import org.apache.commons.lang3.time.DateUtils;
//
// import java.io.UnsupportedEncodingException;
// import java.net.URLEncoder;
// import java.time.LocalDateTime;
// import java.time.format.DateTimeFormatter;
// import java.util.HashMap;
// import java.util.Map;
// import java.util.TreeMap;
//
// /**
//  * @author liyue
//  * @date 2019/9/19 11:34
//  */
// public class SecurityUtils {
//
//     private static final String FIELD_SIGN = "sign";
//
//     public static String md5(String data) {
//         return SecureUtil.md5(data);
//     }
//
//     public static String urlEncode(String src) throws UnsupportedEncodingException {
//         return URLEncoder.encode(src, CharsetUtil.UTF_8).replace("+", "%20");
//     }
//
//     public static String generateStr() {
//         return IdUtil.fastSimpleUUID();
//     }
//
//     /**
//      * 组装签名的字段
//      *
//      * @param params     参数
//      * @param urlEncoder 是否urlEncoder
//      * @return {String}
//      */
//     public static String packageSign(Map<String, String> params, boolean urlEncoder) {
//         // 先将参数以其参数名的字典序升序进行排序
//         TreeMap<String, String> sortedParams = new TreeMap<String, String>(params);
//         // 遍历排序后的字典，将所有参数按"key=value"格式拼接在一起
//         StringBuilder sb = new StringBuilder();
//         boolean first = true;
//         for (Map.Entry<String, String> param : sortedParams.entrySet()) {
//             String value = param.getValue();
//             if (StrUtil.isEmpty(value)) {
//                 continue;
//             }
//             if (first) {
//                 first = false;
//             } else {
//                 sb.append("&");
//             }
//             sb.append(param.getKey()).append("=");
//             if (urlEncoder) {
//                 try {
//                     value = urlEncode(value);
//                 } catch (UnsupportedEncodingException e) {
//                 }
//             }
//             sb.append(value);
//         }
//         return sb.toString();
//     }
//
//     /**
//      * 生成签名
//      *
//      * @param params     需要签名的参数
//      * @param partnerKey 密钥
//      * @return 签名后的数据
//      */
//     public static String createSign(Map<String, String> params, String partnerKey) {
//
//         // 生成签名前先去除sign
//         params.remove(FIELD_SIGN);
//         String tempStr = packageSign(params, false);
//         String stringSignTemp = tempStr + "&key=" + partnerKey;
//         return md5(stringSignTemp).toUpperCase();
//     }
//
//     /**
//      * 校验签名
//      *
//      * @param params
//      * @param partnerKey
//      * @param sign
//      * @return
//      */
//     public static Boolean checkSign(Map<String, String> params, String partnerKey, String sign) {
//
//         return createSign(params, partnerKey).equals(sign);
//     }
//
//     /**
//      * 效验时间戳
//      *
//      * @param timestamp
//      * @return
//      */
//     public static boolean checkTimestamp(String timestamp) {
//
//         int interval = Math.abs(DateUtils.getBetweenDays(DateUtils.nowLocalDateTime(), LocalDateTime.parse(timestamp, DateTimeFormatter.ofPattern("yyyyMMddHHmmss"))));
//
//         return interval <= 120;
//     }
//
//
//     public static void main(String[] args) {
//      /*   date=20190918121212&father={"userId":9,"realName":""}&friends=[{"userId":1,"name":"tom"},{"userId":2,"name":"jack"}]&gender=-1&hobbies=["篮球","足球"]&isVip=true&name=test&weight=60.5&
//                 */
//
//         String key="192006250b4c09247ec02edce69f6a2d";
//         String sign = "AEC267BAB88AF495AF0F616EAA93FB57";
//
//         Map map = new HashMap();
//         map.put("date","20190918121212");
//         map.put("father","{\"userId\":9,\"realName\":\"\"}");
//         map.put("friends","[{\"userId\":1,\"name\":\"tom\"},{\"userId\":2,\"name\":\"jack\"}]");
//
//         map.put("gender","-1");
//
//         map.put("hobbies","[\"篮球\",\"足球\"]");
//         map.put("isVip","true");
//         map.put("name","test");
//         map.put("weight","60.5");
//         System.out.println(checkSign(map,key,sign));
//
//         System.out.println(createSign(map,key));
//     }
// }
