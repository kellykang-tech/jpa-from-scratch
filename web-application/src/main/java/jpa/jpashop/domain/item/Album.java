package jpa.jpashop.domain.item;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("A")
public class Album extends Item {

    private String artist;

    private String etc;


    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getEtc() {
        return etc;
    }

    public void setEtc(String etc) {
        this.etc = etc;
    }

    @Override
    public String getTitle() {
        return "[ 제목:" + getName() + ", 가수: " + getArtist() + " ]";
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
