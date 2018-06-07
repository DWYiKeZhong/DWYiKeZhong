package com.example.yikezhong.bean;

import java.util.List;

/**
 * Created   by   Dewey .
 * 关注用户列表
 */
public class GuanListBean {

    /**
     * msg : 获取关注用户列表成功
     * code : 0
     * data : [{"age":null,"appkey":"efa34e9f47fd52b3","appsecret":"54AB0FBEEC135E1F78626A2A624AA4CB","createtime":"2018-05-11T08:03:21","email":null,"fans":null,"follow":null,"gender":null,"icon":"https://www.zhaoapi.cn/images/1524713222381$UK4AL@_ZOCC[$WYVNBK}OS.jpg","latitude":null,"longitude":null,"mobile":"15631440600","money":null,"nickname":"大苏打","password":"8F669074CAF5513351A2DE5CC22AC04C","praiseNum":null,"token":"064658A9304FA4A00AEC52B789ED4926","uid":10160,"userId":null,"username":"15631440600"},{"age":null,"appkey":null,"appsecret":null,"createtime":"2018-04-09T16:16:20","email":null,"fans":null,"follow":null,"gender":0,"icon":"https://www.zhaoapi.cn/images/1513246264287cropped_1513246262263.jpg","latitude":null,"longitude":null,"mobile":"15810672623","money":0,"nickname":"\"刘恒\"","password":"123456","praiseNum":null,"token":"28C3793F9AB8E555D523C46D8D867998","uid":150,"userId":null,"username":"15810672623"},{"age":null,"appkey":"2d962d6f8056b195","appsecret":"D42A30302B8497B97752C3B5B9C83189","createtime":"2018-01-31T13:52:18","email":null,"fans":null,"follow":null,"gender":null,"icon":"https://www.zhaoapi.cn/images/1516841991537timg.jpg","latitude":null,"longitude":null,"mobile":"18637973081","money":null,"nickname":"熊猫","password":"8F669074CAF5513351A2DE5CC22AC04C","praiseNum":null,"token":"72C032EA59A11A107C81D57047F2FF6E","uid":1758,"userId":null,"username":"18637973081"},{"age":null,"appkey":null,"appsecret":null,"createtime":"2017-11-13T20:35:16","email":null,"fans":null,"follow":null,"gender":0,"icon":"http://120.27.23.105/images/264.jpg","latitude":null,"longitude":null,"mobile":"13161962287","money":0,"nickname":"厂长4396","password":"111111","praiseNum":null,"token":"7EF8444848D4310EA8FB95CBA2C0AD52","uid":264,"userId":null,"username":"13161962287"},{"age":null,"appkey":null,"appsecret":null,"createtime":"2017-10-10T00:00:00","email":null,"fans":null,"follow":null,"gender":0,"icon":"https://www.zhaoapi.cn/images/1524231294329t016b697d0374c17e3f.jpg","latitude":null,"longitude":null,"mobile":"18612991021","money":0,"nickname":"你好","password":"111111","praiseNum":null,"token":null,"uid":123,"userId":null,"username":"18612991021"}]
     */

    private String msg;
    private String code;
    private List<DataBean> data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * age : null
         * appkey : efa34e9f47fd52b3
         * appsecret : 54AB0FBEEC135E1F78626A2A624AA4CB
         * createtime : 2018-05-11T08:03:21
         * email : null
         * fans : null
         * follow : null
         * gender : null
         * icon : https://www.zhaoapi.cn/images/1524713222381$UK4AL@_ZOCC[$WYVNBK}OS.jpg
         * latitude : null
         * longitude : null
         * mobile : 15631440600
         * money : null
         * nickname : 大苏打
         * password : 8F669074CAF5513351A2DE5CC22AC04C
         * praiseNum : null
         * token : 064658A9304FA4A00AEC52B789ED4926
         * uid : 10160
         * userId : null
         * username : 15631440600
         */

        private Object age;
        private String appkey;
        private String appsecret;
        private String createtime;
        private Object email;
        private Object fans;
        private Object follow;
        private Object gender;
        private String icon;
        private Object latitude;
        private Object longitude;
        private String mobile;
        private Object money;
        private String nickname;
        private String password;
        private Object praiseNum;
        private String token;
        private int uid;
        private Object userId;
        private String username;

        public Object getAge() {
            return age;
        }

        public void setAge(Object age) {
            this.age = age;
        }

        public String getAppkey() {
            return appkey;
        }

        public void setAppkey(String appkey) {
            this.appkey = appkey;
        }

        public String getAppsecret() {
            return appsecret;
        }

        public void setAppsecret(String appsecret) {
            this.appsecret = appsecret;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public Object getEmail() {
            return email;
        }

        public void setEmail(Object email) {
            this.email = email;
        }

        public Object getFans() {
            return fans;
        }

        public void setFans(Object fans) {
            this.fans = fans;
        }

        public Object getFollow() {
            return follow;
        }

        public void setFollow(Object follow) {
            this.follow = follow;
        }

        public Object getGender() {
            return gender;
        }

        public void setGender(Object gender) {
            this.gender = gender;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public Object getLatitude() {
            return latitude;
        }

        public void setLatitude(Object latitude) {
            this.latitude = latitude;
        }

        public Object getLongitude() {
            return longitude;
        }

        public void setLongitude(Object longitude) {
            this.longitude = longitude;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public Object getMoney() {
            return money;
        }

        public void setMoney(Object money) {
            this.money = money;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public Object getPraiseNum() {
            return praiseNum;
        }

        public void setPraiseNum(Object praiseNum) {
            this.praiseNum = praiseNum;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public Object getUserId() {
            return userId;
        }

        public void setUserId(Object userId) {
            this.userId = userId;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "age=" + age +
                    ", appkey='" + appkey + '\'' +
                    ", appsecret='" + appsecret + '\'' +
                    ", createtime='" + createtime + '\'' +
                    ", email=" + email +
                    ", fans=" + fans +
                    ", follow=" + follow +
                    ", gender=" + gender +
                    ", icon='" + icon + '\'' +
                    ", latitude=" + latitude +
                    ", longitude=" + longitude +
                    ", mobile='" + mobile + '\'' +
                    ", money=" + money +
                    ", nickname='" + nickname + '\'' +
                    ", password='" + password + '\'' +
                    ", praiseNum=" + praiseNum +
                    ", token='" + token + '\'' +
                    ", uid=" + uid +
                    ", userId=" + userId +
                    ", username='" + username + '\'' +
                    '}';
        }
    }
}
