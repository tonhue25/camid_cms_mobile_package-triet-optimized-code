package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the BOOKS_PRE database table.
 * 
 */
@Entity
@Table(name="BOOKS_PRE")
@NamedQuery(name="BooksPre.findAll", query="SELECT b FROM BooksPre b")
public class BooksPre implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	@Column(name="BOOK_NAME")
	private String bookName;

	private String code;

	private String publisher;

	public BooksPre() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getBookName() {
		return this.bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPublisher() {
		return this.publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

}