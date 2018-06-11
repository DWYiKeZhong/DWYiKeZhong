package com.example.yikezhong.bean;

import java.util.List;

/**
 * 获取某个用户的视频作品集
 */

public class GetUserVideosBean {

    /**
     * msg : 获取作品列表成功
     * code : 0
     * data : [{"commentNum":null,"cover":"https://www.zhaoapi.cn/images/quarter/1517732692467video_don`t zip zap zop.png","createTime":"2018-02-04T16:24:52","favoriteNum":null,"latitude":"39.95","localUri":null,"longitude":"116.30","playNum":2,"praiseNum":null,"uid":3881,"videoUrl":"https://www.zhaoapi.cn/images/quarter/1517732692467video_cover_don`t zip zap zop.wmv","wid":280,"workDesc":null},{"commentNum":null,"cover":"https://www.zhaoapi.cn/images/quarter/1517732837250video_cover_tokyo_hot.jpg","createTime":"2018-02-04T16:27:17","favoriteNum":null,"latitude":"39.95","localUri":null,"longitude":"116.30","playNum":46,"praiseNum":null,"uid":3881,"videoUrl":"https://www.zhaoapi.cn/images/quarter/1517732837250video_tokyo_hot.mp4","wid":281,"workDesc":null},{"commentNum":null,"cover":"https://www.zhaoapi.cn/images/quarter/1517733899967video_cover_fucking.jpg","createTime":"2018-02-04T16:44:59","favoriteNum":null,"latitude":"39.95","localUri":null,"longitude":"116.30","playNum":1009,"praiseNum":null,"uid":3881,"videoUrl":"https://www.zhaoapi.cn/images/quarter/1517733899967video_fucking_awesome.mp4","wid":282,"workDesc":"姑娘放开那个武器让我来"},{"commentNum":null,"cover":"https://www.zhaoapi.cn/images/quarter/1523262781765截屏_20180409_162125.jpg","createTime":"2018-04-09T16:33:01","favoriteNum":null,"latitude":"69.11","localUri":null,"longitude":"107.02","playNum":0,"praiseNum":null,"uid":3881,"videoUrl":"https://www.zhaoapi.cn/images/quarter/1523262781765绝地求生：刺激战场_吴京版.mp4","wid":318,"workDesc":"绝地求生：刺激战场_吴京版"},{"commentNum":null,"cover":"https://www.zhaoapi.cn/images/quarter/1523262990592截屏_20180409_162849.jpg","createTime":"2018-04-09T16:36:30","favoriteNum":null,"latitude":"66","localUri":null,"longitude":"107","playNum":0,"praiseNum":null,"uid":3881,"videoUrl":"https://www.zhaoapi.cn/images/quarter/1523262990592集合卧倒.mp4","wid":319,"workDesc":"全体集合，隐藏埋伏，准备吃鸡"},{"commentNum":null,"cover":"https://www.zhaoapi.cn/images/quarter/1523263302808截屏_20180409_163729.jpg","createTime":"2018-04-09T16:41:42","favoriteNum":null,"latitude":"66","localUri":null,"longitude":"107","playNum":0,"praiseNum":null,"uid":3881,"videoUrl":"https://www.zhaoapi.cn/images/quarter/1523263302808吓我一跳.mp4","wid":320,"workDesc":"尼玛又唬我"},{"commentNum":null,"cover":"https://www.zhaoapi.cn/images/quarter/1523263424886截屏_20180409_163842.jpg","createTime":"2018-04-09T16:43:44","favoriteNum":null,"latitude":"69.11","localUri":null,"longitude":"107.02","playNum":0,"praiseNum":null,"uid":3881,"videoUrl":"https://www.zhaoapi.cn/images/quarter/1523263424886猫出来.mp4","wid":321,"workDesc":"你瞅啥？瞅你咋地！"}]
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
         * commentNum : null
         * cover : https://www.zhaoapi.cn/images/quarter/1517732692467video_don`t zip zap zop.png
         * createTime : 2018-02-04T16:24:52
         * favoriteNum : null
         * latitude : 39.95
         * localUri : null
         * longitude : 116.30
         * playNum : 2
         * praiseNum : null
         * uid : 3881
         * videoUrl : https://www.zhaoapi.cn/images/quarter/1517732692467video_cover_don`t zip zap zop.wmv
         * wid : 280
         * workDesc : null
         */

        private Object commentNum;
        private String cover;
        private String createTime;
        private Object favoriteNum;
        private String latitude;
        private Object localUri;
        private String longitude;
        private int playNum;
        private Object praiseNum;
        private int uid;
        private String videoUrl;
        private int wid;
        private Object workDesc;

        public Object getCommentNum() {
            return commentNum;
        }

        public void setCommentNum(Object commentNum) {
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

        public Object getFavoriteNum() {
            return favoriteNum;
        }

        public void setFavoriteNum(Object favoriteNum) {
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

        public Object getPraiseNum() {
            return praiseNum;
        }

        public void setPraiseNum(Object praiseNum) {
            this.praiseNum = praiseNum;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
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

        public Object getWorkDesc() {
            return workDesc;
        }

        public void setWorkDesc(Object workDesc) {
            this.workDesc = workDesc;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "commentNum=" + commentNum +
                    ", cover='" + cover + '\'' +
                    ", createTime='" + createTime + '\'' +
                    ", favoriteNum=" + favoriteNum +
                    ", latitude='" + latitude + '\'' +
                    ", localUri=" + localUri +
                    ", longitude='" + longitude + '\'' +
                    ", playNum=" + playNum +
                    ", praiseNum=" + praiseNum +
                    ", uid=" + uid +
                    ", videoUrl='" + videoUrl + '\'' +
                    ", wid=" + wid +
                    ", workDesc=" + workDesc +
                    '}';
        }
    }
}
