package co.siten.myvtg.bean;
/**
 * Created by luatnc on 15/05/2017.
 */
public class ResponseObj {
        String errCode;
        String responseStr;
        String requestStr;

        public ResponseObj() {
        }

        public String getErrCode() {
                return errCode;
        }

        public void setErrCode(String errCode) {
                this.errCode = errCode;
        }

        public String getResponseStr() {
                return responseStr;
        }

        public void setResponseStr(String responseStr) {
                this.responseStr = responseStr;
        }

        public String getRequestStr() {
                return requestStr;
        }

        public void setRequestStr(String requestStr) {
                this.requestStr = requestStr;
        }
}
