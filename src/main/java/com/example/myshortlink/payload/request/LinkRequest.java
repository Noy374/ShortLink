package com.example.myshortlink.payload.request;

import lombok.Data;

@Data
public class LinkRequest {

    String username;
    String link;
    String accessToken;
    String shortLinkName;
}
