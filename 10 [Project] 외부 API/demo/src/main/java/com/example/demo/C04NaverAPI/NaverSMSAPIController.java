package com.example.demo.C04NaverAPI;




import com.example.demo.C04NaverAPI.sms.SMSDto;
import com.example.demo.C04NaverAPI.sms.SMSResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/naver/sms")
public class NaverSMSAPIController {

    private String SERVICE_KEY = "SERVICE_KEY";
    private String ACCESS_KEY = "ACCESS_KEY";;
    private String ACCESS_SECRET = "ACCESS_SECRET";


    @GetMapping("/index")
    public void index() {
        log.info("GET /naver/sms/index");
    }


    @GetMapping("/request")
    public @ResponseBody Object requestFunc(SMSDto dto) throws Exception {


        Long time = System.currentTimeMillis();

        //URL
        String url = "https://sens.apigw.ntruss.com/sms/v2/services/"+SERVICE_KEY+"/messages";

        //Header
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("x-ncp-apigw-timestamp", time.toString());
        headers.add("x-ncp-iam-access-key", ACCESS_KEY);
        headers.add("x-ncp-apigw-signature-v2", makeSignature(time));

        //Parameter
        JSONObject params = new JSONObject();

        JSONArray array = new JSONArray();  // [ ]
        JSONObject param_sub = new JSONObject();    // {}
        param_sub.put("to",dto.getTo());
        array.add(param_sub);       // [{"to","01023753058"}]

        params.put("type","SMS");
        params.put("contentType","COMM");
        params.put("countryCode","82");
        params.put("from","01023753058");
        //params.put("subject","--[WEB 발신]--");
        params.put("content",dto.getContent());
        params.put("messages",array);

        //header + parameter
        HttpEntity<String> entity = new HttpEntity<>(params.toString(),headers);
        System.out.println("entity : " + entity);

        //Request_Case1
//        RestTemplate rt = new RestTemplate();
//        ResponseEntity<String> response =  rt.exchange(url, HttpMethod.POST,entity,String.class);
//
//        System.out.println(response);
//        System.out.println(response.getBody());
//
//        return response.getBody();


        //Request_Case2
        RestTemplate rt = new RestTemplate();
        SMSResponse response =  rt.postForObject(url,entity, SMSResponse.class);
        System.out.println(response);
        return response;
    }



    //----------------------------------------------------------------
    public String makeSignature(Long time) throws Exception {

        String space = " ";					// one space
        String newLine = "\n";					// new line
        String method = "POST";					// method
        String url = "/sms/v2/services/"+SERVICE_KEY+"/messages";	// url (include query string)
        String timestamp = time.toString();			// current timestamp (epoch)
        String accessKey = ACCESS_KEY;			// access key id (from portal or Sub Account)
        String secretKey = ACCESS_SECRET;

        String message = new StringBuilder()
                .append(method)
                .append(space)
                .append(url)
                .append(newLine)
                .append(timestamp)
                .append(newLine)
                .append(accessKey)
                .toString();

        SecretKeySpec signingKey = new SecretKeySpec(secretKey.getBytes("UTF-8"), "HmacSHA256");
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(signingKey);

        byte[] rawHmac = mac.doFinal(message.getBytes("UTF-8"));
        String encodeBase64String = Base64.encodeBase64String(rawHmac);

        return encodeBase64String;
    }




}



// 전자서명 생성 함수



