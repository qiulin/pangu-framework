package org.pf9.pangu.framework.data.domain;

import java.util.HashMap;
import java.util.Map;

public class ResponseResult {

    public static ResponseResult success() {
        return new ResponseResult().code(ResultCode.success).markSuccess().message(ResultCode.success.getMessage());
    }

    public static ResponseResult failure() {
        return new ResponseResult().code(ResultCode.failure).message(ResultCode.failure.getMessage());
    }

    public static ResponseResult validateError() {
        return new ResponseResult().code(ResultCode.validateError).message(ResultCode.validateError.getMessage());
    }

    public static ResponseResult accessDenide() {
        return new ResponseResult().code(ResultCode.accessDenide).message(ResultCode.accessDenide.getMessage());
    }

    public static ResponseResult notLogin() {
        return new ResponseResult().code(ResultCode.notLogin).message(ResultCode.notLogin.getMessage());
    }

    public static ResponseResult exception() {
        return new ResponseResult().code(ResultCode.exception).message(ResultCode.exception.getMessage());
    }

    public static ResponseResult unknown() {
        return new ResponseResult().code(ResultCode.unknown).message(ResultCode.unknown.getMessage());
    }

    public static ResponseResult captchaError() {
        return new ResponseResult().code(ResultCode.captchaError).message(ResultCode.captchaError.getMessage());
    }

    private ResultCode code;
    private String message;
    private boolean success = false;

    private Map<String, Object> data = new HashMap<>();

    public ResultCode getCode() {
        return code;
    }

    public ResponseResult code(ResultCode code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ResponseResult message(String message) {
        this.message = message;
        return this;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public ResponseResult addData(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

    public ResponseResult data(String key, Object data) {

        // 异常时，使用异常信息
        if (this.code == ResultCode.exception && data instanceof Exception) {
            this.message(((Exception) data).getMessage());
        }
        return addData(key, data);
    }

    public ResponseResult data(Object data) {
        return data("rows", data);
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public ResponseResult markSuccess() {
        this.setSuccess(true);
        return this;
    }

    public enum ResultCode {
        success("操作成功"), failure("操作失败"), validateError("验证错误"), accessDenide("无权限访问"), notLogin("未登录"), captchaError(
                "验证码错误"), exception("系统异常"), unknown("未知情况");

        private final String message;

        ResultCode(final String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }
}
