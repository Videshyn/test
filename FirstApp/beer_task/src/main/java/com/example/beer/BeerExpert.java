package com.example.beer;

import java.util.ArrayList;

/**
 * Created by Vedia on 20.06.2017.
 */

public class BeerExpert {
    public ArrayList<String> getBeers(String beerName){
        ArrayList<String> beerList = new ArrayList<>();
        if (beerName.equals("amber")){
            beerList.add("Carlsberg");
            beerList.add("Черниговское");
        }else  if (beerName.equals("dark")){
            beerList.add("Obolon'");
            beerList.add("Рогань");
        }else
            beerList.add("Putinka");
        return beerList;
    }
}
