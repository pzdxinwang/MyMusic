package com.example.mymusic.model;

import java.util.List;

/*
{
{
"code":200,
"relatedVideos":null,
"playlist":{},
"urls":null,
"privileges":[],
"sharedPrivilege":null
}

playlist:
"id":24381616,
"name":"binaryify喜欢的音乐",
"coverImgId":109951165446962380,
"coverImgUrl":"https://p1.music.126.net/TKala-POVRn7ZQs3nP-oWQ==/109951165446962390.jpg",
"coverImgId_str":"109951165446962390",
"adType":0,
"userId":32953014,
"createTime":1407747901072,
"status":0,
"opRecommend":false,
"highQuality":false,
"newImported":false,
"updateTime":1630058072687,
"tracks":[],

 */
public class Music {
    private playListModel playListModel;

    public Music(Music.playListModel playListModel) {
        this.playListModel = playListModel;
    }

    public Music.playListModel getPlayListModel() {
        return playListModel;
    }

    public void setPlayListModel(Music.playListModel playListModel) {
        this.playListModel = playListModel;
    }

    public class playListModel {
        private List<TrackIdsBean> trackIds;

        public playListModel(List<TrackIdsBean> trackIds) {
            this.trackIds = trackIds;
        }

        public List<TrackIdsBean> getTrackIds() {
            return trackIds;
        }

        public void setTrackIds(List<TrackIdsBean> trackIds) {
            this.trackIds = trackIds;
        }

        public class TrackIdsBean {
            private String id;

            public TrackIdsBean(String id) {
                this.id = id;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }
        }
    }
}
