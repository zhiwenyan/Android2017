package zhiwenyan.cmccaifu.com.android2017.retrofit.model;

import java.util.List;

/**
 * Created by zhiwenyan on 4/19/17.
 */

public class BookResponse {


    /**
     * rating : {"max":10,"numRaters":2,"average":"0.0","min":0}
     * subtitle :
     * author : ["罗斯玛丽・汤姆森"]
     * pubdate : 2003-5-1
     * tags : [{"count":1,"name":"人际","title":"人际"},{"count":1,"name":"摇滚","title":"摇滚"},{"count":1,"name":"民谣","title":"民谣"},{"count":1,"name":"管理","title":"管理"},{"count":1,"name":"职场","title":"职场"}]
     * origin_title :
     * image : https://img3.doubanio.com/mpic/s1154446.jpg
     * binding : 平装
     * translator : ["方薇"]
     * catalog : 序言
     第一章 人力资源管理者的作用
     第二章 与人交流
     第三章 决策与计划
     第四章 工作满意度、报酬与忠诚
     第五章 绩效与职业生涯管理
     第六章 培训与发展
     第七章 入门、训练、指导和授权
     第八章 团队协作
     第九章 合适的人做合适的工作
     第十章 敏感问题
     * pages : 304
     * images : {"small":"https://img3.doubanio.com/spic/s1154446.jpg","large":"https://img3.doubanio.com/lpic/s1154446.jpg","medium":"https://img3.doubanio.com/mpic/s1154446.jpg"}
     * alt : https://book.douban.com/subject/1003077/
     * id : 1003077
     * publisher : 中信出版社
     * isbn10 : 780073692X
     * isbn13 : 9787800736926
     * title : 管人管到位
     * url : https://api.douban.com/v2/book/1003077
     * alt_title :
     * author_intro : 罗斯玛丽・汤姆森是当前人力资源管理方面的杰出作家和教练，在英国开入大学（Open University)商学院任教。
     * summary : 管理问题说到底是一具管人的问题，无论是资金，还是供货，销售或者设备故障，追根溯源，都能找到人为因素。对一个组织来说，管理的一人主要目的就是，使得人员不至于成为管理者乃至整个组织的问题或者麻烦；另一方面，人员是无价之宝，它是组织最重要的资本。管人管到位，确保组织高效运转，增强组织的竞争力，管理者责无旁贷。杰出的管理教练和学者罗斯玛丽・汤姆森分析了管理者每天都要遇到的普遍存在的问题，为我们提供了一系列解决问题的方法。而人员管理的最高目标并不仅限于解决问题，对于如何调动员工的积极性、保持高绩效和持续竞争力，本收提出了有益的忠告和有效的技巧。书中讨论的十个问题都自成一体，分别介绍有关人力资源管理的不同方面的主题。每一章节还设置了一个虚拟的管理人员，你可以根据自己的理解和体验，去试着解决那些棘手的问题，同时也检验自己的收获。
     * price : 19.00
     */

    private RatingBean rating;
    private String subtitle;
    private String pubdate;
    private String origin_title;
    private String image;
    private String binding;
    private String catalog;
    private String pages;
    private ImagesBean images;
    private String alt;
    private String id;
    private String publisher;
    private String isbn10;
    private String isbn13;
    private String title;
    private String url;
    private String alt_title;
    private String author_intro;
    private String summary;
    private String price;
    private List<String> author;
    private List<TagsBean> tags;
    private List<String> translator;

    public RatingBean getRating() {
        return rating;
    }

    public void setRating(RatingBean rating) {
        this.rating = rating;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getPubdate() {
        return pubdate;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }

    public String getOrigin_title() {
        return origin_title;
    }

    public void setOrigin_title(String origin_title) {
        this.origin_title = origin_title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBinding() {
        return binding;
    }

    public void setBinding(String binding) {
        this.binding = binding;
    }

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public ImagesBean getImages() {
        return images;
    }

    public void setImages(ImagesBean images) {
        this.images = images;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getIsbn10() {
        return isbn10;
    }

    public void setIsbn10(String isbn10) {
        this.isbn10 = isbn10;
    }

    public String getIsbn13() {
        return isbn13;
    }

    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAlt_title() {
        return alt_title;
    }

    public void setAlt_title(String alt_title) {
        this.alt_title = alt_title;
    }

    public String getAuthor_intro() {
        return author_intro;
    }

    public void setAuthor_intro(String author_intro) {
        this.author_intro = author_intro;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public List<String> getAuthor() {
        return author;
    }

    public void setAuthor(List<String> author) {
        this.author = author;
    }

    public List<TagsBean> getTags() {
        return tags;
    }

    public void setTags(List<TagsBean> tags) {
        this.tags = tags;
    }

    public List<String> getTranslator() {
        return translator;
    }

    public void setTranslator(List<String> translator) {
        this.translator = translator;
    }

    public static class RatingBean {
        /**
         * max : 10
         * numRaters : 2
         * average : 0.0
         * min : 0
         */

        private int max;
        private int numRaters;
        private String average;
        private int min;

        public int getMax() {
            return max;
        }

        public void setMax(int max) {
            this.max = max;
        }

        public int getNumRaters() {
            return numRaters;
        }

        public void setNumRaters(int numRaters) {
            this.numRaters = numRaters;
        }

        public String getAverage() {
            return average;
        }

        public void setAverage(String average) {
            this.average = average;
        }

        public int getMin() {
            return min;
        }

        public void setMin(int min) {
            this.min = min;
        }
    }

    public static class ImagesBean {
        /**
         * small : https://img3.doubanio.com/spic/s1154446.jpg
         * large : https://img3.doubanio.com/lpic/s1154446.jpg
         * medium : https://img3.doubanio.com/mpic/s1154446.jpg
         */

        private String small;
        private String large;
        private String medium;

        public String getSmall() {
            return small;
        }

        public void setSmall(String small) {
            this.small = small;
        }

        public String getLarge() {
            return large;
        }

        public void setLarge(String large) {
            this.large = large;
        }

        public String getMedium() {
            return medium;
        }

        public void setMedium(String medium) {
            this.medium = medium;
        }
    }

    public static class TagsBean {
        /**
         * count : 1
         * name : 人际
         * title : 人际
         */

        private int count;
        private String name;
        private String title;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
