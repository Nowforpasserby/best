package com.logictech.config;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Strings;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import static com.logictech.App.logger;

/**
 * @author JG.Hannibal
 * @since 2017/11/11 下午1:16
 */
public class MessageConfig {
    private static final String HEAD = "##########|    ";

    /**
     * Message 格式化工具
     */
    public final static MessageFormat FORMAT = new MessageFormat("");
    /**
     * Message cache 中文
     */
    final static Map<String, String> messageCacheMap = new HashMap<>();

    static {
        ResourceBundle rb = ResourceBundle.getBundle("message/messages");
        Set<String> keySet = rb.keySet();
        for (String keyStr : keySet) {
            messageCacheMap.put(keyStr, rb.getString(keyStr));
        }
    }

    /**
     * @param key message.properties 存在的key
     * @Author xp-zhao@logictech.cn
     * @CreateOn 2016/12/28 13:40
     * @Parameter message.properties 存在的key
     * @Remark 获取il8n下存在的value
     */
    public static String get(String key) {
        return get(key, null);
    }

    /**
     * @param key  message.properties 存在的key
     * @param arg0 填充参数
     * @Author xp-zhao@logictech.cn
     * @CreateOn 2016/12/28 13:40
     * @Remark 获取il8n下存在的携带参数的value
     */
    public static String get(String key, String... arg0) {
        String msgCh = messageCacheMap.get(key);
        logger.info(HEAD + "获取配置文件：{}，值：{}", key, msgCh);
        if (Strings.isNullOrEmpty(msgCh)) {
            return "{}";
        }
        FORMAT.applyPattern(msgCh);
        final String format = FORMAT.format(arg0);
        if (arg0 != null) {
            logger.info(HEAD + "填充数据：{}", format);
        }
        return String.valueOf(JSON.toJSON(format));
    }

}
    