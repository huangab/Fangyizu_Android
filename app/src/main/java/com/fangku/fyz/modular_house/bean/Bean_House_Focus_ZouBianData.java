package com.fangku.fyz.modular_house.bean;/**
 * Created by 67343 on 2016/8/24.
 */

import java.util.List;

/**
 * Created by   J
 * Date: 2016/7/11
 * Time: 15:04
 */
public class Bean_House_Focus_ZouBianData {

    /**
     * code : 200
     * data : {"AroundSupport":[{"name":"银行","basId":"B"},{"name":"网吧","basId":"N"},{"name":"超市","basId":"S"}]}
     */

    private String code;
    private DataBean data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * name : 银行
         * basId : B
         */

        private List<AroundSupportBean> AroundSupport;

        public List<AroundSupportBean> getAroundSupport() {
            return AroundSupport;
        }

        public void setAroundSupport(List<AroundSupportBean> AroundSupport) {
            this.AroundSupport = AroundSupport;
        }

        public static class AroundSupportBean {
            private String name;
            private String basId;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getBasId() {
                return basId;
            }

            public void setBasId(String basId) {
                this.basId = basId;
            }
        }
    }
}
