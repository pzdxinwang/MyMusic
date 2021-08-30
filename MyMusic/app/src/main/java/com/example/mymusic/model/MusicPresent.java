package com.example.mymusic.model;

/**
 * 对应的是那个搜索的api
 */

import java.util.List;

public class MusicPresent {

    private List<tracksBean> tracks;

    public List<tracksBean> getTracks() {
        return tracks;
    }

    public void setTracks(List<tracksBean> tracks) {
        this.tracks = tracks;
    }


    public static class tracksBean {

        private String name;
        private int id;
        private int fee;
        private AlBean al;
        private List<ArBean> ar;
        private int mv;
        private List<String> alia;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getFee() {
            return fee;
        }

        public void setFee(int fee) {
            this.fee = fee;
        }

        public AlBean getAl() {
            return al;
        }

        public void setAl(AlBean al) {
            this.al = al;
        }

        public List<ArBean> getAr() {
            return ar;
        }

        public void setAr(List<ArBean> ar) {
            this.ar = ar;
        }

        public int getMv() {
            return mv;
        }

        public void setMv(int mv) {
            this.mv = mv;
        }

        public List<String> getAlia() {
            return alia;
        }

        public void setAlia(List<String> alia) {
            this.alia = alia;
        }

        //专辑名
        public static class AlBean {
            private int id;
            private String name;
            private String picUrl;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPicUrl() {
                return picUrl;
            }

            public void setPicUrl(String picUrl) {
                this.picUrl = picUrl;
            }

        }

        //歌手名
        public static class ArBean {

            private int id;
            private String name;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}

