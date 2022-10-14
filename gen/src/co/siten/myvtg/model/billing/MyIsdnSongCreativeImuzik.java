package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the MY_ISDN_SONG_CREATIVE_IMUZIK database table.
 * 
 */
@Entity
@Table(name="MY_ISDN_SONG_CREATIVE_IMUZIK")
@NamedQuery(name="MyIsdnSongCreativeImuzik.findAll", query="SELECT m FROM MyIsdnSongCreativeImuzik m")
public class MyIsdnSongCreativeImuzik implements Serializable {
	private static final long serialVersionUID = 1L;

	private String isdn;

	@Column(name="SONG_ID")
	private String songId;

	public MyIsdnSongCreativeImuzik() {
	}

	public String getIsdn() {
		return this.isdn;
	}

	public void setIsdn(String isdn) {
		this.isdn = isdn;
	}

	public String getSongId() {
		return this.songId;
	}

	public void setSongId(String songId) {
		this.songId = songId;
	}

}