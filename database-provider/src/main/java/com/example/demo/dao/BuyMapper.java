package com.example.demo.dao;

import java.util.Map;

public interface BuyMapper {
    /**
     * @param Map
     * @IN :"op","money","userid"
     * @OUT: "succ"> String
     */
    String moneyChange(Map<String,Object> Map);

    /**
     * @param Map
     * @IN :"userid","songid","type"
     * @OUT: "succ"> String
     */
    String addBuy(Map<String,Object> Map);
    
    /**
     * @param Map
     * @IN :"userid"
     * @OUT: "buyrecord"> ArrayList<Buy>
     */
    void getBuyRecord(Map<String,Object> Map);

    /**
     * @param Map
     * @IN : "userid"
     * @OUT: "songs" > ArrayList<Song>
     */
    void getBoughtSongs(Map<String,Object> Map);

    /**
     * @param Map
     * @IN : "userid"
     * @OUT: "albums" > ArrayList<Album>
     */
    void getBoughtAlbums(Map<String,Object> Map);

    void isMusicBought(Map<String,Object> Map);

}