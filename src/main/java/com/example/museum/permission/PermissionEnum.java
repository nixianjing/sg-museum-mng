package com.example.museum.permission;

import com.alibaba.fastjson.JSON;
import com.example.museum.po.PermissionPO;
import lombok.Getter;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 权限枚举
 *
 * @author xianjing.n
 * @date 2019-10-14 19:49
 **/
@Getter
public enum PermissionEnum {


    PERMISSION(PermissionTypeEnum.PERMISSION.getCode(), 0, 1, "管理员权限"),

    /**
     * A权限组
     **/
    SYSTEM_SET_UP_GROUP(PermissionTypeEnum.GROUP.getCode(), 1, 100, "系统设置"),

    /**
     * A1菜单
     **/
    USER_MENU(PermissionTypeEnum.MENU.getCode(), 100, 1001, "用户管理"),

    /**
     * A1-1按钮
     **/
    USER_LIST_BUTTON(PermissionTypeEnum.BUTTON.getCode(), 1001, 1001001, "列表"),
    USER_ADD_BUTTON(PermissionTypeEnum.BUTTON.getCode(), 1001, 1001002, "新增"),
    USER_EDIT_BUTTON(PermissionTypeEnum.BUTTON.getCode(), 1001, 1001003, "编辑"),
    USER_EDIT_STATUS_BUTTON(PermissionTypeEnum.BUTTON.getCode(), 1001, 1001004, "编辑状态"),
    USER_DELETE_BUTTON(PermissionTypeEnum.BUTTON.getCode(), 1001, 1001005, "删除"),
    USER_PASSWORD_BUTTON(PermissionTypeEnum.BUTTON.getCode(), 1001, 1001006, "初始化密码"),

    /**
     * A2菜单
     **/
    ROLE_MENU(PermissionTypeEnum.MENU.getCode(), 100, 1002, "角色管理"),

    /**
     * A2-1按钮
     **/
    ROLE_LIST_BUTTON(PermissionTypeEnum.BUTTON.getCode(), 1002, 1002001, "列表"),
    ROLE_ADD_BUTTON(PermissionTypeEnum.BUTTON.getCode(), 1002, 1002002, "新增"),
    ROLE_EDIT_BUTTON(PermissionTypeEnum.BUTTON.getCode(), 1002, 1002003, "编辑"),
    ROLE_EDIT_STATUS_BUTTON(PermissionTypeEnum.BUTTON.getCode(), 1002, 1002004, "编辑状态"),
    ROLE_DELETE_BUTTON(PermissionTypeEnum.BUTTON.getCode(), 1002, 1002005, "删除"),

    /**
     * A3菜单
     **/
    DICTION_MENU(PermissionTypeEnum.MENU.getCode(), 100, 1003, "字典管理"),

    /**
     * A3-1按钮
     **/
    DICTION_LIST_BUTTON(PermissionTypeEnum.BUTTON.getCode(), 1003, 1003001, "列表"),
    DICTION_ADD_BUTTON(PermissionTypeEnum.BUTTON.getCode(), 1003, 1003002, "新增"),
    DICTION_EDIT_BUTTON(PermissionTypeEnum.BUTTON.getCode(), 1003, 1003003, "编辑"),

    /**
     * A4菜单
     **/
    LOG_MENU(PermissionTypeEnum.MENU.getCode(), 100, 1004, "日志管理"),

    /**
     * A4-1按钮
     **/
    LOG_LIST_BUTTON(PermissionTypeEnum.BUTTON.getCode(), 1004, 1004001, "列表"),
    LOG_DETAILS_BUTTON(PermissionTypeEnum.BUTTON.getCode(), 1004, 1004002, "详情"),

    /**
     * B权限组
     **/
    RELIC_GROUP(PermissionTypeEnum.GROUP.getCode(), 1, 200, "文物信息"),

    /**
     * B1菜单
     **/
    COLLECTION_MENU(PermissionTypeEnum.MENU.getCode(), 200, 2001, "馆藏作品"),

    /**
     * B1-1按钮
     **/
    COLLECTION_BUTTON(PermissionTypeEnum.BUTTON.getCode(), 2001, 2001001, "详情"),


