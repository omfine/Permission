package com.omfine.utils.permission.ext;

import java.util.ArrayList;

/**
 * 权限请求结果回调。
 * @author E
 */
public class OnPermissionRequestResultListener {
    /**
     * 所有的权限被允许。
     */
    public void onGranted(){}

    /**
     * 权限被拒绝。
     * @param grantedPermissions 在多个权限中，有通过的，有拒绝的
     * @param deniedWithRemindPermissions 拒绝了，但下次请求还会提醒的权限
     * @param deniedWithoutRemindPermissions 拒绝且不再提醒的权限, 需要跳转到应用详情页面，手动允许相关权限
     */
    public void onDenied(ArrayList<String> grantedPermissions ,ArrayList<String> deniedWithRemindPermissions , ArrayList<String> deniedWithoutRemindPermissions){}

}
