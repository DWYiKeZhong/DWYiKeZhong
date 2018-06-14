package com.example.yikezhong.bean;

import java.util.List;

/**
 * Created by lenovo on 2018/6/6.
 */

public class NearbyBean {
    /**
     * msg : 获取附近作品列表成功
     * code : 0
     * data : [{"commentNum":5,"comments":[{"cid":50,"content":"亚麻得","createTime":"2017-12-15T14:52:37","jid":null,"mvp":null,"nickname":"\"刘恒\"","praiseNum":0,"uid":150,"wid":218},{"cid":67,"content":"pp","createTime":"2017-12-16T11:53:56","jid":null,"mvp":null,"nickname":"李灿灿","praiseNum":0,"uid":148,"wid":218},{"cid":94,"content":"yyy","createTime":"2017-12-18T14:39:58","jid":null,"mvp":null,"nickname":"beautiful","praiseNum":0,"uid":170,"wid":218},{"cid":103,"content":"傻不傻","createTime":"2017-12-18T15:38:56","jid":null,"mvp":null,"nickname":"linnnn","praiseNum":0,"uid":114,"wid":218},{"cid":126,"content":"\u2026","createTime":"2017-12-18T20:54:59","jid":null,"mvp":null,"nickname":"小五","praiseNum":0,"uid":313,"wid":218}],"cover":"https://www.zhaoapi.cn/images/quarter/1513248624521cover.jpg","createTime":"2017-12-17T19:20:44","favoriteNum":0,"latitude":"101","localUri":null,"longitude":"102","playNum":16,"praiseNum":5,"uid":154,"User":{"age":null,"fans":"null","follow":false,"icon":"https://www.zhaoapi.cn/images/15136653175981513592154181.jpg","nickname":"笑出腹肌的男人","praiseNum":"null"},"videoUrl":"https://www.zhaoapi.cn/images/quarter/1513248624521PictureSelector_20171214_184937.mp4","wid":218,"workDesc":"111"}]
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
         * commentNum : 5
         * comments : [{"cid":50,"content":"亚麻得","createTime":"2017-12-15T14:52:37","jid":null,"mvp":null,"nickname":"\"刘恒\"","praiseNum":0,"uid":150,"wid":218},{"cid":67,"content":"pp","createTime":"2017-12-16T11:53:56","jid":null,"mvp":null,"nickname":"李灿灿","praiseNum":0,"uid":148,"wid":218},{"cid":94,"content":"yyy","createTime":"2017-12-18T14:39:58","jid":null,"mvp":null,"nickname":"beautiful","praiseNum":0,"uid":170,"wid":218},{"cid":103,"content":"傻不傻","createTime":"2017-12-18T15:38:56","jid":null,"mvp":null,"nickname":"linnnn","praiseNum":0,"uid":114,"wid":218},{"cid":126,"content":"\u2026","createTime":"2017-12-18T20:54:59","jid":null,"mvp":null,"nickname":"小五","praiseNum":0,"uid":313,"wid":218}]
         * cover : https://www.zhaoapi.cn/images/quarter/1513248624521cover.jpg
         * createTime : 2017-12-17T19:20:44
         * favoriteNum : 0
         * latitude : 101
         * localUri : null
         * longitude : 102
         * playNum : 16
         * praiseNum : 5
         * uid : 154
         * User : {"age":null,"fans":"null","follow":false,"icon":"https://www.zhaoapi.cn/images/15136653175981513592154181.jpg","nickname":"笑出腹肌的男人","praiseNum":"null"}
         * videoUrl : https://www.zhaoapi.cn/images/quarter/1513248624521PictureSelector_20171214_184937.mp4
         * wid : 218
         * workDesc : 111
         */

        private int commentNum;
        private String cover;
        private String createTime;
        private int favoriteNum;
        private String latitude;
        private Object localUri;
        private String longitude;
        private int playNum;
        private int praiseNum;
        private int uid;
        private UserBean user;
        private String videoUrl;
        private int wid;
        private String workDesc;
        private List<CommentsBean> comments;

        public int getCommentNum() {
            return commentNum;
        }

        public void setCommentNum(int commentNum) {
            this.commentNum = commentNum;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getFavoriteNum() {
            return favoriteNum;
        }

        public void setFavoriteNum(int favoriteNum) {
            this.favoriteNum = favoriteNum;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public Object getLocalUri() {
            return localUri;
        }

        public void setLocalUri(Object localUri) {
            this.localUri = localUri;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public int getPlayNum() {
            return playNum;
        }

        public void setPlayNum(int playNum) {
            this.playNum = playNum;
        }

        public int getPraiseNum() {
            return praiseNum;
        }

        public void setPraiseNum(int praiseNum) {
            this.praiseNum = praiseNum;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public String getVideoUrl() {
            return videoUrl;
        }

        public void setVideoUrl(String videoUrl) {
            this.videoUrl = videoUrl;
        }

        public int getWid() {
            return wid;
        }

        public void setWid(int wid) {
            this.wid = wid;
        }

        public String getWorkDesc() {
            return workDesc;
        }

        public void setWorkDesc(String workDesc) {
            this.workDesc = workDesc;
        }

        public List<CommentsBean> getComments() {
            return comments;
        }

        public void setComments(List<CommentsBean> comments) {
            this.comments = comments;
        }

        public static class UserBean {
            /**
             * age : null
             * fans : null
             * follow : false
             * icon : https://www.zhaoapi.cn/images/15136653175981513592154181.jpg
             * nickname : 笑出腹肌的男人
             * praiseNum : null
             */

            private Object age;
            private String fans;
            private boolean follow;
            private String icon;
            private String nickname;
            private String praiseNum;

            public Object getAge() {
                return age;
            }

            public void setAge(Object age) {
                this.age = age;
            }

            public String getFans() {
                return fans;
            }

            public void setFans(String fans) {
                this.fans = fans;
            }

            public boolean isFollow() {
                return follow;
            }

            public void setFollow(boolean follow) {
                this.follow = follow;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getPraiseNum() {
                return praiseNum;
            }

            public void setPraiseNum(String praiseNum) {
                this.praiseNum = praiseNum;
            }
        }

        public static class CommentsBean {
            /**
             * cid : 50
             * content : 亚麻得
             * createTime : 2017-12-15T14:52:37
             * jid : null
             * mvp : null
             * nickname : "刘恒"
             * praiseNum : 0
             * uid : 150
             * wid : 218
             */

            private int cid;
            private String content;
            private String createTime;
            private Object jid;
            private Object mvp;
            private String nickname;
            private int praiseNum;
            private int uid;
            private int wid;

            public int getCid() {
                return cid;
            }

            public void setCid(int cid) {
                this.cid = cid;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public Object getJid() {
                return jid;
            }

            public void setJid(Object jid) {
                this.jid = jid;
            }

            public Object getMvp() {
                return mvp;
            }

            public void setMvp(Object mvp) {
                this.mvp = mvp;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public int getPraiseNum() {
                return praiseNum;
            }

            public void setPraiseNum(int praiseNum) {
                this.praiseNum = praiseNum;
            }

            public int getUid() {
                return uid;
            }

            public void setUid(int uid) {
                this.uid = uid;
            }

            public int getWid() {
                return wid;
            }

            public void setWid(int wid) {
                this.wid = wid;
            }
        }
    }
}
