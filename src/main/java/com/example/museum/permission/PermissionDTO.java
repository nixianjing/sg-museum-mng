package com.example.museum.permission;

import lombok.Data;

import java.util.List;

/**
 * 一级菜单
 *
 * @author xianjing.n
 * @date 2019-10-24 20:10
 **/
@Data
public class PermissionDTO {

    /**
     * 编号
     */
    private Integer id;

    /**
     * 标题
     */
    private String title;

    /**
     * 跳转链接
     */
    private String href;

    /**
     * 节点是否初始展开 true展开 false不展开
     */
    private Boolean spread;

    /**
     * 是否选中 true选择 false未选中
     */
    private Boolean checked;

    /**
     * 禁止使用 true禁止使用 false可以使用
     */
    private Boolean disabled;

    /**
     * 子菜单集合
     */
    private List<PermissionDTO> children;
}
