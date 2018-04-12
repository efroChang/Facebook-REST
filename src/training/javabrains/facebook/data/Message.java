package training.javabrains.facebook.data;

import java.util.*;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

// KEY: Auto convert object to XML!!
@XmlRootElement
public class Message {

	private long id;
	private String message;
	private Date created;
	private String author;
	private Map<Long, Comment> commentMap = new HashMap<>();
	private List<Link> LinkList = new ArrayList<>();

	// Necessary to have no op constructor
	public Message() {

	}

	public Message(long id, String message, String author) {
		super();
		this.id = id;
		this.message = message;
		this.created = new Date();
		this.author = author;
	}

	@XmlTransient // KEY: This makes sure the Comments won't be returned with the Messages
	public Map<Long, Comment> getComments() {
		return commentMap;
	}

	public void setComments(Map<Long, Comment> map) {
		this.commentMap = map;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Map<Long, Comment> getCommentMap() {
		return commentMap;
	}

	public void setCommentMap(Map<Long, Comment> commentMap) {
		this.commentMap = commentMap;
	}

	public List<Link> getLinkList() {
		return LinkList;
	}

	public void setLinkList(List<Link> linkList) {
		LinkList = linkList;
	}

	public void addLink(String url, String rel) {
		Link link = new Link();
		link.setLink(url);
		link.setRel(rel);
		this.getLinkList()
			.add(link);
	}

}