    /**
     * B2菜单
     **/
    RELIC_MENU(PermissionTypeEnum.MENU.getCode(), 200, 2002, "馆藏作品"),

    /**
     * B2-1按钮
     **/
    RELIC_BUTTON(PermissionTypeEnum.BUTTON.getCode(), 2002, 2002001, "详情"),


    ;

    /**
     * 权限类型 {@link PermissionTypeEnum}
     */
    private Integer typeCode;

    /**
     * 父级编号
     */
    private Integer parentCode;

    /**
     * 权限编号
     */
    private Integer code;

    /**
     * 权限名称
     */
    private String title;

    PermissionEnum(Integer typeCode, int parentCode, int code, String title) {
        this.typeCode = typeCode;
        this.parentCode = parentCode;
        this.code = code;
        this.title = title;
    }

    /**
     * 根据typeCode查询
     *
     * @param typeCode
     * @return
     */
    public static List<PermissionEnum> getByTypeCode(Integer typeCode) {
        if (Objects.isNull(typeCode)) {
            return null;
        }
        List<PermissionEnum> list = new ArrayList<>();
        for (PermissionEnum permissionEnum : values()) {
            if (Objects.equals(permissionEnum.getTypeCode(), typeCode)) {
                list.add(permissionEnum);
            }
        }
        return list;
    }

    /**
     * 根据parentCode查询
     *
     * @param parentCode
     * @return
     */
    public static List<PermissionEnum> getByParentCode(Integer parentCode) {
        if (Objects.isNull(parentCode)) {
            return null;
        }
        List<PermissionEnum> list = new ArrayList<>();
        for (PermissionEnum permissionEnum : values()) {
            if (Objects.equals(permissionEnum.getParentCode(), parentCode)) {
                list.add(permissionEnum);
            }
        }
        return list;
    }

    /**
     * 根据code查询
     *
     * @param code
     * @return
     */
    public static PermissionEnum getByCode(Integer code) {
        if (Objects.isNull(code)) {
            return null;
        }
        for (PermissionEnum permissionEnum : values()) {
            if (Objects.equals(permissionEnum.getCode(), code)) {
                return permissionEnum;
            }
        }
        return null;
    }

    /**
     * 权限枚举集合
     *
     * @param parentCode
     * @return
     */
    public static List<PermissionEnum> getChildByParentCodeAndTypeCode(PermissionTypeEnum permissionTypeEnum, Integer parentCode) {
        if (Objects.isNull(parentCode) || Objects.isNull(permissionTypeEnum)) {
            return null;
        }
        List<PermissionEnum> list = new LinkedList<>();
        for (PermissionEnum permissionEnum : values()) {
            if (Objects.equals(permissionEnum.getParentCode(), parentCode) && Objects.equals(permissionEnum.getTypeCode(), permissionTypeEnum.getCode())) {
                list.add(permissionEnum);
            }
        }
        return list;
    }


    /**
     * 权限枚举集合
     *
     * @return
     */
    private static List<PermissionEnum> getPermissionEnumList() {
        List<PermissionEnum> list = new LinkedList<>();
        for (PermissionEnum permissionEnum : values()) {
            list.add(permissionEnum);
        }
        return list;
    }

