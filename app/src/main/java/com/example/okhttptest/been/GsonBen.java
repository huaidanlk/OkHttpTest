package com.example.okhttptest.been;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by 坏蛋 on 2017/6/14.
 */

public class GsonBen extends JSONObject{

    /**
     * error : false
     * results : [{"_id":"593a6015421aa92c769a8c43","createdAt":"2017-06-09T16:45:09.679Z","desc":"微信数据库组件，开源了～","publishedAt":"2017-06-14T11:34:54.556Z","source":"web","type":"Android","url":"https://github.com/Tencent/wcdb","used":true,"who":"color"},{"_id":"593f205e421aa92c73b64804","createdAt":"2017-06-13T07:14:38.148Z","desc":"又一个漂亮的 Gank 客户端！","images":["http://img.gank.io/6a80ddb8-646f-4177-bdae-d6066aa18982"],"publishedAt":"2017-06-14T11:34:54.556Z","source":"chrome","type":"Android","url":"https://github.com/yanyiqun001/ganguo","used":true,"who":"代码家"},{"_id":"593f2083421aa92c769a8c69","createdAt":"2017-06-13T07:15:15.25Z","desc":"歌词风格的 TextView，利用 Gradient 渐变实现。","images":["http://img.gank.io/738f9e17-60b8-4361-be84-3d11de47d4bc"],"publishedAt":"2017-06-14T11:34:54.556Z","source":"chrome","type":"Android","url":"https://github.com/livesun/GradientTextView","used":true,"who":"代码家"},{"_id":"593f4df8421aa92c769a8c70","createdAt":"2017-06-13T10:29:12.239Z","desc":"Menu Animation Android","publishedAt":"2017-06-14T11:34:54.556Z","source":"web","type":"Android","url":"https://blog.mindorks.com/menu-animation-android-2876869d5855","used":true,"who":"AMIT SHEKHAR"},{"_id":"593f8d6c421aa92c769a8c76","createdAt":"2017-06-13T14:59:56.902Z","desc":"基于Material Design设计的环形菜单控件","images":["http://img.gank.io/25a41a46-1bff-490b-8dc8-03c31f52389f"],"publishedAt":"2017-06-14T11:34:54.556Z","source":"web","type":"Android","url":"https://github.com/DingMouRen/AnnularMenuView","used":true,"who":null},{"_id":"594073a8421aa92c769a8c7d","createdAt":"2017-06-14T07:22:16.404Z","desc":"一大波Android技术干货！","publishedAt":"2017-06-14T11:34:54.556Z","source":"web","type":"Android","url":"http://url.cn/4APgNxz","used":true,"who":"陈宇明"},{"_id":"5940a7f2421aa92c769a8c80","createdAt":"2017-06-14T11:05:22.663Z","desc":"GMTC参会纪实，谁的寒冬又是谁的春天","publishedAt":"2017-06-14T11:34:54.556Z","source":"web","type":"Android","url":"https://mp.weixin.qq.com/s?__biz=MzI3OTU3OTQ1Mw==&mid=2247483797&idx=1&sn=8c4fcb3566d2e46af4039ad1f21392c6&chksm=eb44dfc7dc3356d1725ac294deca95832825a96b4993ea7c90ec4c5a1fa1d4f04d1fabac16f4#rd","used":true,"who":null},{"_id":"593df47c421aa92c769a8c58","createdAt":"2017-06-12T09:55:08.555Z","desc":"花式封装与使用SpannableString","images":["http://img.gank.io/fe6e86bc-a9d6-425b-984c-15605efa9066"],"publishedAt":"2017-06-12T11:11:11.25Z","source":"web","type":"Android","url":"http://www.jianshu.com/p/509b0d2626f4","used":true,"who":"Mengjie Cai"},{"_id":"593df95a421aa92c794633ab","createdAt":"2017-06-12T10:15:54.140Z","desc":"Android View Tooltips，目前来看做的最漂亮的。","publishedAt":"2017-06-12T11:11:11.25Z","source":"chrome","type":"Android","url":"https://github.com/florent37/ViewTooltip","used":true,"who":"代码家"},{"_id":"593dfa84421aa92c794633ac","createdAt":"2017-06-12T10:20:52.751Z","desc":"Android PinCode 密码输入效果","images":["http://img.gank.io/81405d8f-20bf-4456-b23a-945feffbdddd"],"publishedAt":"2017-06-12T11:11:11.25Z","source":"chrome","type":"Android","url":"https://github.com/antoxa2584x/PinCodeView","used":true,"who":"代码家"}]
     */

    private boolean error;
    private List<ResultsBean> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        @Override
        public String toString() {
            return "ResultsBean{" +
                    "_id='" + _id + '\'' +
                    ", createdAt='" + createdAt + '\'' +
                    ", desc='" + desc + '\'' +
                    ", publishedAt='" + publishedAt + '\'' +
                    ", source='" + source + '\'' +
                    ", type='" + type + '\'' +
                    ", url='" + url + '\'' +
                    ", used=" + used +
                    ", who='" + who + '\'' +
                    ", images=" + images +
                    '}';
        }

        /**
         * _id : 593a6015421aa92c769a8c43
         * createdAt : 2017-06-09T16:45:09.679Z
         * desc : 微信数据库组件，开源了～
         * publishedAt : 2017-06-14T11:34:54.556Z
         * source : web
         * type : Android
         * url : https://github.com/Tencent/wcdb
         * used : true
         * who : color
         * images : ["http://img.gank.io/6a80ddb8-646f-4177-bdae-d6066aa18982"]
         */

        private String _id;
        private String createdAt;
        private String desc;
        private String publishedAt;
        private String source;
        private String type;
        private String url;
        private boolean used;
        private String who;
        private List<String> images;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public boolean isUsed() {
            return used;
        }

        public void setUsed(boolean used) {
            this.used = used;
        }

        public String getWho() {
            return who;
        }

        public void setWho(String who) {
            this.who = who;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }
}
