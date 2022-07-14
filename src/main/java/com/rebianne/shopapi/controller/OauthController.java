package com.rebianne.shopapi.controller;

import com.rebianne.shopapi.entity.OauthToken;
import kong.unirest.Unirest;
import org.apache.commons.codec.binary.Base64;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OauthController {

    @RequestMapping("/callback")
    public OauthToken.response code(@RequestParam String code){

        String cridentials = "clientId:secretKey";
        // base 64로 인코딩 (basic auth 의 경우 base64로 인코딩 하여 보내야한다.)
        String encodingCredentials = new String(
                Base64.encodeBase64(cridentials.getBytes())
        );
        String requestCode = code;
        OauthToken.request.accessToken request = new OauthToken.request.accessToken(){{
            setCode(requestCode);
            setGrant_type("authorization_code");
            setRedirect_uri("http://localhost:9090/callback");
        }};
        // oauth 서버에 http 통신으로 토큰 발행
        OauthToken.response oauthToken = Unirest.post("http://localhost:9090/oauth/token")
                .header("Authorization","Basic "+encodingCredentials)
                .fields(request.getAccessTokenData())
                .asObject(OauthToken.response.class).getBody();
        return oauthToken;
    }

}