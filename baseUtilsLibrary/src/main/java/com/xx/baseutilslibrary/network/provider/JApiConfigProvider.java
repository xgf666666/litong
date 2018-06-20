package com.xx.baseutilslibrary.network.provider;

/**
 * JApiConfigProvider
 * (。・∀・)ノ
 * Describe：
 * Created by 雷小星🍀 on 2017/10/31 17:48.
 */

public interface JApiConfigProvider {
    /**
     * 获取Api绝对路径
     *
     * @return 接口基础路径
     */
    default String getApiBaseUrl() {
        return (isDebug() ? getDebugHost() : getReleaseHost()) + getApiRelativePath();
    }

    /**
     * 获取Api相对路径
     *
     * @return
     */
    String getApiRelativePath();

    /**
     * 获取调试版的Host路径
     *
     * @return 调试版的Host路径
     */
    String getDebugHost();

    /**
     * 获取发布版的Host路径
     *
     * @return 发布版的Host路径
     */
    String getReleaseHost();

    /**
     * 是否是Debug 模式
     *
     * @return 是否是Debug 模式
     */
    boolean isDebug();
}
