package ru.zheleznikov.mesto.main.model;


import com.google.gson.annotations.Expose;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Objects;

@Data
@Accessors(chain = true)
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
