package com.racofix.model.repo;

import java.util.List;

/**
 * @author: 蜡笔小新
 * @date: 2016-05-31 14:15
 * @GitHub: https://github.com/meikoz
 */
public class Classify {
    private boolean status;
    private int total;

    private List<TngouEntity> tngou;

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setTngou(List<TngouEntity> tngou) {
        this.tngou = tngou;
    }

    public boolean isStatus() {
        return status;
    }

    public int getTotal() {
        return total;
    }

    public List<TngouEntity> getTngou() {
        return tngou;
    }

    public static class TngouEntity {
        private int count;
        private int fcount;
        private int galleryclass;
        private int id;
        private String img;
        private int rcount;
        private int size;
        private long time;
        private String title;

        public void setCount(int count) {
            this.count = count;
        }

        public void setFcount(int fcount) {
            this.fcount = fcount;
        }

        public void setGalleryclass(int galleryclass) {
            this.galleryclass = galleryclass;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public void setRcount(int rcount) {
            this.rcount = rcount;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getCount() {
            return count;
        }

        public int getFcount() {
            return fcount;
        }

        public int getGalleryclass() {
            return galleryclass;
        }

        public int getId() {
            return id;
        }

        public String getImg() {
            return img;
        }

        public int getRcount() {
            return rcount;
        }

        public int getSize() {
            return size;
        }

        public long getTime() {
            return time;
        }

        public String getTitle() {
            return title;
        }
    }
}