    /**
     * 枚举集合
     *
     * @param map    会员权限
     * @param spread 是否展开
     * @return
     */
    public static List<PermissionDTO> getPermissionDTO(Map<String, String> map, Boolean spread) {
        if (Objects.isNull(map)) {
            map = new HashMap<>();
        }
        /** 获取权限 **/
        List<PermissionEnum> enumList = getByTypeCode(PermissionTypeEnum.PERMISSION.getCode());
        List<PermissionDTO> list = new ArrayList<>();
        for (PermissionEnum permissionEnum : enumList) {
            /** 权限组 **/
            List<PermissionEnum> enumList1 = getChildByParentCodeAndTypeCode(PermissionTypeEnum.GROUP, permissionEnum.getCode());
            List<PermissionDTO> list1 = new ArrayList<>();
            for (PermissionEnum permissionEnum1 : enumList1) {
                /** 权限菜单 **/
                List<PermissionEnum> enumList2 = getChildByParentCodeAndTypeCode(PermissionTypeEnum.MENU, permissionEnum1.getCode());
                List<PermissionDTO> list2 = new ArrayList<>();
                for (PermissionEnum permissionEnum2 : enumList2) {
                    /** 权限按钮 **/
                    List<PermissionEnum> enumList3 = getChildByParentCodeAndTypeCode(PermissionTypeEnum.BUTTON, permissionEnum2.getCode());
                    List<PermissionDTO> list3 = new ArrayList<>();
                    for (PermissionEnum permissionEnum3 : enumList3) {
                        list3.add(getPermissionDTO(permissionEnum3.getCode(), permissionEnum3.getTitle(), getChecked(map, permissionEnum3.getCode(), null), null));
                    }
                    list2.add(getPermissionDTO(permissionEnum2.getCode(), permissionEnum2.getTitle(), getChecked(map, permissionEnum2.getCode(), list3), list3));
                }
                list1.add(getPermissionDTO(permissionEnum1.getCode(), permissionEnum1.getTitle(), getChecked(map, permissionEnum1.getCode(), list2), list2));
            }
            list.add(getPermissionDTO(permissionEnum.getCode(), permissionEnum.getTitle(), getChecked(map, permissionEnum.getCode(), list1), spread, list1));
        }
        return list;
    }

    private static Boolean getChecked(Map<String, String> map, Integer code, List<PermissionDTO> list) {
        if (Objects.isNull(map.get(code.toString()))) {
            return Boolean.FALSE;
        }
        if (Objects.nonNull(list)) {
            List<PermissionDTO> checkedList = new ArrayList<>();
            for (PermissionDTO permissionDTO : list) {
                if (permissionDTO.getChecked()) {
                    checkedList.add(permissionDTO);
                }
            }
            if (checkedList.size() != list.size()) {
                return Boolean.FALSE;
            }
        }
        return Boolean.TRUE;
    }

    private static PermissionDTO getPermissionDTO(Integer id, String title, Boolean checked, List<PermissionDTO> children) {
        PermissionDTO permissionDTO = new PermissionDTO();
        permissionDTO.setId(id);
        permissionDTO.setTitle(title);
        permissionDTO.setChecked(checked);
        permissionDTO.setChildren(children);
        return permissionDTO;
    }

    private static PermissionDTO getPermissionDTO(Integer id, String title, Boolean checked, Boolean spread, List<PermissionDTO> children) {
        PermissionDTO permissionDTO = new PermissionDTO();
        permissionDTO.setId(id);
        permissionDTO.setTitle(title);
        permissionDTO.setChecked(checked);
        permissionDTO.setSpread(spread);
        permissionDTO.setChildren(children);
        return permissionDTO;
    }

    /**
     * 根据PermissionDTO 集合获取List<String>
     *
     * @param permissionJson
     * @return
     */
    public static List<String> getPermissionCode(String permissionJson) {
        if (Objects.isNull(permissionJson)) {
            return null;
        }
        try {
            List<PermissionDTO> list = JSON.parseArray(permissionJson, PermissionDTO.class);
            List<String> codeList = new ArrayList<>();
            if (Objects.nonNull(list)) {
                for (PermissionDTO permissionDTO : list) {
                    // 1
                    codeList.add(permissionDTO.getId().toString());
                    if (Objects.nonNull(permissionDTO.getChildren())) {
                        for (PermissionDTO permissionDTO1 : permissionDTO.getChildren()) {
                            // 2
                            codeList.add(permissionDTO1.getId().toString());
                            if (Objects.nonNull(permissionDTO1.getChildren())) {
                                for (PermissionDTO permissionDTO2 : permissionDTO1.getChildren()) {
                                    // 3
                                    codeList.add(permissionDTO2.getId().toString());
                                    if (Objects.nonNull(permissionDTO2.getChildren())) {
                                        for (PermissionDTO permissionDTO3 : permissionDTO2.getChildren()) {
                                            // 4
                                            codeList.add(permissionDTO3.getId().toString());
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return codeList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
