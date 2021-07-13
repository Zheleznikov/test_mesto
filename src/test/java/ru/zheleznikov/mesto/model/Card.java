package ru.zheleznikov.mesto.model;


import com.google.gson.annotations.Expose;

import java.util.List;
import java.util.Objects;

public class Card {

    @Expose
    private String _id;
    @Expose
    private String name;
    @Expose
    private String link;

    @Expose
    private List<String> likes;

    @Expose
    private User owner;

    public User getOwner() {
        return owner;
    }

    public Card withOwner(User owner) {
        this.owner = owner;
        return this;
    }



    public int getLikes() {
        return likes.size();
    }

    public Card withLikes(List<String> likes) {
        this.likes = likes;
        return this;
    }


    public String get_id() {
        return _id;
    }

    public Card with_id(String _id) {
        this._id = _id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Card withName(String name) {
        this.name = name;
        return this;
    }

    public String getLink() {
        return link;
    }

    public Card withLink(String link) {
        this.link = link;
        return this;
    }

    @Override
    public String toString() {
        return "Card{" +
                ", _id='" + _id + '\'' +
                ", name='" + name + '\'' +
                ", link='" + link + '\'' +
                ", likes=" + likes +
                ", owner=" + owner +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return Objects.equals(_id, card._id)
                && Objects.equals(name, card.name)
                && Objects.equals(link, card.link);
//                && Objects.equals(likes, card.likes)
//                && Objects.equals(owner, card.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_id, name, link, likes, owner);
    }
}
