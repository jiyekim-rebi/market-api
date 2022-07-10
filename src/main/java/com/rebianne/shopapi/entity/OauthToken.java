package com.rebianne.shopapi.entity;

import lombok.Data;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * [2022.07.10] Oauth token entity
 */
public class OauthToken {

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Data
    public static class response {
        private String access_token;
        private String token_type;
        private String refresh_token;
        private long expires_in;
        private String scope;
    }

    @Data
    public static class request {

        //access_token
        @Data
        public static class accessToken {

            public String code;
            private String grant_type;
            private String redirect_uri;

            public Map<String, String> getAccessTokenData(){
                Map map = new HashMap<String, String>();

                map.put("code", this.code);
                map.put("grant_type", this.grant_type);
                map.put("redirect_uri", this.redirect_uri);

                return map;
            }
        }

        @Data
        public static class refreshToken {
            private String refresh_Token;
            private String grant_type;

            public Map<String, String> getRefreshTokenData(){
                Map map = new HashMap<String, String>();

                map.put("refresh_Token", this.refresh_Token);
                map.put("grant_type", this.grant_type);

                return map;
            }
        }

    }



}
