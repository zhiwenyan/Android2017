package zhiwenyan.cmccaifu.com.android2017.recyclerview;

/**
 * Created by digvita on 2018/4/13.
 */

public class Leaderboard {
    private int ranking;
    private String ranking_search;
    private String ranking_water;
    private String ranking_vitality;


    public Leaderboard() {
    }

    public Leaderboard(int ranking, String ranking_search, String ranking_water, String ranking_vitality) {
        this.ranking = ranking;
        this.ranking_search = ranking_search;
        this.ranking_water = ranking_water;
        this.ranking_vitality = ranking_vitality;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public int getRanking() {
        return ranking;
    }

    public String getRanking_search() {
        return ranking_search;
    }

    public void setRanking_search(String ranking_search) {
        this.ranking_search = ranking_search;
    }

    public String getRanking_water() {
        return ranking_water;
    }

    public void setRanking_water(String ranking_water) {
        this.ranking_water = ranking_water;
    }

    public String getRanking_vitality() {
        return ranking_vitality;
    }

    public void setRanking_vitality(String ranking_vitality) {
        this.ranking_vitality = ranking_vitality;
    }
}
