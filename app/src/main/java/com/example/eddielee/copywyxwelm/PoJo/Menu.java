package com.example.eddielee.copywyxwelm.PoJo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by eddie on 2017/10/19.
 */

public class Menu implements Serializable {

        private int id;
        private String title;
        private String ingredients;
        private String burden;
        private String albums;
        private List<StepsBean> steps;


        public Menu(int id, String title, String ingredients, String burden, String albums, List<StepsBean> steps) {
            this.id = id;
            this.title = title;
            this.ingredients = ingredients;
            this.burden = burden;
            this.albums = albums;
            this.steps = steps;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getIngredients() {
            return ingredients;
        }

        public void setIngredients(String ingredients) {
            this.ingredients = ingredients;
        }

        public String getBurden() {
            return burden;
        }

        public void setBurden(String burden) {
            this.burden = burden;
        }

        public String getAlbums() {
            return albums;
        }

        public void setAlbums(String albums) {
            this.albums = albums;
        }

        public List<StepsBean> getSteps() {
            return steps;
        }

        public void setSteps(List<StepsBean> steps) {
            this.steps = steps;
        }

}

