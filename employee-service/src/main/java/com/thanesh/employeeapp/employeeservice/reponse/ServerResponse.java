package com.thanesh.employeeapp.employeeservice.reponse;

//@Setter
//@Getter
//@NoArgsConstructor
//@AllArgsConstructor
//@ToString
public class ServerResponse {
    String code;
    String message;
    Object data;
    String status;

    public ServerResponse() {
    }

    public ServerResponse(String code, String message, Object data, String status) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ServerResponse{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                ", status='" + status + '\'' +
                '}';
    }
}
