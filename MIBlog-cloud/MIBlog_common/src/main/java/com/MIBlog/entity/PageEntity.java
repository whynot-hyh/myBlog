package com.MIBlog.entity;

import lombok.Data;

@Data
public class PageEntity {

    private String userId;

    private String nickname;

    private String search;

    private String receiver;

    private Integer typeId;

    private Integer index;

    private Integer size;
}
